/**
 * 
 */
package com.fujitsu.ph.tsup.course.category.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.fujitsu.ph.tsup.course.category.model.CourseCategory;

/**
 * @author a.batongbaca
 * @version creation: 0.01 Date: 2021-02-08
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

}
