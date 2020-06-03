package com.fujitsu.ph.tsup.domain.ramos;

import java.util.regex.Pattern;
import ch.qos.logback.core.boolex.Matcher;

public class Course {
	private Long id;
	private String courseName;

	protected Course() {
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

	public static class Builder {
		private Long id;
		private String courseName;
		private Pattern pattern;
		
		public Builder(Long id, String courseName) {
			validateCourseName(courseName);
			this.courseName = courseName;
		}

		public Course build() {
			return new Course(this);
		}

		private void validateCourseName(String courseName) {
			Pattern.compile("[a-zA-Z]*");
			java.util.regex.Matcher matcher = pattern.matcher(courseName);
			
			if (courseName == null || courseName.isEmpty() || courseName.length() < 10 || courseName.length() > 50) {
				throw new IllegalArgumentException("Course Name is empty");
			}else if (!matcher.matches()) {
				throw new IllegalArgumentException("Course Name contains special characters");
			}
		}
	}
}
