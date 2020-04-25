package org.g7.robbo.opd.fgs.workerdb.service;

import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


/**
 * @author Orlov Diga
 */
public interface EmployeeService{

    public List<Employee> findAll();

    public Employee saveEmployee(Employee employee);

    public Set<Employee> saveEmployeeList(Set<Employee> employees);
}
