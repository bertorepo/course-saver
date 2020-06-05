package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface EmployeeDao {

    void save(Employee EmployeeId);

    Set<Course> findall();

    Course findById(Long EmployeeId);

}
