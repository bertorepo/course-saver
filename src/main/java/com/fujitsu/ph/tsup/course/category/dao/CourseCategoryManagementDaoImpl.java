/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
//=======================================================
/**
* <pre>
* The implementation of Course Category Management Dao
* 
* <pre>
* 
* @version 0.05
* @author a.batongbacal
* @author r.rivero
* @author j.lira
* @author r.piloto
*
*/
@Repository
public class CourseCategoryManagementDaoImpl implements CourseCategoryManagementDao{

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public void updateCourseCategory(CourseCategory courseCategory) {       
        try {
            String query = "UPDATE COURSE_CATEGORY SET category=:category, detail=:detail WHERE ID =:id";
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("id", courseCategory.getId()).addValue("category", courseCategory.getCategory())
                    .addValue("detail", courseCategory.getDetail());
            template.update(query, sqlParameterSource);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }    

    // Method for searching course categories by category
    @Override
    public Set<CourseCategory> findCourseCategoryByName(String category) {

        String query = "SELECT * FROM COURSE_CATEGORY  WHERE LOWER(category) LIKE LOWER('%" + category + "%')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", category);
        List<CourseCategory> courseCategoryList = template.query(query, sqlParameterSource,
                new CourseCategoryRowMapper());
        Set<CourseCategory> courseCategory = new LinkedHashSet<>(courseCategoryList);
        return courseCategory;
    }

    // Method for creating course categories
    @Override
    public void createCourseCategory(CourseCategory courseCategory) {
        try {
            System.out.println("DAOIMPL");
            String query = "INSERT INTO course_category" + "(category, detail)"
                    + " VALUES(:category, :detail)";
            SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                    .addValue("category", courseCategory.getCategory())
                    .addValue("detail", courseCategory.getDetail());

            template.update(query, sqlParameterSource);
        } catch (DuplicateKeyException ex) {
            throw new DuplicateKeyException(ex.getMessage());
        }
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
