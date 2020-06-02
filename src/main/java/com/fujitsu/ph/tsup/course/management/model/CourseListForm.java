package com.fujitsu.ph.tsup.course.management.model;

import java.util.Set;

public class CourseListForm {
	//get and set coursenames
	private Set<CourseNames> CNs;
	public Set<CourseNames> getCNs() 
	{
		return CNs;
	}
	public void setCNs(Set<CourseNames> CNs) 
	{
		this.CNs = CNs;
	}
	@Override
	public String toString() {
		return "CourseListForm [CNs=" + CNs +"]";
	}

}
