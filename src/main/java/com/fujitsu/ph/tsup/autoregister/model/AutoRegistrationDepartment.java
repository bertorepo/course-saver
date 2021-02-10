package com.fujitsu.ph.tsup.autoregister.model;

public class AutoRegistrationDepartment {
	private Long id;
	private String department;
	
	protected AutoRegistrationDepartment() {
		
	}
	
	private AutoRegistrationDepartment(Builder builder) {
        this.id = builder.id;
        this.department = builder.department;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "AutoRegistrationDepartment [id=" + id + ", department=" + department + "]";
	}
	
	public static class Builder {
        private Long id;
        private String department;

        public Builder(Long id, String department) {
            validateId(id);
            validateDepartment(department);

            this.id = id;
            this.department = department;
        }

        public AutoRegistrationDepartment build() {
            return new AutoRegistrationDepartment(this);
        }
        
        private void validateId(Long id) {
            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id should not be empty");
            }
        }

        private void validateDepartment(String department) {
            if (department.equals(null) || department.isEmpty()) {
                throw new IllegalArgumentException(
                        "Department should not be empty");
            }
        }

    }
	
	
}
