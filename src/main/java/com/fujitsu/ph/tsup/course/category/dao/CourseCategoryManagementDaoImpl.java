package com.fujitsu.ph.tsup.course.category.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

//==================================================================================================
//$Id:PR10$
//Project Name : Training Sign up
//System Name  : Course Category Management Process
//Class Name   : CourseCategoryManagementDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By                                                | Content
//--------+------------+---------------------------------------------------------+-----------------
//0.01    | 06/26/2020 | WS) A.Batongbacal, WS) G.Cabiling, WS) Z.De Guia, WS) J.Lira, WS) R.Piloto, R.Rivero, WS) J.Zamora    | New Creation
//==================================================================================================
/**
 * <pre>
* The data access class for course category related database access
 * </pre>
 *
 * @version 0.01
 * @author a.batongbacal
 * @author g.cabiling
 * @author z.deguia
 * @author j.lira
 * @author r.piloto
 * @author r.rivero
 * @author j.zamora
 *
 */

@Repository
public class CourseCategoryManagementDaoImpl implements CourseCategoryManagementDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

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
