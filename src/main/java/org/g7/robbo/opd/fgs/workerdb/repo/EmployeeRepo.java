package org.g7.robbo.opd.fgs.workerdb.repo;


import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Orlov Diga
 */
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
