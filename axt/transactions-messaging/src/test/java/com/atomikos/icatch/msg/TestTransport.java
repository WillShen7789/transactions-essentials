package com.atomikos.icatch.msg;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Stack;
import java.util.Vector;

import com.atomikos.diagnostics.Console;

 /**
  *Copyright &copy; 2002, Guy Pardon. All rights reserved.
  *
  *A test transport layer. 
  */

public class TestTransport
extends AbstractTransport
implements Runnable
{
  
    
    private Hashtable listenerstable_;
    //the table of listeners that listen
    //only on specific types
    
    private Vector anylisteners_;
    //the listeners that listen on any type
    
    private Stack inbox_;
    //where messages are put.
    
    private boolean needed_;
    //true as long as msg thread needs to work
    
    private Console console_;
    //for logging messages
    
    private String address_;
    //the address we listen on
    
    private TestTransport requestTransport_;
    //where to send request messages to
    
    private TestTransport replyTransport_;
    //where to send reply msgs to
    
    /** 
     *Constructs a new instance.
     *@param factory The message factory.
     *@param console The console to log messages to, or null if not needed.
     */
    
    public TestTransport (  
        String participantAddress , 
        String coordinatorAddress,
        Console console ) 
    {
       
        super ( "TestTransport" , participantAddress , coordinatorAddress ,
        	CommitProtocol.PROTOCOL_UNKNOWN , Transport.UNKNOWN_PROTOCOL , TransactionMessage.FORMAT_UNKNOWN , 10000 );
        
        needed_ = true;
        inbox_ = new Stack();
        console_ = console;
        new Thread ( this ).start();
    }
    
    public void setRequestTranspor ( TestTransport t )
    {
        
        requestTransport_ = t;
    }
    
    public void setReplyTransport ( TestTransport t )
    {
        replyTransport_ = t;
    }
    
    private void logMessage ( TransactionMessage msg )
    {
          try {
              if ( console_ != null ) {
            	     
                  console_.println ( "TestTransport: dealing with message " + msg.toString() ); 
                  console_.println ( "" );
              }
          }
          catch ( IOException io ) {}    
    }
 
	
	protected void replyReceived ( TransactionMessage msg ) {
		super.replyReceived ( msg );
		//System.out.println ( "Reply received: " + msg );
	}
    
    protected void requestReceived ( TransactionMessage msg ) {
    	super.requestReceived ( msg );
    	//System.out.println ( "Request received: " + msg );
    }

    
  /**
    *@see Transport
    */
      
    public void send ( TransactionMessage msg )
    throws TransportException, IllegalMessageTypeException
    {
        synchronized ( inbox_ ) {
            inbox_.push ( msg ); 
            inbox_.notifyAll();
            logMessage ( msg );
        }
        //Configuration.logDebug ( "Transport: msg sent: " + msg );
    }
    
    private String correctURI ( String uri )
    {
    	//remove proxy for target in tests
    	if ( uri.endsWith(":proxy")) {
    		uri = uri.substring(0,uri.length() - 6);
    	}
    	return uri;
    }
    
	public ForgetMessage createForgetMessage ( String senderURI , 
		   String targetURI, String targetAddress )
	{
		   return	super.createForgetMessage( senderURI , 
		correctURI ( targetURI ) , targetAddress );
	}
    
	public CommitMessage createCommitMessage (
		  boolean onephase, String senderURI , 
		  String targetURI, String targetAddress )
	{
		return super.createCommitMessage ( onephase , 
			senderURI  , correctURI ( targetURI ), 
			targetAddress );
	}
    
    
	public PrepareMessage createPrepareMessage ( String senderURI , 
		   String targetURI, String targetAddress )
		   
	{
		return super.createPrepareMessage (
			senderURI  , correctURI ( targetURI ) , 
			targetAddress
 		);
	}
    
	public PrepareMessage createPrepareMessage ( 
		   int globalSiblingCount, CascadeInfo[] cascadeInfo, String senderURI , 
		   String targetURI, String targetAddress )
	{
		return super.createPrepareMessage (
			globalSiblingCount , cascadeInfo , 
			senderURI , correctURI ( targetURI ) , targetAddress
		);
	}
 
	public RollbackMessage createRollbackMessage ( String senderURI , 
		   String targetURI, String targetAddress )
	{
		return super.createRollbackMessage (
			senderURI , correctURI ( targetURI ) ,
			targetAddress
		);
	}
 
    public void run()
    {
        System.err.println ( "TestTransport: listener thread running..." );
        
        while ( needed_ ) {
          
            try {
                synchronized ( inbox_ ) {
                  
                    while ( inbox_.empty() && needed_ ) {
                        inbox_.wait ( 100 ) ;
                    }
                
                    if ( ! inbox_.empty() ) {
                        TransactionMessage msg =
                            ( TransactionMessage ) inbox_.pop();
                        Thread t = new Thread ( 
                                new NotificationThread ( 
                                        requestTransport_ , 
                                        replyTransport_ , msg ) );
                        t.start();
                        //notifyListeners ( msg ); 
                    }
                } //synchronized 
            }
            catch ( Exception e ) {
                System.err.println ( "Error in transport thread" );
                e.printStackTrace(); 
            } //try
        } //while
        
        System.err.println ( "TestTransport: listener thread exiting..." );
    } 
    
  

         
}
