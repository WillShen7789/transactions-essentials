package com.atomikos.icatch.trmi;

public final class TrmiTransactionManager_Stub
    extends java.rmi.server.RemoteStub
    implements com.atomikos.icatch.trmi.CompositeTransactionServer, com.atomikos.icatch.trmi.ParticipantServer, com.atomikos.icatch.trmi.RecoveryServer, java.rmi.Remote
{
    private static final java.rmi.server.Operation[] operations = {
	new java.rmi.server.Operation("com.atomikos.icatch.trmi.RecoveryCoordinatorProxy addParticipant(com.atomikos.icatch.Participant, java.lang.String)"),
	new java.rmi.server.Operation("void addSubTxAwareParticipant(com.atomikos.icatch.SubTxAwareParticipant, java.lang.String)"),
	new java.rmi.server.Operation("com.atomikos.icatch.HeuristicMessage commit(java.lang.String)[]"),
	new java.rmi.server.Operation("com.atomikos.icatch.HeuristicMessage commitOnePhase(java.lang.String, int, java.util.Dictionary)[]"),
	new java.rmi.server.Operation("void forget(java.lang.String)"),
	new java.rmi.server.Operation("int prepare(java.lang.String, int, java.util.Dictionary)"),
	new java.rmi.server.Operation("java.lang.Boolean replayCompletion(java.lang.String, com.atomikos.icatch.Participant)"),
	new java.rmi.server.Operation("com.atomikos.icatch.HeuristicMessage rollback(java.lang.String)[]")
    };
    
    private static final long interfaceHash = 9008997189527120110L;
    
    private static final long serialVersionUID = 2;
    
    private static boolean useNewInvoke;
    private static java.lang.reflect.Method $method_addParticipant_0;
    private static java.lang.reflect.Method $method_addSubTxAwareParticipant_1;
    private static java.lang.reflect.Method $method_commit_2;
    private static java.lang.reflect.Method $method_commitOnePhase_3;
    private static java.lang.reflect.Method $method_forget_4;
    private static java.lang.reflect.Method $method_prepare_5;
    private static java.lang.reflect.Method $method_replayCompletion_6;
    private static java.lang.reflect.Method $method_rollback_7;
    
    static {
	try {
	    java.rmi.server.RemoteRef.class.getMethod("invoke",
		new java.lang.Class[] {
		    java.rmi.Remote.class,
		    java.lang.reflect.Method.class,
		    java.lang.Object[].class,
		    long.class
		});
	    useNewInvoke = true;
	    $method_addParticipant_0 = com.atomikos.icatch.trmi.CompositeTransactionServer.class.getMethod("addParticipant", new java.lang.Class[] {com.atomikos.icatch.Participant.class, java.lang.String.class});
	    $method_addSubTxAwareParticipant_1 = com.atomikos.icatch.trmi.CompositeTransactionServer.class.getMethod("addSubTxAwareParticipant", new java.lang.Class[] {com.atomikos.icatch.SubTxAwareParticipant.class, java.lang.String.class});
	    $method_commit_2 = com.atomikos.icatch.trmi.ParticipantServer.class.getMethod("commit", new java.lang.Class[] {java.lang.String.class});
	    $method_commitOnePhase_3 = com.atomikos.icatch.trmi.ParticipantServer.class.getMethod("commitOnePhase", new java.lang.Class[] {java.lang.String.class, int.class, java.util.Dictionary.class});
	    $method_forget_4 = com.atomikos.icatch.trmi.ParticipantServer.class.getMethod("forget", new java.lang.Class[] {java.lang.String.class});
	    $method_prepare_5 = com.atomikos.icatch.trmi.ParticipantServer.class.getMethod("prepare", new java.lang.Class[] {java.lang.String.class, int.class, java.util.Dictionary.class});
	    $method_replayCompletion_6 = com.atomikos.icatch.trmi.RecoveryServer.class.getMethod("replayCompletion", new java.lang.Class[] {java.lang.String.class, com.atomikos.icatch.Participant.class});
	    $method_rollback_7 = com.atomikos.icatch.trmi.ParticipantServer.class.getMethod("rollback", new java.lang.Class[] {java.lang.String.class});
	} catch (java.lang.NoSuchMethodException e) {
	    useNewInvoke = false;
	}
    }
    
    // constructors
    public TrmiTransactionManager_Stub() {
	super();
    }
    public TrmiTransactionManager_Stub(java.rmi.server.RemoteRef ref) {
	super(ref);
    }
    
    // methods from remote interfaces
    
    // implementation of addParticipant(Participant, String)
    public com.atomikos.icatch.trmi.RecoveryCoordinatorProxy addParticipant(com.atomikos.icatch.Participant $param_Participant_1, java.lang.String $param_String_2)
	throws com.atomikos.icatch.SysException, java.lang.IllegalStateException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_addParticipant_0, new java.lang.Object[] {$param_Participant_1, $param_String_2}, -7498011041286730506L);
		return ((com.atomikos.icatch.trmi.RecoveryCoordinatorProxy) $result);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 0, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_Participant_1);
		    out.writeObject($param_String_2);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		com.atomikos.icatch.trmi.RecoveryCoordinatorProxy $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = (com.atomikos.icatch.trmi.RecoveryCoordinatorProxy) in.readObject();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} catch (java.lang.ClassNotFoundException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of addSubTxAwareParticipant(SubTxAwareParticipant, String)
    public void addSubTxAwareParticipant(com.atomikos.icatch.SubTxAwareParticipant $param_SubTxAwareParticipant_1, java.lang.String $param_String_2)
	throws com.atomikos.icatch.SysException, java.lang.IllegalStateException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		ref.invoke(this, $method_addSubTxAwareParticipant_1, new java.lang.Object[] {$param_SubTxAwareParticipant_1, $param_String_2}, -8438551629400399220L);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 1, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_SubTxAwareParticipant_1);
		    out.writeObject($param_String_2);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		ref.done(call);
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of commit(String)
    public com.atomikos.icatch.HeuristicMessage[] commit(java.lang.String $param_String_1)
	throws com.atomikos.icatch.HeurHazardException, com.atomikos.icatch.HeurMixedException, com.atomikos.icatch.HeurRollbackException, com.atomikos.icatch.RollbackException, com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_commit_2, new java.lang.Object[] {$param_String_1}, -6785758951217652594L);
		return ((com.atomikos.icatch.HeuristicMessage[]) $result);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 2, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		com.atomikos.icatch.HeuristicMessage[] $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = (com.atomikos.icatch.HeuristicMessage[]) in.readObject();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} catch (java.lang.ClassNotFoundException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurHazardException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurMixedException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurRollbackException e) {
	    throw e;
	} catch (com.atomikos.icatch.RollbackException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of commitOnePhase(String, int, Dictionary)
    public com.atomikos.icatch.HeuristicMessage[] commitOnePhase(java.lang.String $param_String_1, int $param_int_2, java.util.Dictionary $param_Dictionary_3)
	throws com.atomikos.icatch.HeurHazardException, com.atomikos.icatch.HeurMixedException, com.atomikos.icatch.HeurRollbackException, com.atomikos.icatch.RollbackException, com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_commitOnePhase_3, new java.lang.Object[] {$param_String_1, new java.lang.Integer($param_int_2), $param_Dictionary_3}, -3829391615912805741L);
		return ((com.atomikos.icatch.HeuristicMessage[]) $result);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 3, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		    out.writeInt($param_int_2);
		    out.writeObject($param_Dictionary_3);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		com.atomikos.icatch.HeuristicMessage[] $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = (com.atomikos.icatch.HeuristicMessage[]) in.readObject();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} catch (java.lang.ClassNotFoundException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurHazardException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurMixedException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurRollbackException e) {
	    throw e;
	} catch (com.atomikos.icatch.RollbackException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of forget(String)
    public void forget(java.lang.String $param_String_1)
	throws com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		ref.invoke(this, $method_forget_4, new java.lang.Object[] {$param_String_1}, -6793985004451225327L);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 4, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		ref.done(call);
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of prepare(String, int, Dictionary)
    public int prepare(java.lang.String $param_String_1, int $param_int_2, java.util.Dictionary $param_Dictionary_3)
	throws com.atomikos.icatch.HeurHazardException, com.atomikos.icatch.HeurMixedException, com.atomikos.icatch.RollbackException, com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_prepare_5, new java.lang.Object[] {$param_String_1, new java.lang.Integer($param_int_2), $param_Dictionary_3}, 5010699337017937512L);
		return ((java.lang.Integer) $result).intValue();
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 5, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		    out.writeInt($param_int_2);
		    out.writeObject($param_Dictionary_3);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		int $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = in.readInt();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurHazardException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurMixedException e) {
	    throw e;
	} catch (com.atomikos.icatch.RollbackException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of replayCompletion(String, Participant)
    public java.lang.Boolean replayCompletion(java.lang.String $param_String_1, com.atomikos.icatch.Participant $param_Participant_2)
	throws com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_replayCompletion_6, new java.lang.Object[] {$param_String_1, $param_Participant_2}, 2313171612230415664L);
		return ((java.lang.Boolean) $result);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 6, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		    out.writeObject($param_Participant_2);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		java.lang.Boolean $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = (java.lang.Boolean) in.readObject();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} catch (java.lang.ClassNotFoundException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
    
    // implementation of rollback(String)
    public com.atomikos.icatch.HeuristicMessage[] rollback(java.lang.String $param_String_1)
	throws com.atomikos.icatch.HeurCommitException, com.atomikos.icatch.HeurHazardException, com.atomikos.icatch.HeurMixedException, com.atomikos.icatch.SysException, java.rmi.RemoteException
    {
	try {
	    if (useNewInvoke) {
		Object $result = ref.invoke(this, $method_rollback_7, new java.lang.Object[] {$param_String_1}, -3797690192234522552L);
		return ((com.atomikos.icatch.HeuristicMessage[]) $result);
	    } else {
		java.rmi.server.RemoteCall call = ref.newCall((java.rmi.server.RemoteObject) this, operations, 7, interfaceHash);
		try {
		    java.io.ObjectOutput out = call.getOutputStream();
		    out.writeObject($param_String_1);
		} catch (java.io.IOException e) {
		    throw new java.rmi.MarshalException("error marshalling arguments", e);
		}
		ref.invoke(call);
		com.atomikos.icatch.HeuristicMessage[] $result;
		try {
		    java.io.ObjectInput in = call.getInputStream();
		    $result = (com.atomikos.icatch.HeuristicMessage[]) in.readObject();
		} catch (java.io.IOException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} catch (java.lang.ClassNotFoundException e) {
		    throw new java.rmi.UnmarshalException("error unmarshalling return", e);
		} finally {
		    ref.done(call);
		}
		return $result;
	    }
	} catch (java.lang.RuntimeException e) {
	    throw e;
	} catch (java.rmi.RemoteException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurCommitException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurHazardException e) {
	    throw e;
	} catch (com.atomikos.icatch.HeurMixedException e) {
	    throw e;
	} catch (java.lang.Exception e) {
	    throw new java.rmi.UnexpectedException("undeclared checked exception", e);
	}
    }
}
