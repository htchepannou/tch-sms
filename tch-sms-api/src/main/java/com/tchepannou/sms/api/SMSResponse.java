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

import java.io.Serializable;

/**
 *
 * @author herve
 */
public class SMSResponse
    implements Serializable
{
    //-- Attribute
    private boolean _success;
    private String _status;
    private String _statusText;
    private String _transactionId;

    
    //-- Getter/Setter
    public boolean isSuccess ()
    {
        return _success;
    }

    public String getStatusText ()
    {
        return _statusText;
    }

    public void setSuccess (boolean success)
    {
        this._success = success;
    }

    public void setStatusText (String statusText)
    {
        this._statusText = statusText;
    }

    public String getStatus ()
    {
        return _status;
    }

    public void setStatus (String status)
    {
        this._status = status;
    }

    public String getTransactionId ()
    {
        return _transactionId;
    }

    public void setTransactionId (String transactionId)
    {
        this._transactionId = transactionId;
    }
}
