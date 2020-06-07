package com.fujitsu.ph.tsup.domain.macabugao;

public class Course {
    private Long id;
    private String courseName;


    protected Course(long l, String string) {

    }

    private Course(Builder builder) {
        this.id = builder.id;
        this.courseName = builder.courseName;
    }

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setId(Long id) {
		this.id = id;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public static class Builder {
        private Long id;
        private String courseName;
        
 
        public Builder(Long id,String courseName) {
        
            validateCourseName(courseName);
           
            this.courseName = courseName;
        }
        
        public Builder(String courseName) {
        	validateCourseName(courseName);
        	   this.courseName = courseName;
        }


        public Course build() {
            return new Course(this);
        }

        
        private void validateCourseName(String courseName) {
            if (courseName == null || courseName.isEmpty() || courseName.length() <1 || courseName.length() > 100) {
                throw new IllegalArgumentException("Course Name should not be empty");
            }

        }
    }

}
