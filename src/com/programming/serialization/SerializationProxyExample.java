package com.programming.serialization;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Date;

public class SerializationProxyExample implements Serializable{
  
	private static final long serialVersionUID = 1L;
    private final Date start;
    private final Date end;
   
   public   SerializationProxyExample(Date start, Date end)
   {
	   this.start=new Date (start.getTime());
	   this.end=new Date (end.getTime());
	   
   }
   
   private Object writeReplace() {
	   
	   return new SerializationProxy(this);
   }
   
   private void readObject(ObjectInputStream s) throws InvalidObjectException{
	   
	   throw new InvalidObjectException("proxy required");
   }
	/*
	 * static inner class, logical representation of variable
	 */
   private static class SerializationProxy implements Serializable {
	   
	   private static final long serialVersionUID = 1L;
	    private final Date start;
	    private final Date end;
	   
	   public  SerializationProxy(SerializationProxyExample s)
	   {
		   this.start=s.start;
		   this.end=s.end;
		   
	   }
	   // before readresolve , default deserialization will take place.
	   private Object readResolve() {
		   
		   return new SerializationProxyExample(start,end);
	   }
	   
   }
   
}
