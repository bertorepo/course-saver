/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//=======================================================
//$Id: PR10$
//Project Name: Training Sign Up
//System Name : Course Category Management Process
//Class Name: CourseCategoryManagementDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated by          | Content
//--------+------------+---------------------+---------------
//0.01    | 02/08/2020 | WS) A.Batongbacal   | New Creation
//0.02    | 02/15/2020 | WS) A.Batongbacal   | Update
//0.03    | 02/24/2020 | WS) R.Rivero        | Update
//0.04    | 02/24/2020 | WS) J.ira           | Update
//0.05    | 02/24/2020 | WS) R.Piloto        | Update
//0.06    | 06/16/2021 | WS) M.Aguinaldo     | Removed unnecessary imports
//=======================================================
/**
 * <pre>
 * The implementation of Course Category Management Dao
 * 
 * <pre>
 * 
 * @version 0.06
 * @author a.batongbacal
 * @author r.rivero
 * @author j.lira
 * @author r.piloto
 * @author mi.aguinaldo
 *
 */
@Repository
public class CourseCategoryManagementDaoImpl implements CourseCategoryManagementDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseCategoryManagementDaoImpl.class);
    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void updateCourseCategory(CourseCategory courseCategory) {
        String query = "UPDATE COURSE_CATEGORY SET category=:category, detail=:detail WHERE ID =:id";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("id", courseCategory.getId()).addValue("category", courseCategory.getCategory())
                .addValue("detail", courseCategory.getDetail());
        int result = template.update(query, sqlParameterSource);
        if (result > 0) {
            LOGGER.debug("Successfully updated course category ID: " + courseCategory.getId());
        } else {
            LOGGER.debug("Unable to update course category");
        }
    }

    // Method for searching course categories by category
    @Override
    public Set<CourseCategory> findCourseCategoryByName(String category) {
        String query = "SELECT * FROM COURSE_CATEGORY  WHERE LOWER(category) LIKE LOWER('%" + category + "%') ORDER BY category";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", category);
        List<CourseCategory> courseCategoryList = template.query(query, sqlParameterSource,
                new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);
        return courseCategory;
    }

    // Method for creating course categories
    @Override
    public void createCourseCategory(CourseCategory courseCategory) {
                   
        String query = "INSERT INTO course_category" + "(category, detail)"
                + " VALUES(:category, :detail)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("category", courseCategory.getCategory())
                .addValue("detail", courseCategory.getDetail());

        template.update(query, sqlParameterSource);
     }
    
    @Override
    public Set<CourseCategory> findAllCourseCategory() {

        String query = "SELECT * FROM COURSE_CATEGORY ORDER BY category";
        List<CourseCategory> courseCategoryList = template.query(query, new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);
        return courseCategory;
    }

    /**
     * Method for finding Course Category by Id
     */
    @Override
    public CourseCategory findCourseCategoryById(Long id) {

        String query = "SELECT * FROM COURSE_CATEGORY WHERE ID =" + id;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        List<CourseCategory> courseCategoryList = template.query(query, sqlParameterSource,
                new CourseCategoryRowMapper());

        if (!courseCategoryList.isEmpty()) {
            return courseCategoryList.get(0);
        } else {
            return null;
        }
        
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