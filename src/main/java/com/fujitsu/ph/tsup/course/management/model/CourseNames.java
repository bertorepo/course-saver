/* Author : Angara, Mary Rose */
package com.fujitsu.ph.tsup.course.management.model;

public class CourseNames {
	
	private String name;
	private int id;

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	@Override
	public String toString() 
	{
		return "CN [id=" + id + ", name=" + name + "]";
	}

}
