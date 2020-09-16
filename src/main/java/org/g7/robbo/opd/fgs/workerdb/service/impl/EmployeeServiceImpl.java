package org.g7.robbo.opd.fgs.workerdb.service.impl;


import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.g7.robbo.opd.fgs.workerdb.repo.EmployeeRepo;
import org.g7.robbo.opd.fgs.workerdb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * @author Orlov Diga
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee saveEmployee(final Employee employee) {
        assert employee!=null;

        employee.setCreationTime(LocalDateTime.now());

        return employeeRepo.save(employee);
    }

    @Override
    public Set<Employee> saveEmployeeList(Set<Employee> employees) {
        assert employees != null && !employees.isEmpty();

        employees.forEach(e -> e.setCreationTime(LocalDateTime.now()));

        return (Set<Employee>) employeeRepo.saveAll(employees);
    }
}
