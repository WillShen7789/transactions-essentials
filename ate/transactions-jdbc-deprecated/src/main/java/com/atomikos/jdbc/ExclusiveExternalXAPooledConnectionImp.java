package com.atomikos.jdbc;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;
import javax.sql.XAConnection;

import com.atomikos.datasource.ResourceTransaction;
import com.atomikos.datasource.TransactionalResource;
import com.atomikos.icatch.CompositeTransaction;
import com.atomikos.icatch.Synchronization;
import com.atomikos.icatch.TxState;
import com.atomikos.icatch.system.Configuration;

/**
 * 
 * 
 * A non-shared implementation of a pooled connection. Some DBs such as Oracle
 * or SQLServer deviate from the standard in the moment where an XAResource can
 * be reused. This has to be AFTER 2PC (contrary to standard). Instances of this
 * class will generate TWO close events, and only the second will have
 * isDiscarded set to true.
 */

public class ExclusiveExternalXAPooledConnectionImp extends
        ExternalXAPooledConnectionImp implements Synchronization
{

    private boolean afterCompletionDone_ = false;

    public ExclusiveExternalXAPooledConnectionImp ( XAConnection c ,
            TransactionalResource res )
    {
        super ( c , res );
    }

    public ExclusiveExternalXAPooledConnectionImp ( XAConnection c ,
            TransactionalResource res , PrintWriter logWriter )
    {
        super ( c , res , logWriter );
    }

    public synchronized void setResourceTransaction ( ResourceTransaction restx )
            throws SQLException
    {
        // System.out.println ( "setResourceTransaction");
        super.setResourceTransaction ( restx );
        try {
            // add synchronization to current CT.
            // NOTE: since we are only reusable AFTER termination
            // of the CT, we can not add ourselves as synchronization
            // more than once -> GOOD, otherwise we would have
            // dangerous behaviour (multiple terminated notifications
            // would mess up reused connections!)
            // System.out.println ( "registering synchronization" );
            CompositeTransaction ct = Configuration
                    .getCompositeTransactionManager ()
                    .getCompositeTransaction ();
            ct.registerSynchronization ( this );
            afterCompletionDone_ = false;
            // added: needed for reuse of aborts (beforecompletion not called!)

        } catch ( Exception e ) {
        	AtomikosSQLException.throwAtomikosSQLException ( e.getMessage() , e );
        }
    }

    public void beforeCompletion ()
    {

        // reset from previous tx; for afterCompletion processing
        afterCompletionDone_ = false;
    }

    public void afterCompletion ( Object state )
    {
        // this method is called once for every
        // SQL method done in this transaction
        // so don't repeat this every time
        if ( afterCompletionDone_ )
            return;

        if ( state.equals ( TxState.TERMINATED )
                || state.equals ( TxState.HEUR_MIXED )
                || state.equals ( TxState.HEUR_HAZARD )
                || state.equals ( TxState.HEUR_ABORTED )
                || state.equals ( TxState.HEUR_COMMITTED ) ) {

            // connection is reusable!

            setDiscarded ();

            // next, notify listeners of REAL close event.
            ConnectionEvent e2 = new ConnectionEvent ( this );
            Enumeration enumm = listeners_.elements ();
            while ( enumm.hasMoreElements () ) {
                ConnectionEventListener l = (ConnectionEventListener) enumm
                        .nextElement ();
                l.connectionClosed ( e2 );
            }
            afterCompletionDone_ = true;
            

        }

        // System.out.println ( "afterCompletion ( " + state + " )" );

    }

    public void connectionClosed ( ConnectionEvent e )
    {

        // overridden from base class, to NOT set discarded

        ResourceTransaction restx = unsetResourceTransaction ();
        if ( restx != null )
        		suspendResourceTransaction ( restx );
        else {
            // if not in tx, then don't wait until tx finishes to reuse!
            setDiscarded ();
            ConnectionEvent e2 = new ConnectionEvent ( this );
            Enumeration enumm = listeners_.elements ();
            while ( enumm.hasMoreElements () ) {
                ConnectionEventListener l = (ConnectionEventListener) enumm
                        .nextElement ();
                l.connectionClosed ( e2 );
            }
        }
    }

}
