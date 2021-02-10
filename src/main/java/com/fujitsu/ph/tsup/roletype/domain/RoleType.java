/*
 * Copyright (C) 2021 FUJITSU LIMITED All rights reserved.
 */
package com.fujitsu.ph.tsup.roletype.domain;

/**
 * RoleType class
 * 
 * @author rl.naval (New Creation by: rl.naval)
 * @version Revision: 0.01 Date: 2021-02-05
 */

public class RoleType {
	private Long id;
	private String rolename;
	private String roledesc;
	
	protected RoleType() {
		
	}
	
	private RoleType(Builder builder) {
		this.id = builder.id;
		this.rolename = builder.rolename;
		this.roledesc = builder.roledesc;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public static class Builder{
		private Long id;
		private String rolename;
		private String roledesc;
		
		public Builder(String rolename) {
			validateRolename(rolename);
			this.rolename = rolename;
		}
		
		public Builder(Long id, String rolename) {
			validateId(id);
			validateRolename(rolename);
			this.id = id;
			this.rolename = rolename;
		}
		
		public Builder(String rolename, String roledesc) {
			validateRolename(rolename);
			validateRoleDesc(roledesc);
			
			this.rolename = rolename;
			this.roledesc = roledesc;
		}
		
		public Builder roledesc(String roledesc) {
			validateRoleDesc(roledesc);
			this.roledesc = roledesc;
			
			return this;
		}
		
		public RoleType build() {
			return new RoleType(this);
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getRolename() {
			return rolename;
		}
		public void setRolename(String rolename) {
			this.rolename = rolename;
		}
		public String getRoledesc() {
			return roledesc;
		}
		public void setRoledesc(String roledesc) {
			this.roledesc = roledesc;
		}
		
		private void validateRolename(String rolename) {
			if (rolename.equals(null) || rolename.isEmpty()) {

                throw new IllegalArgumentException(
                        "Role name should not be empty");

            }
		}
		
		private void validateId(Long id) {

            if (id == null || id == 0) {

                throw new IllegalArgumentException("Id should not be empty");

            }
        }
		
		private void validateRoleDesc(String roledesc) {

            if (roledesc.equals(null) || roledesc.isEmpty()) {

                throw new IllegalArgumentException(
                        "Role Description should not be empty");

            }

        }
	}
	
}
