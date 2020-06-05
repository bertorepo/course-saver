package com.fujitsu.ph.tsup.domain.balanon;

import java.util.Set;

public interface EmployeeService {
    void save(Employee EmployeeId);

    Set<Course> findall();

    Course findById(Long EmployeeId);

}
