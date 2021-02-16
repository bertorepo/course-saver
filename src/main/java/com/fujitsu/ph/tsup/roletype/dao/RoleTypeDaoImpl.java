package com.fujitsu.ph.tsup.roletype.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.enrollment.dao.EnrollmentRowMapperTopLearner;
import com.fujitsu.ph.tsup.enrollment.model.TopLearnerForm;
import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01   | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02   | 2021/02/16 | WS) s.labador         | Updated
//==================================================================================================

/**
 * <pre>
 * RoleTypeDaoImp class
 * 
 * <pre>
 * 
 * @version 0.01
 * @author rl.naval
 */
@Repository
public class RoleTypeDaoImpl implements RoleTypeDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Method for finding Role by Id
     * @param id Role id
     */
    @Override
    public RoleType findRoleById(Long id) {
        String query = "SELECT id, role_type, role_desc FROM MEMBER_ROLE where ID = " + id;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, sqlParameterSource, new RoleTypeRowMapper());
    }

    /**
     * Method for finding Role by name
     * @param rolename Role name
     */
    @Override
    public Set<RoleType> findRoleTypeByName(String rolename) {
        String query = "SELECT * FROM MEMBER_ROLE WHERE LOWER(role_type) LIKE LOWER('%" + rolename + "%')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("role_type", rolename);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }

    /**
     * Load all role types
     * @return roles
     */
    @Override
    public Set<RoleType> loadAllRoleType() {
        String query = "SELECT * FROM MEMBER_ROLE";

        List<RoleType> roleList = template.query(query, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);

        return roles;
    }

    /**
     * Method for deleting Role by Id
     * @param id Role id
     */
    @Override
    public void deleteRoleTypeById(Long id) {
        String query = "DELETE FROM MEMBER_ROLE WHERE ID = " + id;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        template.update(query, sqlParameterSource);

    }

    /**
     * Method for creating role type
     * @param role Role type
     */
    @Override
    public void createRoleType(RoleType role) {
        String query = "INSERT INTO MEMBER_ROLE" + "(role_type, role_desc)"
                + "VALUES (:rolename , :roledesc)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("rolename", role.getRolename()).addValue("roledesc", role.getRoledesc());

        template.update(query, sqlParameterSource);
    }

    /**
     * Method for updating role type
     * @param roletype Role type
     */
    @Override
    public void updateRoleType(RoleType roleType) {
        String query = "UPDATE MEMBER_ROLE " + "SET role_name = '', role_desc = '' " + "WHERE id = "
                + roleType.getId() + ";";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("role_name", roleType.getRolename()).addValue("role_desc", roleType.getRoledesc());

        template.update(query, sqlParameterSource);

    }
    
    public List<RoleType> getRoleTypeByPage(int pageid, int total){
        String query = "SELECT * FROM MEMBER_ROLE" + (pageid-1)+","+total;
        List<RoleType> roleList = template.query(query, new RoleTypeRowMapper());
        
        return roleList;
        
    }

    @Override
    public Set<RoleType> findRoleTypeByKeyword(String keyword) {
        String query = "SELECT * FROM MEMBER_ROLE "
                + "WHERE LOWER(role_type) LIKE LOWER('%" + keyword + "%') "
                + "OR LOWER(role_desc) LIKE LOWER('%" + keyword + "%')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("role_type", keyword)
                .addValue("role_desc", keyword);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }

}
