package com.programming.serialization;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
// immutable period class.
public class Period implements Serializable{

	private static final long serialVersionUID = 1L;
    private  Date start;
    private  Date end;
	
    //Defensive copy
    public   Period(Date start, Date end)
    {
 	   this.start=new Date (start.getTime());
 	   this.end=new Date (end.getTime());
 	   
    }
    
    public Date getStart() {
    	
    	return new Date(start.getTime());
    }
    
    public Date getEnd() {
    	
    	return new Date(end.getTime());
    }
    // Synchronized can be used.
   private void readObject(ObjectInputStream e ) throws ClassNotFoundException, IOException{
        e.defaultReadObject();
        
        // s.readObject() or s.readInt()
 	   start = new Date(start.getTime());
 	   end = new Date(end.getTime());
 	   
 	   if(start.compareTo(end) > 0)
 		   throw new InvalidObjectException("start > end");
 }

   // Synchronized can be used.
 private void writeObject(ObjectOutputStream e) throws IOException{
    e.defaultWriteObject(); // using default write object.
    
    // s.writeInt(2) or s.writeObject();
   
 }

}
