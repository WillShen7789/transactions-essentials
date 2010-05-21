package com.atomikos.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import com.atomikos.datasource.xa.TestXAResource;
import com.atomikos.datasource.xa.jms.JmsTransactionalResource;
import com.atomikos.icatch.StringHeuristicMessage;
import com.atomikos.icatch.config.TSInitInfo;
import com.atomikos.icatch.config.UserTransactionService;
import com.atomikos.icatch.config.UserTransactionServiceImp;
import com.atomikos.icatch.config.imp.AbstractUserTransactionServiceFactory;
import com.atomikos.icatch.imp.TransactionServiceTestCase;

public class JtaTopicConnectionFactoryTestJUnit extends TransactionServiceTestCase 
{
	
	private UserTransactionService uts;
	private TransactionManager tm;
	private TopicConnection conn;
	private TopicSession session;
	private HeuristicTopicPublisher sender;
	private HeuristicTopicSubscriber receiver;
	private StringHeuristicMessage hmsg;
	private TestXAResource xares;

	public JtaTopicConnectionFactoryTestJUnit ( String name )
	{
		super ( name );
	}

	protected void setUp()
	{
		super.setUp();
		
		uts =
            new UserTransactionServiceImp();
		 
		xares = new TestXAResource();
         TestTopicConnectionFactory fact =
            new TestTopicConnectionFactory ( xares );
        JtaTopicConnectionFactory jtaFact =
            new JtaTopicConnectionFactory ( "TestJmsResource" , fact );        
        
        JmsTransactionalResource res =
            jtaFact.getTransactionalResource();
        
        uts.registerResource ( res );
        
        try {
			conn = jtaFact.createTopicConnection();
			session = conn.createTopicSession ( true , 0 );
		} catch (JMSException e ) {
			failTest ( e.getMessage() );
		}
        try {
			sender = ( HeuristicTopicPublisher ) session.createPublisher ( null );
			receiver = ( HeuristicTopicSubscriber ) session.createSubscriber ( null );
		} catch (JMSException e) {
			failTest ( e.getMessage() );
		}
        hmsg =
            new StringHeuristicMessage ( "TestMessage" );
		
	    TSInitInfo info = uts.createTSInitInfo();
        Properties properties = info.getProperties();        
        properties.setProperty ( 
				AbstractUserTransactionServiceFactory.TM_UNIQUE_NAME_PROPERTY_NAME , "JtaTopicConnectionFactoryTestJUnit" );
        	properties.setProperty ( AbstractUserTransactionServiceFactory.OUTPUT_DIR_PROPERTY_NAME , getTemporaryOutputDir() );
        	properties.setProperty ( AbstractUserTransactionServiceFactory.LOG_BASE_DIR_PROPERTY_NAME , getTemporaryOutputDir()
        	        );
        	properties.setProperty ( AbstractUserTransactionServiceFactory.CONSOLE_LOG_LEVEL_PROPERTY_NAME , "DEBUG" );
        	properties.setProperty ( AbstractUserTransactionServiceFactory.MAX_ACTIVES_PROPERTY_NAME , "25000" );
		uts.init ( info );
		tm = uts.getTransactionManager();
	}
	
