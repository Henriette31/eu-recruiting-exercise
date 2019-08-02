package com.epiuse.recruiting;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student")
public class Student {
	
	
		@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
		@NotNull
	private int id;
		@Column(name = "birthday")
	private Date birthdate;
	private String degree;
	private Date entrydate;
	private String firstname;
	private String lastname;
	private Integer year;
	@OneToMany(mappedBy = "studentInfo",orphanRemoval = true , cascade = CascadeType.ALL)
	private List<Module> modules ;

	//... getters

	public int getID() {
		return id;
	}
	public Date getbirthdate() {
		return birthdate;
	}
	public String getdegree() {
		return degree;
	}
	public Date getentrydate() {
		return entrydate;
	}

	public String getfirstname() {
		return firstname;
	}
	public String getlastname() {
		return lastname;
	}
	public int getyear() {
		return year;
	}
	//setters 
	public void setID(int newID) {
		this.id=newID;
	}
	public void setbirthdate (Date newbirthdate) {
		this.birthdate = newbirthdate;
	}
	public void setdegree(String newdegree) {
		this.degree=newdegree;
	}
	public void setfirstname(String newfirstname) {
		this.firstname = newfirstname;
	}
	public void setlastname(String newlastname) {
		this.lastname = newlastname;
	}
	public void setyear(int newyear) {
		this.year = newyear;
	}

	//hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result =1;
		 //result = prime * result + ((id == null) ? 0 : id.hashCode());
	     result = prime * result + ((modules == null) ? 0 : modules.hashCode());
	     result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
	     result = prime * result + ((degree == null) ? 0 : degree.hashCode());
	     result = prime * result + ((entrydate == null) ? 0 : entrydate.hashCode());
	     result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
	     result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
	     result = prime * result + ((year == null) ? 0 : year.hashCode());
	     return result;
	}
	@Override
	//equals
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Student other = (Student) obj ; 
		//if (id == null) {
	      //  if (other.id != null) 
	      //      return false;
	   // } else if (!id.equals(other.id))
	     //   return false;
		
	    if (modules == null) {
	        if (other.modules != null)
	            return false;
	    } else if (!modules.equals(other.modules))
	        return false;
	    
	    if (birthdate == null) {
	        if (other.birthdate != null)
	            return false;
	    } else if (!birthdate.equals(other.birthdate))
	        return false;
	   
	    if (degree == null) {
	        if (other.birthdate != null)
	            return false;
	    } else if (!birthdate.equals(other.birthdate))
	        return false;
	    if (lastname == null) {
	        if (other.lastname != null)
	            return false;
	    } else if (!lastname.equals(other.lastname))
	        return false;
	    
	    if (year==null) {
	        if (other.year != null)
	            return false;
	    } else if (!year.equals(other.year))
	        return false;
	    
	    if (firstname == null) {
	        if (other.firstname != null)
	            return false;
	    } else if (!firstname.equals(other.firstname))
	        return false;
	    
	    if (entrydate == null) {
	        if (other.entrydate != null)
	            return false;
	    } else if (!entrydate.equals(other.entrydate))
	        return false;
	    return true;
	}
	}


