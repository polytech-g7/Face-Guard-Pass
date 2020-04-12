package org.g7.robbo.opd.fgs;

import org.g7.robbo.opd.fgs.camera.CameraAdapter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author Orlov Diga
 */
public class Application {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:config/ioadapter.xml");
    }

    static class CameraAdapter {

        public CameraAdapter() {
        }

        //in FaceRecognitionAdapter.getPhoto
        public String sendPhoto() {
            return "src/main/resources/example/31.JPG";
        }
    }

    static class MessageProducer {
        public String produce() {
            String[] array = {"first line!", "second line!", "third line!"};
            return array[new Random().nextInt(3)];
        }
    }

    static class MessageConsumer {
        public void consume(String message) {
            System.out.println(message);
        }
    }

}
