package com.atomikos.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.Synchronization;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import com.atomikos.icatch.jta.UserTransactionManager;

/**
 * 
 * 
 * 
 * 
 *
 * 
 */
public class TestMessageListener 
implements MessageListener, Synchronization
{

	private Message lastMessage;
	private boolean committed = false;
	private boolean simulateError = false;
	private UserTransactionManager utm = new UserTransactionManager();
	private boolean wasClosed = false;
	
	public void setSimulateError ( boolean error )
	{
		simulateError = error;
	}
  
    public void onMessage(Message msg)
    {
    	if ( msg == null ) {
    		wasClosed = true;
    		return;
    		
    	} 
        this.lastMessage = msg;
        try
        {
            Transaction tx = utm.getTransaction();
            try
            {
                tx.registerSynchronization(this);
            }
            catch (IllegalStateException e1)
            {
                e1.printStackTrace();
            }
            catch (RollbackException e1)
            {
                e1.printStackTrace();
            }
        }
        catch (SystemException e)
        {
            
            e.printStackTrace();
        }
        
        committed = false;
        if ( simulateError )
        	throw new RuntimeException ( "Simulated error" );

    }
    
    public Message getLastMessage()
    {
    	return lastMessage;
    }

  
    public void afterCompletion(int state)
    {
        if ( state == Status.STATUS_COMMITTED )
        	committed = true;
        
    }

    public void beforeCompletion()
    {
        
        
    }
    
    public boolean wasCommitted()
    {
    	return committed;
    }
    
    public boolean wasClosed()
    {
    	return wasClosed;
    }

}
