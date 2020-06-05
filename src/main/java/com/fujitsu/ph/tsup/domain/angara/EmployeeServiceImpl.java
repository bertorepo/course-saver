package com.fujitsu.ph.tsup.domain.angara;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import com.fujitsu.ph.tsup.domain.angara.EmployeeDao;

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao dao;

    // check if id matches any id from Employee
    // (employee.getId() != id) throw exception

    @Override
    public void save(Employee employee) {
        dao.save(employee);
        if (employee.getId() == null) {
            throw new IllegalArgumentException("Employee not saved.");
        }
    }

    @Override
    public Set<Employee> findAll() {
        Set<Employee> e = dao.findAll();
        if (e.isEmpty()) {
            throw new IllegalArgumentException("No Records Found.");
        }
        return e;
    }

    @Override
    public Employee findById(Long id) {
      Employee em = dao.findById(id);
      if (em.getId() == 0) {
          throw new IllegalArgumentException("No records found.");
      }
        return em;
    }

}