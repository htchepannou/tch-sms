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
package com.tchepannou.sms.api;

import java.io.IOException;

/**
 *
 * @author herve
 */
public class SMSService
{
    //-- Attributes
    private SMSGateway _gateway;
    private SMSListener _listener;

    //-- Constructor
    public SMSService (SMSGateway gateway)
    {
        _gateway = gateway;
    }


    //-- SMSService overrides
    public SMSResponse process (SMSRequest request)
        throws IOException
    {
        SMSResponse response = _gateway.process (request);
        notifyListener (request, response);
        return response;
    }

    public void setSMSListener (SMSListener listener)
    {
        _listener = listener;
    }


    //-- Private method
    private void notifyListener (SMSRequest request, SMSResponse response)
    {
        if (_listener == null)
        {
            return;
        }
        _listener.onProcess (request, response);
    }
    
}
