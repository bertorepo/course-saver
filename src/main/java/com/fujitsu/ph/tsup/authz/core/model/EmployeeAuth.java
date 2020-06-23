package com.fujitsu.ph.tsup.authz.core.model;

import org.springframework.data.annotation.Id;
/**
 * 
 * @author j.macabudbud
 *
 */
public class EmployeeAuth {
	@Id
	private Long id;
	private String username;
	private String role;

	public static class Builder {
		private final String username;
		private final String role;

		private Long id;

		public Builder(String username, String role) {
			validateUsername(username);
			validateRole(role);
			this.username = username;
			this.role = role;
		}

		private void validateRole(String role) {
			if (role == null || role.isEmpty()) {
				throw new IllegalArgumentException("role should have a value");
			}
			
		}

		private void validateUsername(String username) {
			if (username == null || username.isEmpty()) {
				throw new IllegalArgumentException("username should have a value");
			}
			
		}
		
		public EmployeeAuth build() {
			return new EmployeeAuth(this);
		}
	}
	
	protected EmployeeAuth(Builder builder) {
		this.role = builder.role;
		this.username = builder.username;
		this.id = builder.id;
	}
	
	public Long getId() {
		return id;
	}

	public String getRole() {
		return role;
	}

	public String getusername() {
		return username;
	}

	@Override
	public String toString() {
		return "EmployeeAuth [id=" + id + ", username=" + username + ", role=" + role + "]";
	}

}
