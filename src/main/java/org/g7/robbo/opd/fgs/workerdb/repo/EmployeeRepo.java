package org.g7.robbo.opd.fgs.workerdb.repo;


import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Orlov Diga
 */
@Repository
public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    @Transactional
    List<Employee> findAll();
}
