package org.g7.robbo.opd.wd.service.impl;

import org.g7.robbo.opd.wd.repo.EmployeeRepo;
import org.g7.robbo.opd.wd.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Orlov Diga
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private final EmployeeRepo employeeRepo;


    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
}
