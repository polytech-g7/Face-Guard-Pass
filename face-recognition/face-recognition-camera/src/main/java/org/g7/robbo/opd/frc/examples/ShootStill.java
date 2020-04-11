package org.g7.robbo.opd.frc.examples;



import org.g7.robbo.opd.frc.RPiCamera;
import org.g7.robbo.opd.frc.enums.AWB;
import org.g7.robbo.opd.frc.enums.DRC;
import org.g7.robbo.opd.frc.enums.Encoding;
import org.g7.robbo.opd.frc.exceptions.FailedToRunRaspistillException;

import java.io.File;
import java.io.IOException;

/**
 * ShootStill is an example of how to take a still image using JRPiCam.
 * 
 * @author Andrew Dillon
 */
public class ShootStill {

	public static void main(String[] args) {
		RPiCamera piCamera = null;
		// Attempt to create an instance of RPiCamera, will fail if raspistill is not properly installed
		try {
			String saveDir = "/home/pi/Desktop";
			piCamera = new RPiCamera(saveDir);
		} catch (FailedToRunRaspistillException e) {
			e.printStackTrace();
		}
		// Take a still image and save it
		if (piCamera != null)
			shootStill(piCamera);
	}
	
	public static void shootStill(RPiCamera piCamera) {
		piCamera.setAWB(AWB.AUTO) 	    // Change Automatic White Balance setting to automatic
			.setDRC(DRC.OFF) 			// Turn off Dynamic Range Compression
			.setContrast(100) 			// Set maximum contrast
			.setSharpness(100)		    // Set maximum sharpness
			.setQuality(100) 		    // Set maximum quality
			.setTimeout(1000)		    // Wait 1 second to take the image
			.turnOnPreview()            // Turn on image preview
			.setEncoding(Encoding.PNG); // Change encoding of images to PNG
		// Take a 650x650 still image and save it as "/home/pi/Desktop/A Cool Picture.png"
		try {
			File image = piCamera.takeStill("A Cool Picture.png", 650, 650);
			System.out.println("New PNG image saved to:\n\t" + image.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
