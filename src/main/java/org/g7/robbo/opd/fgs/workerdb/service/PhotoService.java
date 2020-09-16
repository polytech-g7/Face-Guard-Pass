package org.g7.robbo.opd.fgs.workerdb.service;

import org.g7.robbo.opd.fgs.workerdb.entity.Photo;

import java.util.Set;

/**
 * @author Orlov Diga
 */
public interface PhotoService {

    public Photo savePhoto(Photo photo);

    public Set<Photo> savePhotos(Set<Photo> photos);
}
