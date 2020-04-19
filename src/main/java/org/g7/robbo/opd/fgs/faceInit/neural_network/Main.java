package org.g7.robbo.opd.fgs.faceInit.neural_network;



import org.g7.robbo.opd.fgs.faceInit.neural_network.face_detection.Face;
import org.g7.robbo.opd.fgs.faceInit.neural_network.face_detection.UserFaceDetector;
import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FaceRecognizer;
import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FullFaceFeatures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FaceRecognizer faceRecognizer = FaceRecognizer.create();
        UserFaceDetector faceDetector = UserFaceDetector.create();

        Face.Response<FullFaceFeatures, String> f1 = faceRecognizer.calculateFullFeaturesForUser("dima", new File("example/dimCentre.jpg"), new File("example/dim6.jpg"), new File("example/dimLeft3.jpg"), new File("example/dimLeft4.jpg"), new File("example/dimLeft2.jpg"));

        FullFaceFeatures[] arr = {f1.left};

        String[] arrImg = {"example/dimCheck.jpg"};
        for (int i = 0; i < arrImg.length; i++) {
            BufferedImage res = faceRecognizer.identifyUsersOnPhoto(new File(arrImg[i]), arr);
            if (res != null) {
                File file = new File("example/MYPIS1" + i + ".jpg");
                ImageIO.write(res, "jpg", file);
            }
        }

    //   example/jb-cropped.jpg - лицо
        //git remote add origin remote repository
    }
}
