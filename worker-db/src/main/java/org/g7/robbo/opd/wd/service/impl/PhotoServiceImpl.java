package org.g7.robbo.opd.wd.service.impl;

import org.g7.robbo.opd.wd.repo.PhotoRepo;
import org.g7.robbo.opd.wd.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Orlov Diga
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private final PhotoRepo photoRepo;

    public PhotoServiceImpl(PhotoRepo photoRepo) {
        this.photoRepo = photoRepo;
    }
}
