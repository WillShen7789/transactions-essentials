package com.atomikos.jdbc;
import java.sql.Connection;

import javax.sql.DataSource;

import com.atomikos.icatch.CompositeTransaction;
import com.atomikos.icatch.Synchronization;

 /**
  *A test thread for testing the exclusive connection option.
  */

class TestSynchronization
implements Synchronization
{
     private DataSource ds_;
     //the data source to get a connection from
     
     private Connection connection_;
     //the connection as gotten from the datasource.
     
     private CompositeTransaction ct_;
     //where to listen for beforecompletion
     
     TestSynchronization ( DataSource ds , CompositeTransaction ct )
     {
          super();
          ds_ = ds;
          ct_ =ct;
     }
     
     public synchronized void beforeCompletion()
     {
          try {
              //get and close the connection, to check if the 
              //pool returns the right instance.
              connection_ = ds_.getConnection();
              connection_.close();
          }
          catch ( Exception e ) {
              throw new RuntimeException ( e.getMessage() ); 
          }  
     }
     
     public synchronized void afterCompletion ( Object s )
     {
          //nothing to do
     }
     
     /**
      *Retrieves the connection as gotten from the pool.
      *This method can be used to test if a different
      *connection is gotten for exclusive mode.
      *(In combination with a pool of size 1).
      */
      
     synchronized Connection getConnection()
     {

          return connection_;
     }
     
    
     
}
