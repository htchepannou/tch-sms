package com.tchepannou.sms.mock;

import com.tchepannou.sms.api.SMSGateway;
import com.tchepannou.sms.api.SMSRequest;
import com.tchepannou.sms.api.SMSResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class MockGateway 
implements SMSGateway
{
    //-- Attribute
    private boolean _sucess;
    private String _status;
    private String _statusText;
    private String _transactionId;
    
    
    //-- SMSGateway overrides
    public void init (Properties properties)
    {
    }

    public SMSResponse process (SMSRequest request)
        throws IOException
    {
        SMSResponse resp = new SMSResponse ();
        resp.setStatus (getStatus ());
        resp.setStatusText (getStatusText ());
        resp.setSuccess (isSucess ());
        resp.setTransactionId (getTransactionId ());
        return resp;
    }
    
    
    //-- Getter/Setter
    public boolean isSucess ()
    {
        return _sucess;
    }

    public String getStatus ()
    {
        return _status;
    }

    public String getStatusText ()
    {
        return _statusText;
    }

    public String getTransactionId ()
    {
        return _transactionId;
    }

    public void setSucess (boolean sucess)
    {
        this._sucess = sucess;
    }

    public void setStatus (String status)
    {
        this._status = status;
    }

    public void setStatusText (String statusText)
    {
        this._statusText = statusText;
    }

    public void setTransactionId (String transactionId)
    {
        this._transactionId = transactionId;
    }
}
