package org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying;

public class FullFaceFeatures {
    private FaceFeatures left;
    private FaceFeatures center;
    private FaceFeatures right;

    private String faceLabel;
    private int identifier = 0;


    public FullFaceFeatures(String faceLabel) {
        this.faceLabel = faceLabel;
    }


    public String getFaceLabel() {
        return faceLabel;
    }


    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }


    public int getIdentifier() {
        return identifier;
    }


    public void setFaceFeatures(int faceType, FaceFeatures features) {
        switch (faceType) {
            case FaceFeatures.LEFT_FACE:
                left = features;
                break;
            case FaceFeatures.CENTER_FACE:
                center = features;
                break;
            case FaceFeatures.RIGHT_FACE:
                right = features;
                break;
            default:
                throw new IllegalArgumentException("not expected facetype");
        }
    }

    public FaceFeatures getLeft() {
        return left;
    }

    public FaceFeatures getCenter() {
        return center;
    }

    public FaceFeatures getRight() {
        return right;
    }

    public FaceFeatures getFaceFeatures(int faceType) {
        switch (faceType) {
            case FaceFeatures.LEFT_FACE:
                return left;
            case FaceFeatures.CENTER_FACE:
                return center;
            case FaceFeatures.RIGHT_FACE:
                return right;
            default:
                return null;
        }
    }


    public boolean allFacesAreSet() {
        return left != null && center != null && right != null;
    }
}
