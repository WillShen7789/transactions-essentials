package com.atomikos.jdbc;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;

/**
 * A factory for JNDI lookup of JtaDataSourceImp instances.
 */

public class JtaDataSourceImpFactory implements ObjectFactory
{
    public JtaDataSourceImpFactory ()
    {
    }

    /**
     * @see javax.naming.spi.ObjectFactory
     */

    public Object getObjectInstance ( Object obj , Name name , Context nameCtx ,
            Hashtable environment ) throws Exception
    {
        if ( !(obj instanceof Reference) )
            return null;

        Reference ref = (Reference) obj;
        if ( !ref.getClassName ()
                .equals ( "com.atomikos.jdbc.JtaDataSourceImp" ) )
            return null;
        // as required by JNDI

        String url = (String) ref.get ( "ResourceName" ).getContent ();

        return JtaDataSourceImp.getInstance ( url );

    }

}
