package org.g7.robbo.opd.fgs.faceRecognition;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Orlov Diga
 */
public class FaceRecognitionAdapter {

    @Autowired
    private JFaceDetection faceDetection;

    //out CameraAdapter.sendPhoto
    public void getPhoto(String photoPath) {
        faceDetection.findFaces(photoPath);
    }

    public String sendFace(String pathFacePhoto) {
        return pathFacePhoto;
    }
}
