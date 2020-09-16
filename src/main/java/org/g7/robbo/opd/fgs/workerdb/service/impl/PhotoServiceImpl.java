package org.g7.robbo.opd.fgs.workerdb.service.impl;

import org.g7.robbo.opd.fgs.workerdb.entity.Photo;
import org.g7.robbo.opd.fgs.workerdb.repo.PhotoRepo;
import org.g7.robbo.opd.fgs.workerdb.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author Orlov Diga
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepo photoRepo;

    @Autowired
    public PhotoServiceImpl(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }

    @Override
    public Photo savePhoto(final Photo photo) {
        if (photo != null) {
            photo.setCreationTime(LocalDateTime.now());

            return photoRepo.save(photo);
        }

        return null;
    }

    @Override
    public Set<Photo> savePhotos(final Set<Photo> photos) {
        if (photos != null && !photos.isEmpty()) {
            photos.forEach(photo -> photo.setCreationTime(LocalDateTime.now()));

            return (Set<Photo>) photoRepo.saveAll(photos);
        }

        return null;
    }
}
