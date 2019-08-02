package com.epiuse.recruiting;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "module")
public class Module {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
  private int id;
	private Integer isdistinction;
	private Double markRecieved;
	private String modulecode;
	private String moduledescription;
	private Integer snumber;
	
	@ManyToOne(optional = false , fetch = FetchType.LAZY)
	@JoinColumn(name = "snumber-fk")
	private Student stud;
	
	
	// ... getters 
	
	public int getID() {
		return id;
	}
	public int getIsdistinction() 
	{
		return isdistinction;
	}
	
	public double getmarkRecieved() {
		return markRecieved;
	}
	public String getmodulecode() {
		return modulecode;
	}
	public String getmoduledescription() {
		return moduledescription;
	}
	public int getsnumber() {
		return snumber;
	}
	//...setters
	public void stID(int newID) {
		this.id = newID;
	}
	
	public void setIsdistinction(int  newisdistinction) {
		this.isdistinction = newisdistinction;
	}
	public void setmarkRecieved(double newmarkRecieved) {
		this.markRecieved= newmarkRecieved;
	}
	
	public void setmodulecode(String newmodulecode) {
		this.modulecode = newmodulecode;
	}
	public void setmoduledescription(String newmoduledescription) {
		this.moduledescription=newmoduledescription;
	}
	public void setsnumber(int newsnumber) {
		this.snumber = newsnumber;
	}
	
	//.. hashcode 
	   @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	       // result = prime * result + ((id == null) ? 0 : id.hashCode());
	        result = prime * result + ((isdistinction == null) ? 0 : isdistinction.hashCode());
	        result = prime * result + ((markRecieved == null) ? 0 : markRecieved.hashCode());
	        result = prime * result + ((modulecode == null) ? 0 : modulecode.hashCode());
	        result = prime * result + ((moduledescription == null) ? 0 : moduledescription.hashCode());
	        result = prime * result + ((snumber == null) ? 0 : snumber.hashCode());
	        return result;
	    }
	
	  //equals override
	   @Override
	    public boolean equals(Object obj) {
	        if (this == obj)
	            return true;
	        if (obj == null)
	            return false;
	        if (getClass() != obj.getClass())
	            return false;
	        Module other = (Module) obj;
	     //  if (id == null) {
	       //     if (other.id != null)
	         //       return false;
	       // } else if (!id.equals(other.id))
	         //   return false;
	        if (isdistinction == null) {
	            if (other.isdistinction != null)
	                return false;
	        } else if (!isdistinction.equals(other.isdistinction))
	            return false;
	        if (markRecieved == null) {
	            if (other.markRecieved != null)
	                return false;
	        } else if (!markRecieved.equals(other.markRecieved))
	            return false;
	        if (modulecode == null) {
	            if (other.modulecode != null)
	                return false;
	        } else if (!modulecode.equals(other.modulecode))
	            return false;
	        if (moduledescription == null) {
	            if (other.moduledescription != null)
	                return false;
	        } else if (!moduledescription.equals(other.moduledescription))
	            return false;
	        if (snumber == null) {
	            if (other.snumber != null)
	                return false;
	        } else if (!snumber.equals(other.snumber))
	            return false;
	        return true;
	    }
	   
}


