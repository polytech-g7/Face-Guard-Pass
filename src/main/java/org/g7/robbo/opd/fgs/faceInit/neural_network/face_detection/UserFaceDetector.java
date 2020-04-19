package org.g7.robbo.opd.fgs.faceInit.neural_network.face_detection;


import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FaceRecognizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

public class UserFaceDetector {
    private static final String LOADER_URL = "save_session/haarcascade_frontalcatface.xml";
    public static final int IMAGE_WIDTH = 160;
    public static final int IMAGE_HEIGHT = 170;
    public static final int INITIAL_WIDTH = 250;
    public static final int INITIAL_HEIGHT = 250;

    private static UserFaceDetector instance;
    private UserFaceAligner userFaceAligner;
    private CascadeClassifier faceDetector;


    private UserFaceDetector() {
        userFaceAligner = UserFaceAligner.create();
        URL loaderUrl = getClass().getClassLoader().getResource(LOADER_URL);
        if (loaderUrl == null) throw new IllegalStateException("No cascade file.");

        faceDetector = new CascadeClassifier(loaderUrl.getPath());
    }


    private MatFace cropMatFaceFromImage(File image) {
        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
        RectVector faces = detectFaces(matImage);

        System.out.println(image.getName() + " found faces = " + faces.size());
        if (faces.size() == 0) {
            return null;
        }

        Rect rect = faces.get(0);

        return cropAlignAndResizeFace(matImage, rect);
    }


    private MatFace cropAlignAndResizeFace(Mat image, Rect rect) {
        MatFace response = userFaceAligner.align(image, rect);

        if (response == null || response.face == null) {
            System.out.println("-------Couldn't align faces.-------");
            return null;
        }

        int centerX = response.face.size().width() / 2;
        int centerY = response.face.size().height() / 2;

        Rect staticSizeRect = new Rect(
                centerX - IMAGE_WIDTH / 2,
                centerY - IMAGE_HEIGHT / 2,
                IMAGE_WIDTH, IMAGE_HEIGHT
        );

        Mat alignedFace = new Mat(response.face, staticSizeRect);
        return new MatFace(alignedFace, response.faceType);
    }


    /**
     * Finds all faces on given image
     */
    public Face[] getAllFacesFromImage(File image) {
        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
        RectVector foundFaces = detectFaces(matImage);

        List<Face> alignedFaces = new ArrayList<>();
        System.out.println("FOUND FACES = " + foundFaces.size());

        for (int i = 0; i < foundFaces.size(); i++) {
            Rect frame = foundFaces.get(i);

            MatFace matFace = cropAlignAndResizeFace(matImage, frame);

            //we were able to locate landmarks
            if (matFace != null) {
                BufferedImage croppedFace = matToImage(matFace.face);

                //assign face as well as the face type
                alignedFaces.add(new Face(frame.x(), frame.y(),
                        frame.width(), frame.height(),
                        matFace.faceType, croppedFace));
            }
        }

        return alignedFaces.toArray(new Face[alignedFaces.size()]);
    }


    /**
     * @return list of eyes in form of {x left, y left, x right, y right}
     */
    public List<double[]> getEyesCoordinates(File image) {
        Mat imageMat = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
        RectVector foundFaces = detectFaces(imageMat);

        if (foundFaces.size() == 0) {
            return null;
        }

        List<double[]> eyesCoords = new ArrayList<>();
        for (int i = 0; i < foundFaces.size(); i++) {
            Point2d[] eyesCenters = userFaceAligner.getEyesCenterCoordinates(imageMat, foundFaces.get(i));

            if (eyesCenters != null) {
                double[] eyes = new double[4];
                eyes[0] = eyesCenters[0].x();
                eyes[1] = eyesCenters[0].y();
                eyes[2] = eyesCenters[1].x();
                eyes[3] = eyesCenters[1].y();

                eyesCoords.add(eyes);
            }
        }

        return eyesCoords;
    }


    public Face[] getFacesCoordinates(File image) {
        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
        RectVector foundFaces = detectFaces(matImage);

        Face[] faces = new Face[(int) foundFaces.size()];
        for (int i = 0; i < foundFaces.size(); i++) {
            Rect frame = foundFaces.get(i);

            faces[i] = new Face(
                   frame.x(), frame.y(),
                   frame.width(), frame.height(),
                   -1,
                   null);
        }

        return faces;
    }


    /**
     * Finds face on image and crops it.
     * If several faces are found then it'll take a random one.
     *
     * @return cropped and greyed image 160x170px
     */
    public BufferedFace cropFaceFromImage(File image) {
        if (image == null) {
            throw new IllegalArgumentException("image is null");
        }

        MatFace response = cropMatFaceFromImage(image);
        if (response == null) {
            return null;
        }

        return new BufferedFace(matToImage(response.face), response.faceType);
    }


