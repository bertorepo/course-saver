/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
 *
 */
public class CourseCategoryManagementDaoImpl implements CourseCategoryManagementDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Method for finding Course Category by Id
     */
    @Override
    public CourseCategory findCourseCategoryById(Long id) {

        String query = "SELECT id,category,detail FROM COURSE_CATEGORY WHERE ID =" + id;

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);

        return template.queryForObject(query, sqlParameterSource, new CourseCategoryRowMapper());
    }

    /**
     * Method for deleting Course Category by Id
     */
    @Override
    public void deleteCourseCategoryById(Long id) {

        String query = "DELETE FROM COURSE_CATEGORY WHERE ID =" + id;

        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);
        template.update(query, namedParameters);

    }

}
