package com.atomikos.beans;
import java.io.Serializable;

public class TestIntegerBean
implements Serializable
{
    private int value;
    
  public TestIntegerBean()
  {
      setValue ( 0 ); 
  }
  
  public void setValue ( int val )
  {
      value = val; 
  }
  
  public int getValue()
  {   
      return value;
  } 
}