    /**
     * Finds all faces on image and marks it with a rectangle.
     *
     * @return image with marked faces
     */
    public BufferedImage highlightFacesOnImage(File image) {
        if (image == null) {
            throw new IllegalArgumentException("image is null");
        }

        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_ANYCOLOR);
        Mat matImageGreyscale = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_GRAYSCALE);
        RectVector faces = detectFaces(matImage);

        for (int i = 0; i < faces.size(); i++) {
            Point2d[] eyes = userFaceAligner.getEyesCenterCoordinates(matImageGreyscale, faces.get(i));
            if (eyes != null) {
                highlightFrame(matImage, eyes[0]);
                highlightFrame(matImage, eyes[1]);
            }

            highlightFrame(matImage, faces.get(i));
        }

        return matToImage(matImage);
    }


    /**
     * Creates rectangle and text with probability for detected image
     *
     * @return input image with colored rectangles and probabilities
     */
    public BufferedImage drawFaceDetection(File image, Face[] faces, FaceRecognizer.Prediction[] prediction) {
        if (faces.length != prediction.length) {
            return null;
        }

        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_ANYCOLOR);

        for (int i = 0; i < faces.length; i++) {
            drawFaceDetection(matImage, faces[i], prediction[i]);
        }

        return matToImage(matImage);
    }


    private void drawFaceDetection(Mat image, Face face, FaceRecognizer.Prediction prediction) {
        String label;
        Scalar color;
        float offsetScale;

        if (prediction.isIdentified()) {
            label = prediction.getUsername() + ";prob=" +
                    String.format("%.2f%%", prediction.getPercentage());
            color = Scalar.RED;
            offsetScale = 0.75f;
        } else {
            label = "unknown";
            color = Scalar.BLUE;
            offsetScale = 0.25f;
        }


        putTextOnImage(image, label,
                face.getLeftTopX() - (int) (face.getLeftTopX() * offsetScale),
                face.getLeftTopY() - 15
        );

        highlightFrame(image, new Rect(
                face.getLeftTopX(), face.getLeftTopY(),
                face.getWidth(), face.getHeight()
        ), color);
    }


    public BufferedImage drawColoredText(File image, String text, int pointX, int pointY) {
        Mat matImage = imread(image.getAbsolutePath(), CV_LOAD_IMAGE_ANYCOLOR);
        putTextOnImage(matImage, text, pointX, pointY);

        return matToImage(matImage);
    }


    private void putTextOnImage(Mat matImage, String text, int pointX, int pointY) {
        putText(matImage, text,
                new Point(pointX, pointY), FONT_HERSHEY_COMPLEX, 1.5, Scalar.BLUE, 1, CV_AA, false);
    }


    private void highlightFrame(Mat image, Rect rect) {
        rectangle(image, rect, Scalar.RED, 3, 5, 0);
    }


    private void highlightFrame(Mat image, Point2d point) {
        Rect rect = new Rect((int) point.x(), (int) point.y(), 15, 15);
        highlightFrame(image, rect, Scalar.MAGENTA);
    }


    private void highlightFrame(Mat image, Rect rect, Scalar color) {
        rectangle(image, rect, color, 3, 5, 0);
    }


    private BufferedImage matToImage(Mat image) {
        if (image == null) {
            return null;
        }

        byte[] data = new byte[image.size().width() * image.size().height()];
        imencode(".jpg", image, data);

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bufferedImage;
    }


    // runs 20-30ms what is very good
    private Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat;

        if (bi.getType() == BufferedImage.TYPE_BYTE_GRAY) {
            mat = new Mat(bi.getHeight(), bi.getWidth(), CV_8UC1);
        } else {
            mat = new Mat(bi.getHeight(), bi.getWidth(), CV_8UC3);
        }

        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.data().put(data);

        return mat;
    }


    private RectVector detectFaces(Mat image) {
        if (image == null) {
            throw new IllegalArgumentException("no image provided");
        }

        RectVector faces = new RectVector();
        faceDetector.detectMultiScale(image, faces);

        RectVector filteredFaces = new RectVector();

        //filter faces to exclude wrong occurrences
        for (int i = 0; i < faces.size(); i++) {
            if (faces.get(i).width() >= 50
                    && faces.get(i).height() >= 50) {
                Rect faceRect = new Rect(
                        faces.get(i).x(),
                        faces.get(i).y(),
                        faces.get(i).width(),
                        faces.get(i).height()
                );

                filteredFaces.push_back(faceRect);
            }
        }

        return filteredFaces;
    }


    public static UserFaceDetector create() {
        if (instance == null) {
            instance = new UserFaceDetector();
        }

        return instance;
    }
}
