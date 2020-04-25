package org.g7.robbo.opd.fgs.camera;

import org.g7.robbo.opd.fgs.camera.enums.AWB;
import org.g7.robbo.opd.fgs.camera.enums.DRC;
import org.g7.robbo.opd.fgs.camera.enums.Encoding;
import org.g7.robbo.opd.fgs.workerdb.StartInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Orlov Diga
 */
@Component
@EnableScheduling
public class CameraAdapter {

    private static final String SAVE_DIR = "";

    private static Logger LOG = LoggerFactory.getLogger(StartInit.class);

  /*  public CameraAdapter() {
    }*/

    private StartInit startInit;

    //private RPiCamera piCamera;

    @Autowired
    public CameraAdapter(StartInit startInit) {
        this.startInit = startInit;
    }



    @PostConstruct
    public void init() {
        LOG.info("Init method Camera Adapter start...");
        /*piCamera.setAWB(AWB.AUTO) 	    // Change Automatic White Balance setting to automatic
                .setDRC(DRC.OFF) 			// Turn off Dynamic Range Compression
                .setContrast(100) 			// Set maximum contrast
                .setSharpness(100)		    // Set maximum sharpness
                .setQuality(100) 		    // Set maximum quality
                .setTimeout(1000)		    // Wait 1 second to take the image
                .turnOnPreview()            // Turn on image preview
                .setEncoding(Encoding.PNG); // Change encoding of images to PNG*/

    }

    @Scheduled(fixedRate = 2000)
    public void sendPhoto() throws IOException, InterruptedException {
        LOG.info("Send photo start...");

        String namePhoto = "photo"+ LocalDateTime.now() + ".png";

        File image = new File("example/dimCheck.jpg");
        //File image = piCamera.takeStill(namePhoto, 650, 650);

        startInit.getPhoto(image);

        LOG.info("Send photo finish");

        //return namePhoto;
        LOG.info(LocalDateTime.now() + "");
        System.out.println(LocalDateTime.now());

    }
}
