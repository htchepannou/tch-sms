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
package com.tchepannou.sms.twilio;

import com.tchepannou.sms.api.SMSGateway;
import com.tchepannou.sms.api.SMSRequest;
import com.tchepannou.sms.api.SMSResponse;
import java.io.IOException;
import java.util.Properties;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;


/**
 * Implementation of {@link SMSGateway} for <a href="https://www.twilio.com/">Twilio</a>
 * 
 * @author herve
 */
public class TwilioGateway
implements SMSGateway
{
    //-- Static Attributes
    public static final String PROPERTY_FROM = "from";
    public static final String PROPERTY_ACCOUNT_SID= "account_sid";
    public static final String PROPERTY_AUTH_CODE = "auth_code";
    
    
    //-- Attribute
    private Properties _properties;
    
    
    //-- SMSGateway overrides
    public void init (Properties properties)
    {
        _properties = properties;
    }

    public SMSResponse process (SMSRequest request)
        throws IOException
    {
        String from = _properties.getProperty(PROPERTY_FROM);
        String accountSId = _properties.getProperty(PROPERTY_ACCOUNT_SID);
        String authCode = _properties.getProperty(PROPERTY_AUTH_CODE);

        HttpClient client = new HttpClient ();
        Credentials cred = new UsernamePasswordCredentials(accountSId,authCode);
        client.getState ().setCredentials (AuthScope.ANY, cred);
        
        /* posting */
        String url = "https://api.twilio.com/2010-04-01/Accounts/" + accountSId + "/SMS/Messages.json";        
        PostMethod post = new PostMethod(url);
        NameValuePair[] data = {
          new NameValuePair("From", from),
          new NameValuePair("To", request.getTo ()),
          new NameValuePair("Body", request.getBody ())
        };
        post.setRequestBody(data);        
        client.executeMethod(post);         
        String response = post.getResponseBodyAsString();        
        
        SMSResponse resp = new SMSResponse ();
        JSONObject json = (JSONObject) JSONSerializer.toJSON( response );
        try
        {
            int status = json.getInt ("status");
            String message = json.getString ("message");
            
            resp.setSuccess (false);
            resp.setStatus (String.valueOf(status));
            resp.setStatusText (message);
        }
        catch (JSONException e)
        {
            resp.setSuccess (true);
            resp.setTransactionId (json.getString ("sid"));
        }
        return resp;
        
    }
    
}
