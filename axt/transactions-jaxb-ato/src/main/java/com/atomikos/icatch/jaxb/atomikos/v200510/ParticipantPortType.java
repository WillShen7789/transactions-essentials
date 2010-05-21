
package com.atomikos.icatch.jaxb.atomikos.v200510;

import javax.jws.WebParam.Mode;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding;
import javax.jws.Oneway;
import javax.jws.WebMethod;

/**
 * This class was generated by the CXF 2.0-incubator
 * Wed Jul 11 17:38:06 CEST 2007
 * Generated source version: 2.0-incubator
 * 
 */

@WebService(targetNamespace = "http://www.atomikos.com/schemas/2005/10/transactions", name = "ParticipantPortType")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)

public interface ParticipantPortType {

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @WebMethod
    public void prepare(
        @WebParam(targetNamespace = "http://www.atomikos.com/schemas/2005/10/transactions", partName = "PrepareRequestContent", name = "Prepare")
        com.atomikos.icatch.jaxb.atomikos.v200510.PrepareMessageType prepareRequestContent
    );

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @WebMethod
    public void rollback(
        @WebParam(targetNamespace = "http://www.atomikos.com/schemas/2005/10/transactions", partName = "RollbackContent", name = "Rollback")
        com.atomikos.icatch.jaxb.atomikos.v200510.TransactionMessageType rollbackContent
    );

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @WebMethod
    public void commit(
        @WebParam(targetNamespace = "http://www.atomikos.com/schemas/2005/10/transactions", partName = "CommitContent", name = "Commit")
        com.atomikos.icatch.jaxb.atomikos.v200510.CommitMessageType commitContent
    );

    @SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
    @Oneway
    @WebMethod
    public void forget(
        @WebParam(targetNamespace = "http://www.atomikos.com/schemas/2005/10/transactions", partName = "ForgetContent", name = "Forget")
        com.atomikos.icatch.jaxb.atomikos.v200510.TransactionMessageType forgetContent
    );
}
