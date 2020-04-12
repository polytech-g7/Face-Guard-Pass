package org.g7.robbo.opd.fgs.faceRecognition;

import org.g7.robbo.opd.fgs.faceInit.FaceInitAdapter;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author Orlov Diga
 */
public class JFaceDetection {

    private CascadeClassifier cc;

    private static final String PATH_FACE_IMG = "src/main/resources/example/";

    private static final String TYPE_FACE_PHOTO = ".jpg";

    private static final String NAME_FACE_PHOTO_WITHOUT_UUID = "crop";

    private static Logger LOGGER = LoggerFactory.getLogger(JFaceDetection.class);

    @Autowired
    FaceRecognitionAdapter faceRecognitionAdapter;

    public JFaceDetection() {
        LOGGER.debug("Start init method for JFaceDetection...");

        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String xmlFile = "src/main/resources/xml/lbpcascade_frontalface.xml";
        cc = new CascadeClassifier(xmlFile);

        LOGGER.debug("Finish init method for JFaceDetection.");
    }

    public void findFaces(String photoPath) {
        LOGGER.debug("Start method findFaces with photo path {}", photoPath);

        Mat src = Imgcodecs.imread(photoPath);

        MatOfRect faceDetection = new MatOfRect();
        cc.detectMultiScale(src, faceDetection);

        LOGGER.debug("Detected faces: {}", faceDetection.toArray().length);

        for (Rect rect: faceDetection.toArray()) {
            Imgproc.rectangle(
                    src,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255), 3);

            Rect rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);

            Mat markedImage = new Mat(src,rectCrop);

            String path = PATH_FACE_IMG + NAME_FACE_PHOTO_WITHOUT_UUID + UUID.randomUUID() + TYPE_FACE_PHOTO;

            Imgcodecs.imwrite(path ,markedImage );

            faceRecognitionAdapter.sendFace(path);
        }

        Imgcodecs.imwrite("src/main/resources/example/dJgDqTSs94c_out.jpg", src);
        System.out.println("Image Detection Finished");
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("Done");
    }

  /*  public static void main(String[] args) {
        String imgFile = "src/main/resources/example/31.JPG";
        JFaceDetection d = new JFaceDetection();
        d.findFaces(imgFile);
    }*/
}
