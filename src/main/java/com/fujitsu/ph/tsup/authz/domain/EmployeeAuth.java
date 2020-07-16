package com.fujitsu.ph.tsup.authz.domain;

import java.util.Set;

import org.springframework.data.annotation.Id;

/**
 * It is a class of Employee Authorization
 * 
 * @author j.macabudbud
 *
 */
public class EmployeeAuth {
	@Id
	private Long id;
	private String username;
	private Set<String> authzSet;

	protected EmployeeAuth() {
	}

	public static class Builder {
		private Long id;
		private String username;
		private Set<String> authzSet;

		/**
		 * Creates a new instance of Builder for creating an employee auth. It validates
		 * and sets the argument into the Builder instance variables. This method is
		 * used for creating an employee auth.
		 * 
		 * @param username
		 * @param authzSet
		 */
		public Builder(String username, Set<String> authzSet) {
			validateUsername(username);
			validateRole(authzSet);
			this.username = username;
			this.authzSet = authzSet;
		}

		/**
		 * Creates a new instance of employee authorization Builder. Validates and sets
		 * the argument into the Builder instance variables. This method is used for
		 * setting the data from the database
		 * 
		 * @param id
		 * @param username
		 * @param authzSet
		 */
		public Builder(Long id, String username, Set<String> authzSet) {
			validateId(id);
			validateUsername(username);
			validateRole(authzSet);
			this.id = id;
			this.username = username;
			this.authzSet = authzSet;
		}

		/**
		 * Validates Employee Auth Id
		 * 
		 * @param id
		 */
		private void validateId(Long id) {
			if (id == null || id == 0) {
				throw new IllegalArgumentException("Id should not be empty");
			}

		}

		/**
		 * Validates authorization
		 * 
		 * @param authzSet
		 */
		private void validateRole(Set<String> authzSet) {
			if (authzSet == null || authzSet.isEmpty() || authzSet.size() == 0) {
				throw new IllegalArgumentException("Authorization should not be empty");
			}

		}

		/**
		 * Validates username
		 * 
		 * @param username
		 */
		private void validateUsername(String username) {
			if (username == null || username.isEmpty() || username.length() == 0) {
				throw new IllegalArgumentException("Username should not be empty");
			}

		}

		public EmployeeAuth build() {
			return new EmployeeAuth(this);
		}
	}

	/**
	 * 
	 * @param builder
	 */
	private EmployeeAuth(Builder builder) {
		this.authzSet = builder.authzSet;
		this.username = builder.username;
		this.id = builder.id;
	}

	public Long getId() {
		if (id == null || id.toString().isEmpty() || id == 0) {
			throw new IllegalArgumentException("Id should not be empty");
		}
		return id;
	}

	public Set<String> getAuthzSet() {
		if (authzSet == null || authzSet.isEmpty() || authzSet.size() == 0) {
			throw new IllegalArgumentException("Authorization should not be empty");
		}
		return authzSet;
	}

	public String getUsername() {
		if (username == null || username.isEmpty() || username.length() == 0) {
			throw new IllegalArgumentException("Username should not be empty");
		}
		return username;
	}

	@Override
	public String toString() {
		return "EmployeeAuth [id=" + id + ", username=" + username + ", authzSet=" + authzSet + "]";
	}

}
