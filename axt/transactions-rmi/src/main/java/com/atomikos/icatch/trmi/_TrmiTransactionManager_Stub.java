package com.atomikos.icatch.trmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.UnexpectedException;
import java.util.Dictionary;

import javax.rmi.CORBA.Stub;
import javax.rmi.CORBA.Util;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.ApplicationException;
import org.omg.CORBA.portable.RemarshalException;
import org.omg.CORBA.portable.ServantObject;

import com.atomikos.icatch.HeurCommitException;
import com.atomikos.icatch.HeurHazardException;
import com.atomikos.icatch.HeurMixedException;
import com.atomikos.icatch.HeurRollbackException;
import com.atomikos.icatch.HeuristicMessage;
import com.atomikos.icatch.Participant;
import com.atomikos.icatch.RollbackException;
import com.atomikos.icatch.SubTxAwareParticipant;


public class _TrmiTransactionManager_Stub extends Stub implements CompositeTransactionServer,
ParticipantServer,
RecoveryServer {
    
    private static final String[] _type_ids = {
        "RMI:com.atomikos.icatch.trmi.TrmiTransactionManager:61C3B0F66DD2DFFC:2DFC8E467C50A4FD", 
        "RMI:com.atomikos.icatch.trmi.CompositeTransactionServer:0000000000000000", 
        "RMI:com.atomikos.icatch.trmi.ParticipantServer:0000000000000000", 
        "RMI:com.atomikos.icatch.trmi.RecoveryServer:0000000000000000"
    };
    
    public String[] _ids() { 
        return _type_ids;
    }
    
    public RecoveryCoordinatorProxy addParticipant(Participant arg0, String arg1) throws RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("addParticipant", true);
                    out.write_value((Serializable)arg0,Participant.class);
                    out.write_value(arg1,String.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (RecoveryCoordinatorProxy) in.read_value(RecoveryCoordinatorProxy.class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return addParticipant(arg0,arg1);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("addParticipant",TrmiTransactionManager.class);
            if (so == null) {
                return addParticipant(arg0, arg1);
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg1},_orb());
                Participant arg0Copy = (Participant) copies[0];
                String arg1Copy = (String) copies[1];
                RecoveryCoordinatorProxy result = ((TrmiTransactionManager)so.servant).addParticipant(arg0Copy, arg1Copy);
                return (RecoveryCoordinatorProxy)Util.copyObject(result,_orb());
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public void addSubTxAwareParticipant(SubTxAwareParticipant arg0, String arg1) throws RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("addSubTxAwareParticipant", true);
                    out.write_value((Serializable)arg0,SubTxAwareParticipant.class);
                    out.write_value(arg1,String.class);
                    _invoke(out);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    addSubTxAwareParticipant(arg0,arg1);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("addSubTxAwareParticipant",TrmiTransactionManager.class);
            if (so == null) {
                addSubTxAwareParticipant(arg0, arg1);
                return ;
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg1},_orb());
                SubTxAwareParticipant arg0Copy = (SubTxAwareParticipant) copies[0];
                String arg1Copy = (String) copies[1];
                ((TrmiTransactionManager)so.servant).addSubTxAwareParticipant(arg0Copy, arg1Copy);
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public HeuristicMessage[] commit(String arg0) throws HeurHazardException, HeurMixedException, HeurRollbackException, RollbackException, RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("commit", true);
                    out.write_value(arg0,String.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (HeuristicMessage[]) in.read_value(HeuristicMessage[].class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    if (id.equals("IDL:com/atomikos/icatch/HeurHazardEx:1.0")) {
                        throw (HeurHazardException) in.read_value(HeurHazardException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurMixedEx:1.0")) {
                        throw (HeurMixedException) in.read_value(HeurMixedException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurRollbackEx:1.0")) {
                        throw (HeurRollbackException) in.read_value(HeurRollbackException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/RollbackEx:1.0")) {
                        throw (RollbackException) in.read_value(RollbackException.class);
                    }
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return commit(arg0);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("commit",TrmiTransactionManager.class);
            if (so == null) {
                return commit(arg0);
            }
            try {
                HeuristicMessage[] result = ((TrmiTransactionManager)so.servant).commit(arg0);
                return (HeuristicMessage[])Util.copyObject(result,_orb());
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                if (exCopy instanceof HeurHazardException) {
                    throw (HeurHazardException)exCopy;
                }
                if (exCopy instanceof HeurMixedException) {
                    throw (HeurMixedException)exCopy;
                }
                if (exCopy instanceof HeurRollbackException) {
                    throw (HeurRollbackException)exCopy;
                }
                if (exCopy instanceof RollbackException) {
                    throw (RollbackException)exCopy;
                }
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public HeuristicMessage[] commitOnePhase(String arg0, int arg1, Dictionary arg2) throws HeurHazardException, HeurMixedException, HeurRollbackException, RollbackException, RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("commitOnePhase", true);
                    out.write_value(arg0,String.class);
                    out.write_long(arg1);
                    out.write_value((Serializable)arg2,Dictionary.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (HeuristicMessage[]) in.read_value(HeuristicMessage[].class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    if (id.equals("IDL:com/atomikos/icatch/HeurHazardEx:1.0")) {
                        throw (HeurHazardException) in.read_value(HeurHazardException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurMixedEx:1.0")) {
                        throw (HeurMixedException) in.read_value(HeurMixedException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurRollbackEx:1.0")) {
                        throw (HeurRollbackException) in.read_value(HeurRollbackException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/RollbackEx:1.0")) {
                        throw (RollbackException) in.read_value(RollbackException.class);
                    }
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return commitOnePhase(arg0,arg1,arg2);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("commitOnePhase",TrmiTransactionManager.class);
            if (so == null) {
                return commitOnePhase(arg0, arg1, arg2);
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg2},_orb());
                String arg0Copy = (String) copies[0];
                Dictionary arg2Copy = (Dictionary) copies[1];
                HeuristicMessage[] result = ((TrmiTransactionManager)so.servant).commitOnePhase(arg0Copy, arg1, arg2Copy);
                return (HeuristicMessage[])Util.copyObject(result,_orb());
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                if (exCopy instanceof HeurHazardException) {
                    throw (HeurHazardException)exCopy;
                }
                if (exCopy instanceof HeurMixedException) {
                    throw (HeurMixedException)exCopy;
                }
                if (exCopy instanceof HeurRollbackException) {
                    throw (HeurRollbackException)exCopy;
                }
                if (exCopy instanceof RollbackException) {
                    throw (RollbackException)exCopy;
                }
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public void forget(String arg0) throws RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("forget", true);
                    out.write_value(arg0,String.class);
                    _invoke(out);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    forget(arg0);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("forget",TrmiTransactionManager.class);
            if (so == null) {
                forget(arg0);
                return ;
            }
            try {
                ((TrmiTransactionManager)so.servant).forget(arg0);
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public int prepare(String arg0, int arg1, Dictionary arg2) throws HeurHazardException, HeurMixedException, RollbackException, RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("prepare", true);
                    out.write_value(arg0,String.class);
                    out.write_long(arg1);
                    out.write_value((Serializable)arg2,Dictionary.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return in.read_long();
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    if (id.equals("IDL:com/atomikos/icatch/HeurHazardEx:1.0")) {
                        throw (HeurHazardException) in.read_value(HeurHazardException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurMixedEx:1.0")) {
                        throw (HeurMixedException) in.read_value(HeurMixedException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/RollbackEx:1.0")) {
                        throw (RollbackException) in.read_value(RollbackException.class);
                    }
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return prepare(arg0,arg1,arg2);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("prepare",TrmiTransactionManager.class);
            if (so == null) {
                return prepare(arg0, arg1, arg2);
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg2},_orb());
                String arg0Copy = (String) copies[0];
                Dictionary arg2Copy = (Dictionary) copies[1];
                return ((TrmiTransactionManager)so.servant).prepare(arg0Copy, arg1, arg2Copy);
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                if (exCopy instanceof HeurHazardException) {
                    throw (HeurHazardException)exCopy;
                }
                if (exCopy instanceof HeurMixedException) {
                    throw (HeurMixedException)exCopy;
                }
                if (exCopy instanceof RollbackException) {
                    throw (RollbackException)exCopy;
                }
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public HeuristicMessage[] rollback(String arg0) throws HeurCommitException, HeurHazardException, HeurMixedException, RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("rollback", true);
                    out.write_value(arg0,String.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (HeuristicMessage[]) in.read_value(HeuristicMessage[].class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    if (id.equals("IDL:com/atomikos/icatch/HeurCommitEx:1.0")) {
                        throw (HeurCommitException) in.read_value(HeurCommitException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurHazardEx:1.0")) {
                        throw (HeurHazardException) in.read_value(HeurHazardException.class);
                    }
                    if (id.equals("IDL:com/atomikos/icatch/HeurMixedEx:1.0")) {
                        throw (HeurMixedException) in.read_value(HeurMixedException.class);
                    }
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return rollback(arg0);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("rollback",TrmiTransactionManager.class);
            if (so == null) {
                return rollback(arg0);
            }
            try {
                HeuristicMessage[] result = ((TrmiTransactionManager)so.servant).rollback(arg0);
                return (HeuristicMessage[])Util.copyObject(result,_orb());
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                if (exCopy instanceof HeurCommitException) {
                    throw (HeurCommitException)exCopy;
                }
                if (exCopy instanceof HeurHazardException) {
                    throw (HeurHazardException)exCopy;
                }
                if (exCopy instanceof HeurMixedException) {
                    throw (HeurMixedException)exCopy;
                }
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
    
    public Boolean replayCompletion(String arg0, Participant arg1) throws RemoteException {
        if (!Util.isLocal(this)) {
            try {
                org.omg.CORBA_2_3.portable.InputStream in = null;
                try {
                    org.omg.CORBA_2_3.portable.OutputStream out = 
                        (org.omg.CORBA_2_3.portable.OutputStream)
                        _request("replayCompletion", true);
                    out.write_value(arg0,String.class);
                    out.write_value((Serializable)arg1,Participant.class);
                    in = (org.omg.CORBA_2_3.portable.InputStream)_invoke(out);
                    return (Boolean) in.read_value(Boolean.class);
                } catch (ApplicationException ex) {
                    in = (org.omg.CORBA_2_3.portable.InputStream) ex.getInputStream();
                    String id = in.read_string();
                    throw new UnexpectedException(id);
                } catch (RemarshalException ex) {
                    return replayCompletion(arg0,arg1);
                } finally {
                    _releaseReply(in);
                }
            } catch (SystemException ex) {
                throw Util.mapSystemException(ex);
            }
        } else {
            ServantObject so = _servant_preinvoke("replayCompletion",TrmiTransactionManager.class);
            if (so == null) {
                return replayCompletion(arg0, arg1);
            }
            try {
                Object[] copies = Util.copyObjects(new Object[]{arg0,arg1},_orb());
                String arg0Copy = (String) copies[0];
                Participant arg1Copy = (Participant) copies[1];
                Boolean result = ((TrmiTransactionManager)so.servant).replayCompletion(arg0Copy, arg1Copy);
                return (Boolean)Util.copyObject(result,_orb());
            } catch (Throwable ex) {
                Throwable exCopy = (Throwable)Util.copyObject(ex,_orb());
                throw Util.wrapException(exCopy);
            } finally {
                _servant_postinvoke(so);
            }
        }
    }
}
