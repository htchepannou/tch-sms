/*
 * Copyright 2012 Herve Tchepannou Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tchepannou.sms.dynamark;

import com.tchepannou.sms.api.SMSGateway;
import com.tchepannou.sms.api.SMSRequest;
import com.tchepannou.sms.api.SMSResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author herve
 */
public class DynamarkGateway
    implements SMSGateway
{
    //-- Static Attributes
    public static final String PROPERTY_USER = "user";
    public static final String PROPERTY_PASSWORD= "password";
    
    
    //-- Attribute
    private Properties _properties;
    
    
    
    //-- SMSGateway override
    public void init (Properties properties)
    {
        _properties = properties;
    }

    public SMSResponse process (SMSRequest request)
        throws IOException
    {
        String user = _properties.getProperty (PROPERTY_USER);
        String password = _properties.getProperty (PROPERTY_PASSWORD);
        
        HttpClient client = new HttpClient ();
        
        /* posting */
        String url = "http://services.dynmark.com/HttpServices/SendMessage.ashx";
        PostMethod post = new PostMethod(url);
        post.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");        
        NameValuePair[] data = {
          new NameValuePair("user", user),
          new NameValuePair("password", password),
          new NameValuePair("to", request.getTo ()),
          new NameValuePair("text", request.getBody ())
        };
        
        post.setRequestBody(data);        
        client.executeMethod(post);         
        //String response = post.getResponseBodyAsString();
        
        SMSResponse resp = new SMSResponse ();
        SAXReader reader = new SAXReader();
        try
        {
            Document doc = reader.read (post.getResponseBodyAsStream ());
            Element root = doc.getRootElement ();
            String status = root.attributeValue ("status");
            if ("failed".equals(status))
            {
                resp.setSuccess (false);
                resp.setStatus (status);
                resp.setStatusText (root.elementText ("failureDescription"));
            }
            else
            {
                Element r = root.element ("Response");
                resp.setSuccess (true);
                resp.setTransactionId (r.attributeValue ("TransactionID"));                
            }
            
            return resp;        
        }
        catch (DocumentException e)
        {
            throw new IOException ("Document error", e);
        }
    }
    
}
