/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

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
//0.02    | 02/24/2020 | WS) R.Rivero        | Update
//=======================================================
/**
* <pre>
* The implementation of Course Category Management Dao
* 
* <pre>
* 
* @version 0.03
* @author a.batongbaca
* @author r.rivero
*
*/
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
    public Set<CourseCategory> findCourseCategoryByName(String name) {

        String query = "SELECT * FROM COURSE_CATEGORY WHERE LOWER(category) LIKE LOWER('" + name + "')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("category", name);
        List<CourseCategory> categoryList = template.query(query, sqlParameterSource,
                new CourseCategoryRowMapper());
        Set<CourseCategory> categories = new LinkedHashSet<>(categoryList);

        return categories;
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
}
