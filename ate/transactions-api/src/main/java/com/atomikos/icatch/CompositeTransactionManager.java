package com.atomikos.icatch;

/**
 *
 *
 *A composite transaction manager. This interface
 *outlines the API for managing composite transactions
 *in the local VM.
 */
 
 public interface CompositeTransactionManager
 {
    /**
     *Starts a new (sub)transaction (not an activity) for the current thread.
     *Associates the current thread with that instance.
     *<br>
     *<b>NOTE:</b> subtransactions should not be mixed: either each subtransaction is
     *an activity, or not (default). Use suspend/resume if mixed models are necessary:
     *for instance, if you want to create a normal transaction within an activity, then
     *suspend the activity first before starting the transaction. Afterwards, resume the
     *activity.
     *
     *@timeout Timeout ( in ms ) for the transaction.
     *
     *@return CompositeTransaction The new instance.
     *@exception SysException Unexpected error.
     *@exception IllegalStateException If there is an existing transaction that is 
     *an activity instead of a classical transaction. 
     *
     */

    public CompositeTransaction createCompositeTransaction ( 
                                                             long timeout ) 
        throws SysException, IllegalStateException;
    
    /**
     *Gets the composite transaction for the current thread.
     *
     *@return CompositeTransaction The instance for the current thread, null if none.
     *
     *@exception SysException Unexpected failure.
     */

      public CompositeTransaction getCompositeTransaction () throws SysException
;
      
       /**
        *Gets the composite transaction with the given id.
        *This method is useful e.g. for retrieving a suspended 
        *transaction by its id.
        *
        *@param tid The id of the transaction.
        *@return CompositeTransaction The transaction with the given id,
        *or null if not found.
        *@exception SysException Unexpected failure.
        */
        
      public CompositeTransaction getCompositeTransaction ( String tid )
      throws SysException;
      
    /**
     *Re-maps the thread to the given tx.
     *
     *@param ct The CompositeTransaction to resume.
     *@exception IllegalStateException If thread has tx already.
     *@exception SysException Unexpected failure.
     */
     
      public void resume ( CompositeTransaction ct )
        throws IllegalStateException, SysException;
        
    /**
     *Suspends the tx for the current thread.
     *
     *@return CompositeTransaction The transaction for the current thread.
     *
     *@exception SysException On failure.
     */

      public CompositeTransaction suspend() throws SysException ;
//      
//      /**
//       * Starts a new transaction with the option of making it an activity.
//       * Activities are treated differently: they are recoverable even while active,
//       * and can be longer-running and compensation-based. 
//       * @param timeout The timeout in ms.
//       * @param activity True if the instance needs to be an activity.
//       * @return The instance.
//       * @throws SysException Unexpected error.
//       * @throws IllegalStateException If an incompatible transaction already exists 
//       * (activities should not be mixed with other transactions).
//       */
//      
//      public CompositeTransaction createCompositeTransaction ( long timeout , boolean activity )
//      throws SysException, IllegalStateException;


 }
