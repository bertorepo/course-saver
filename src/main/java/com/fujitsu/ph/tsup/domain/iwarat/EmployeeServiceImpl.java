package com.fujitsu.ph.tsup.domain.iwarat;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao edao;

    @Override
    public void save(Employee employee) {
        try {
            edao.save(employee);
        } catch (IllegalArgumentException e) {
            throw new  MyException("Error found", e);
        }
    }

    @Override
    public Set<Employee> findAll() {
        try {
            return edao.findAll();
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }

    @Override
    public Employee findById(Long id) {
        try {
            return edao.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MyException("Error found", e);
        }
    }
}
