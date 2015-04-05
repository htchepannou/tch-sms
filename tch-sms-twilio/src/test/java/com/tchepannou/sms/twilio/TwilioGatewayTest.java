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

import com.tchepannou.sms.api.SMSRequest;
import com.tchepannou.sms.api.SMSResponse;
import java.util.Properties;
import junit.framework.TestCase;

/**
 *
 * @author herve
 */
public class TwilioGatewayTest extends TestCase
{
    public TwilioGatewayTest (String testName)
    {
        super (testName);
    }

    public void testProcess ()
        throws Exception
    {
        Properties p = new Properties ();
        p.setProperty (TwilioGateway.PROPERTY_ACCOUNT_SID, "ACcc63c0af55a6437792b8e49a2bea3f89");
        p.setProperty (TwilioGateway.PROPERTY_AUTH_CODE, "9f6190aa684ab2b592da1b47918415f3");
        p.setProperty (TwilioGateway.PROPERTY_FROM, "+14155992671");
        
        SMSRequest req = new SMSRequest ();
        req.setTo("+15147580191");
        req.setBody ("twilio: This is a test from is-sms-web");
        
        TwilioGateway g = new TwilioGateway ();
        g.init (p);
        SMSResponse resp = g.process (req);
        
        assertTrue("success", resp.isSuccess ());
        assertNotNull("transactionId", resp.getTransactionId ());
    }
    public void testProcessBadNumber ()
        throws Exception
    {
        Properties p = new Properties ();
        p.setProperty (TwilioGateway.PROPERTY_ACCOUNT_SID, "ACcc63c0af55a6437792b8e49a2bea3f89");
        p.setProperty (TwilioGateway.PROPERTY_AUTH_CODE, "9f6190aa684ab2b592da1b47918415f3");
        p.setProperty (TwilioGateway.PROPERTY_FROM, "+14155992671");
        
        SMSRequest req = new SMSRequest ();
        req.setTo("xxx");
        req.setBody ("This is a test from is-sms-web");
        
        TwilioGateway g = new TwilioGateway ();
        g.init (p);
        SMSResponse resp = g.process (req);
        
        assertFalse("success", resp.isSuccess ());
    }
}
