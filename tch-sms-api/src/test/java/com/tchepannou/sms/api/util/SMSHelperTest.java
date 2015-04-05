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
package com.tchepannou.sms.api.util;

import com.tchepannou.sms.api.SMSNumberParseException;
import junit.framework.TestCase;

/**
 *
 * @author herve
 */
public class SMSHelperTest extends TestCase
{
    public SMSHelperTest (String testName)
    {
        super (testName);
    }

    public void testCA ()
        throws SMSNumberParseException
    {
        String number = "514 7580191";
        String country = "CA";
        String xnumber = SMSHelper.formatNumber (number, country);
        assertEquals (xnumber, "+15147580191", xnumber);
    }

    public void testUS ()
        throws SMSNumberParseException
    {
        String number = "555-555-5555";
        String country = "US";
        String xnumber = SMSHelper.formatNumber (number, country);
        assertEquals (xnumber, "+15555555555", xnumber);
    }

    public void testFR ()
        throws SMSNumberParseException
    {
        String number = "06 12 34 56 78";
        String country = "FR";
        String xnumber = SMSHelper.formatNumber (number, country);
        assertEquals (xnumber, "+33612345678", xnumber);
    }

    public void testUK ()
        throws SMSNumberParseException
    {
        String number = "07981-555555";
        String country = "GB";
        String xnumber = SMSHelper.formatNumber (number, country);
        assertEquals (xnumber, "+447981555555", xnumber);
    }
}
