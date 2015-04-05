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

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.tchepannou.sms.api.SMSNumberParseException;

/**
 *
 * @author herve
 */
public class SMSHelper
{
    //-- Public methods
    /**
     * Format a phone number to make it SMS friendly
     * 
     * @param number Phone number
     * @param country country
     * 
     * @return formatted number
     * @throws SMSNumberParseException  if the number of invalid
     */
    public static String formatNumber (String number, String country)
        throws SMSNumberParseException
    {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance ();
        try
        {
            PhoneNumber proto = phoneUtil.parse (number, country);
            return phoneUtil.format(proto, PhoneNumberFormat.E164);
        }
        catch ( NumberParseException e )
        {
            throw new SMSNumberParseException("Invalid number", e);
        }
    }
}
