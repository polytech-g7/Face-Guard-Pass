package org.g7.robbo.opd.fgs.camera;

import org.slf4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author Orlov Diga
 */
@EnableScheduling
public class CameraAdapter {

    public CameraAdapter() {
    }

    @Scheduled(fixedRate = 500)
    //in FaceRecognitionAdapter.getPhoto
    public String sendPhoto() {
        return "src/main/resources/example/31.JPG";
    }
}
