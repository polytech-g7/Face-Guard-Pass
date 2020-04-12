package org.g7.robbo.opd.fgs.faceRecognition;

import org.g7.robbo.opd.fgs.faceInit.FaceInitAdapter;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author Orlov Diga
 */
public class JFaceDetection {

    private CascadeClassifier cc;

    @Autowired
    FaceRecognitionAdapter faceRecognitionAdapter;

    public JFaceDetection() {
        nu.pattern.OpenCV.loadShared();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String xmlFile = "src/main/resources/xml/lbpcascade_frontalface.xml";
        CascadeClassifier cc = new CascadeClassifier(xmlFile);
    }

    public void findFaces() {
        String imgFile = "src/main/resources/example/31.JPG";
        Mat src = Imgcodecs.imread(imgFile);

        MatOfRect faceDetection = new MatOfRect();
        cc.detectMultiScale(src, faceDetection);
        System.out.println(String.format("Detected faces: %d", faceDetection.toArray().length));


        for (Rect rect: faceDetection.toArray()) {
            Imgproc.rectangle(src, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 3);
            Rect rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);

            Mat markedImage = new Mat(src,rectCrop);

            String path = "src/main/resources/example/crop" + UUID.randomUUID() + ".jpg";

            Imgcodecs.imwrite(path ,markedImage );

            faceRecognitionAdapter.sendFace(path);
        }

        Imgcodecs.imwrite("src/main/resources/example/dJgDqTSs94c_out.jpg", src);
        System.out.println("Image Detection Finished");
        Mat mat = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println("Done");
    }

    public static void main(String[] args) {

    }
}
