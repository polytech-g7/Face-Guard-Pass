package org.g7.robbo.opd.wd.repo;

import org.g7.robbo.opd.wd.entity.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Orlov Diga
 */
public interface EmployeeRepo extends CrudRepository<Employee, Long> {
}
