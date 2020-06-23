package com.fujitsu.ph.tsup.authz.infrastructure;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
/**
 * 
 * @author j.macabudbud
 *
 */
public interface EmployeeAuthRepository extends CrudRepository<EmployeeAuth, Long> {
    @Query(value = "SELECT T_ACCESS.id, T_ACCESS.access_id, access_type "
    		+ "FROM M_EMPLOYEE "
    		+ "INNER JOIN T_ACCESS ON T_ACCESS.access_id = M_EMPLOYEE.access_id"
    		+ "WHERE username = :username "
    		)
    List<String> findByUsername(@Param("username") String username);
}
