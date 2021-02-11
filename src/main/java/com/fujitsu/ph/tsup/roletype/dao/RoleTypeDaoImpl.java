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

/**
 * RoleTypeDaoImpl class
 * 
 * @author rl.naval (New Creation by: rl.naval)
 * @version Revision: 0.01 Date: 2021-02-05
 */

@Repository
public class RoleTypeDaoImpl implements RoleTypeDao {

    // Call NamedParameterJdbcTemplate
    @Autowired
    private NamedParameterJdbcTemplate template;

    @Override
    public RoleType findRoleById(Long id) {
        String query = "SELECT id, role_type, role_desc FROM MEMBER_ROLE where ID = " + id;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        return template.queryForObject(query, sqlParameterSource, new RoleTypeRowMapper());
    }

    @Override
    public Set<RoleType> findRoleTypeByName(String rolename) {
        String query = "SELECT * FROM MEMBER_ROLE WHERE LOWER(role_type) LIKE LOWER('%" + rolename + "%')";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("role_type", rolename);

        List<RoleType> roleList = template.query(query, sqlParameterSource, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);
        return roles;
    }

    @Override
    public Set<RoleType> loadAllRoleType() {
        String query = "SELECT * FROM MEMBER_ROLE";

        List<RoleType> roleList = template.query(query, new RoleTypeRowMapper());
        Set<RoleType> roles = new LinkedHashSet<>(roleList);

        return roles;
    }

    @Override
    public void deleteRoleTypeById(Long id) {
        String query = "DELETE FROM MEMBER_ROLE WHERE ID = " + id;
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("id", id);
        template.update(query, sqlParameterSource);

    }

    @Override
    public void createRoleType(RoleType role) {
        String query = "INSERT INTO MEMBER_ROLE" + "(role_type, role_desc)"
                + "VALUES (:rolename , :roledesc)";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("rolename", role.getRolename()).addValue("roledesc", role.getRoledesc());

        template.update(query, sqlParameterSource);
    }

    @Override
    public void updateRoleType(RoleType roleType) {
        String query = "UPDATE MEMBER_ROLE " + "SET role_name = '', role_desc = '' " + "WHERE id = "
                + roleType.getId() + ";";

        SqlParameterSource sqlParameterSource = new MapSqlParameterSource()
                .addValue("role_name", roleType.getRolename()).addValue("role_desc", roleType.getRoledesc());

        template.update(query, sqlParameterSource);

    }

}
