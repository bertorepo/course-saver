package com.fujitsu.ph.tsup.authz.infrastructure;

import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.fujitsu.ph.tsup.authz.core.model.EmployeeAuth;
/**
 * It is an interface class for Employee Authorization list
 * @author j.macabudbud
 *
 */
public interface EmployeeAuthRepository extends CrudRepository<EmployeeAuth, Long> {
    @Query(value = "SELECT AUTH_NAME FROM EMPLOYEE WHERE USERNAME = :username ")
    List<String> findByUsername(@Param("username") String username);
}
