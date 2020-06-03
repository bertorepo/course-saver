/* @Author Kenneth Abad */
package com.fujitsu.ph.tsup.venue.management.model;


import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class VenueDeleteForm {
	private int idnumber;
	private String vname;
	
	
	public int getIdnumber() {
		return idnumber;
	}


	public void setIdnumber(int idnumber) {
		this.idnumber = idnumber;
	}


	public String getVname() {
		return vname;
	}


	public void setVname(String vname) {
		this.vname = vname;
	}


	@Override
	public String toString() {
		return "VenueNames [idnumber=" + idnumber + ", vname=" + vname +"]";
	}

}