	protected void tearDown()
	{
		try {
			if ( session != null ) session.close();
			if ( conn != null ) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
 		uts.shutdown ( true );
		super.tearDown();
	}
	
	public void testSendWithoutTransaction()
	{
        try {
            sender.publish ( null , hmsg );
            failTest ( "ERROR: send without tx works?" );
        }
        catch ( JMSException e ) {
            //should happen: no tx started!
        }
	}
	
	public void testSendWithCommit()
	throws Exception
	{
	       tm.begin();
	        //send null msg, merely to test enlist /delist
	        sender.publish ( null , hmsg );
	        
	        if ( xares.getLastStarted() == null )
	            failTest ( "Send does not cause enlist??" );
	        if ( xares.getLastEnded() == null )
	            failTest ( "Send does not end with delist??" );
	        if ( ! xares.getLastStarted().equals ( xares.getLastEnded() ) )
	            failTest ( "Send does enlist/delist with # Xid??" );
	        
	        //now, commit
	        tm.commit();
	        
	        //assert that commit is done in xa 
	        if ( xares.getLastCommitted() == null )
	            failTest ( "Send: tx commit not propagated to xa?" );
	        if ( !xares.getLastCommitted().equals ( xares.getLastEnded() ) )
	            failTest ( "Send: tx commit with # Xid?" );
	}
	
	public void testSendWithRollback()
	throws Exception
	{
        tm.begin();
        //send null msg, merely to test enlist /delist
        sender.publish ( null , hmsg );
        
        if ( xares.getLastStarted() == null )
            failTest ( "Send does not cause enlist??" );
        if ( xares.getLastEnded() == null )
            failTest ( "Send does not end with delist??" );
        if ( ! xares.getLastStarted().equals ( xares.getLastEnded() ) )
            failTest ( "Send does enlist/delist with # Xid??" );
        
        //now, rollback
        tm.rollback();
        
        //assert that rollback is done in xa 
        if ( xares.getLastRolledback() == null )
            failTest ( "Send: tx rollback not propagated to xa?" );
        if ( !xares.getLastRolledback().equals ( xares.getLastEnded() ) )
            failTest ( "Send: tx rollback with # Xid?" );
	}
	
	public void testReceiveWithoutTransaction()
	throws Exception
	{
		Message msg = null;
        try {
            msg = receiver.receive ( "" );
           failTest ( "Error: receive works without tx?" );
        }
        catch ( JMSException e ) {
            //should happen: no tx yet!
        }
	}
	
	public void testReceiveWithCommit()
	throws Exception
	{
        tm.begin();
        Message msg = receiver.receive ( "" );
         if ( xares.getLastStarted() == null )
            failTest ( "Receive does not cause enlist??" );
        if ( xares.getLastEnded() == null )
            failTest ( "Receive does not end with delist??" );
        if ( ! xares.getLastStarted().equals ( xares.getLastEnded() ) )
            failTest ( "Receive does enlist/delist with # Xid??" );
        
        //now, commit
        tm.commit();
        
        //assert that commit is done in xa 
        if ( xares.getLastCommitted() == null )
            failTest ( "Receive: tx commit not propagated to xa?" );
        if ( !xares.getLastCommitted().equals ( xares.getLastEnded() ) )
            failTest ( "Receive: tx commit with # Xid?" );
	}
	
	public void testReceiveWithRollback()
	throws Exception
	{
        tm.begin();
        Message msg = receiver.receive ( "" );
         if ( xares.getLastStarted() == null )
            failTest ( "Receive does not cause enlist??" );
        if ( xares.getLastEnded() == null )
            failTest ( "Receive does not end with delist??" );
        if ( ! xares.getLastStarted().equals ( xares.getLastEnded() ) )
            failTest ( "Receive does enlist/delist with # Xid??" );
        
        //now, rollback
        tm.rollback();
        
        //assert that rb is done in xa 
        if ( xares.getLastRolledback() == null )
            failTest ( "Receive: tx rollback not propagated to xa?" );
        if ( !xares.getLastRolledback().equals ( xares.getLastEnded() ) )
            failTest ( "Receive: tx rollback with # Xid?" );
	}
	
	public void testNumberOfConnections()
	throws Exception
	{
    	UserTransaction utx = uts.getUserTransaction();
		utx.begin();
		int connections = TestTopicConnectionFactory
				.getNumberOfConnectionsCreated();

		HeuristicTopicPublisher sender = (HeuristicTopicPublisher) session
				.createPublisher(null);
		sender.publish (null, hmsg);

		sender.close();

		utx.commit();

		if (connections != TestTopicConnectionFactory
				.getNumberOfConnectionsCreated())
			failTest("MQ problem: sending creates new XAConnection");
    
	}
}

