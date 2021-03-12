package com.fujitsu.ph.tsup.roletype.dao;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.fujitsu.ph.tsup.roletype.domain.RoleType;

//==================================================================================================
//Project Name : Training Sign Up
//System Name  : Role Type Management
//Class Name   : RoleTypeDaoImpl.java
//
//<<Modification History>>
//Version | Date       | Updated By            | Content
//--------+------------+-----------------------+---------------------------------------------------
//0.01    | 2021/02/05 | WS) rl.naval          | Initial Version
//0.02    | 2021/02/16 | WS) s.labador         | Updated
//0.03    | 2021/02/17 | WS) j.sayaboc         | Updated
//0.04    | 2021/02/22 | WS) j.sayaboc         | Updated
//0.05    | 2021/02/24 | WS) p.cui             | Updated
//0.06    | 2021/02/26 | WS) c.sinda           | Updated
//0.07    | 2021/03/03 | WS) m.baton		   | Updated
//0.08    | 2021/03/09 | WS) j.sayaboc         | Updated
//0.09    | 2021/03/11 | WS) p.cui             | Updated
//0.10    | 2021/03/12 | WS) i.fajardo         | Updated
//==================================================================================================


/**
 * RoleTypeDaoImpl class
 * 
 * @version 0.10
 * @author rl.naval
 * @author s.labador
 * @author p.cui
 * @author j.sayaboc
 * @author c.sinda
 * @author m.baton
 * @author i.fajardo
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
        String query = "SELECT * FROM MEMBER_ROLE WHERE LOWER(role_type) LIKE LOWER('%" + rolename + "%') AND deleted_at IS NULL";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("role_type", rolename);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }

    /**
     * Find if Role name is already existing
     * 
     * @param rolename Role name
     * @param id Role id
     * @return roles
     */
    @Override
    public Set<RoleType> findIfRoleNameExists(String rolename, Long id) {
        String query = "SELECT * FROM MEMBER_ROLE WHERE LOWER(role_type) LIKE LOWER('" + rolename
                + "') AND id NOT IN (" + id + ") AND deleted_at IS NULL";
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
        String query = "SELECT id, role_type, role_desc, deleted_at  FROM MEMBER_ROLE  WHERE deleted_at IS NULL";

        List<RoleType> roleList = template.query(query, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);

        return roles;
    }

    /**
     * Load all role types with pagination
     * @param pageSize
     * @param page
     * @return roles
     */
    @Override
    public Set<RoleType> loadAllRoleType(int pageSize, int page) {
        String query = "SELECT id, role_type, role_desc, deleted_at  FROM MEMBER_ROLE WHERE deleted_at IS NULL  LIMIT "+ pageSize+" OFFSET "+ ((pageSize*page)-pageSize);
        
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
        String query = "UPDATE MEMBER_ROLE SET deleted_at = now() WHERE ID = " + id;
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
     * Method for updating Role Type
     * 
     * @param id Role id
     * @param roleType RoleType
     */

    @Override
    public void updateRoleType(Long id, RoleType roleType) {
        String query = "UPDATE MEMBER_ROLE " + "SET role_type = '" + roleType.getRolename()
                + "', role_desc = '" + roleType.getRoledesc() + "' " + "WHERE id = " + id + ";";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("roletype", roleType.getRolename()).addValue("roledesc", roleType.getRoledesc());
        template.update(query, sqlParameterSource);
    }

    /**
     * Method for searching Role Type
     * 
     * @param keyword search keyword
     */
    @Override
    public Set<RoleType> findRoleTypeByKeyword(String keyword) {
        String query = "SELECT * FROM MEMBER_ROLE " + "WHERE LOWER(role_type) LIKE LOWER('%" + keyword
                + "%') " + "OR LOWER(role_desc) LIKE LOWER('%" + keyword + "%')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("role_type", keyword)
                .addValue("role_desc", keyword);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }
    
    /**
     * Method for searching Role Type
     * 
     * @param keyword search keyword
     * @param pageSize
     * @param page
     */
    @Override
    public Set<RoleType> findRoleTypeByKeyword(String keyword, int pageSize, int page) {
        String query = "SELECT * FROM MEMBER_ROLE " + "WHERE LOWER(role_type) LIKE LOWER('%" + keyword
                + "%') " + "OR LOWER(role_desc) LIKE LOWER('%" + keyword + "%') LIMIT "+ pageSize+" OFFSET "+ ((pageSize*page)-pageSize);
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("role_type", keyword)
                .addValue("role_desc", keyword);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }

}