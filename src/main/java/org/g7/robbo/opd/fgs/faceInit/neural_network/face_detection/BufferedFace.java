package org.g7.robbo.opd.fgs.faceInit.neural_network.face_detection;

import java.awt.image.BufferedImage;

public class BufferedFace {
    public BufferedImage face;
    public int faceType;

    public BufferedFace(BufferedImage face, int faceType) {
        this.face = face;
        this.faceType = faceType;
    }
}
