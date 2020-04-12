package org.g7.robbo.opd.fgs.workerdb.repo;


import org.g7.robbo.opd.fgs.workerdb.entity.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Orlov Diga
 */
@Repository
public interface PhotoRepo extends CrudRepository<Photo, Long> {
}
