package com.fujitsu.ph.tsup.coursemanagement.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.coursemanagement.model.Course;

@Repository
public class CourseManagementDaoImpl implements CourseManagementDao {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public Course findCourseById(Long id) {

        String query = "SELECT id,name,detail FROM COURSE WHERE ID =" + id;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", id);

        return template.queryForObject(query, sqlParameterSource,
                new CourseRowMapper());
    }

    @Override
    public void deleteCourseById(Long id) {

        String query = "DELETE FROM COURSE WHERE ID =" + id;

        SqlParameterSource namedParameters = new MapSqlParameterSource("id",
                id);
        template.update(query, namedParameters);

    }
    
    @Override
    public Set<Course> findCourseByName(String name){
    	
    	String query = "SELECT * FROM COURSE WHERE NAME =" + "'" +name+"'";
    	
    	try {
    		
    		SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("name", name);
        	
    		System.out.println("query : " + query.toString());
        	List<Course> courseList = template.query(query, sqlParameterSource, new CourseRowMapper());
            Set<Course> courses = new HashSet<>(courseList);

            return courses;
    		
    	} catch(EmptyResultDataAccessException e) {
    		
    		return null;
    		
    	}
    	
    }
    
    @Override
    public void createCourse(Course course) {
    	
    	String query = "INSERT INTO course"
    			+ " (name, detail)"
    			+ " VALUES(:name, :detail)";
    	
    	SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
    			.addValue("name", course.getName())
    			.addValue("detail", course.getDetail());
    	
    	template.update(query, sqlParameterSource);
    	
    }

}
