package com.atomikos.datasource;

import com.atomikos.icatch.HeuristicMessage;
/**
 *
 *
 *The notion of a local transaction executed on a resource.
 *Serves as a handle  towards the transaction management module.
 */
 
public interface ResourceTransaction //extends Participant
{
   
    
    
    /**
     *Get identifier for this tx.
     *Should be unique in system.
     *
     *@return String The identifier, as determined by resource.
     */

    public java.lang.String getTid();
    
 
  


    /**
     *Get the resource for this transaction.
     *
     *@return TransactionalResource The resource on whose behalf this tx is.
     */

    //public TransactionalResource getResource();

    /**
     *Add a compensation context for this resourcetx.
     *
     *@param context The compensation context.
     *@exception IllegalStateException If no longer active.
     */

    public void addCompensationContext(java.util.Dictionary context)
        throws IllegalStateException;

    /**
     *Add heuristic resolution information.
     *@param mesg The heuristic message.
     *@exception IllegalStateException If no longer active.
     */

    public void addHeuristicMessage(HeuristicMessage mesg)
        throws IllegalStateException;
    
   
    /**
     *Get heuristic context info.
     *
     *@return HeuristicMessage[] An array of messages, or null if none.
     */

    public HeuristicMessage[] getHeuristicMessages();

    
    /**
     *Get the compensation information.
     *
     *@return java.util.Dictionary The compensation info, or null if none.
     */

    public java.util.Dictionary getCompensationContext();

    /**
     *Suspend the resourcetransaction, so that underlying resources can
     *be used for a next (sibling) invocation.
     *This is also the recommended method for adding the 
     *resourcetx to the coordinator object, but ONLY if 
     *the transaction has not been set for rollback.
     *NOTE: suspend is NOT the same as XAResource's suspension!
     *The XAResource version is specific to the XA protocol, 
     *and does not belong in the composite system framework.
     *As mentioned in the JTA specs., the APPLICATION SERVER is 
     *responsible for XAsuspension and XAresume ( page 11 of JTA
     *API, version of May 12, 1999 ).
     *
     *@exception IllegalStateException If wrong state.
     */

    public void suspend() throws IllegalStateException,ResourceException;

    /**
     *Resume a suspended tx.
     *
     *@exception IllegalStateException If not right state.
     */


    public void resume() throws IllegalStateException,ResourceException;
       
}
