package org.g7.robbo.opd.fgs.workerdb;

import org.g7.robbo.opd.fgs.faceInit.neural_network.face_detection.Face;
import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FaceRecognizer;
import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FullFaceFeatures;
import org.g7.robbo.opd.fgs.faceRecognition.JFaceDetection;
import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.g7.robbo.opd.fgs.workerdb.entity.Photo;
import org.g7.robbo.opd.fgs.workerdb.service.EmployeeService;
import org.g7.robbo.opd.fgs.workerdb.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.FIND_ALL;
import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.METHOD_CALL;

/**
 * @author Orlov Diga
 */
@Component
public class StartInit {

    private FaceRecognizer faceRecognizer;

    private final EmployeeService employeeService;

    public FullFaceFeatures[] employeeFaces;

    private static Logger LOG = LoggerFactory.getLogger(StartInit.class);

    @Autowired
    public StartInit(EmployeeService employeeService) {
        LOG.info("Start initialization neural network");

        this.faceRecognizer = FaceRecognizer.create();
        this.employeeService = employeeService;
    }

    @PostConstruct
    public void neuralNetworkInit() {
        LOG.info(METHOD_CALL, "neuralNetworkInit");

        FaceRecognizer faceRecognizer = FaceRecognizer.create();

        List<Employee> employees = employeeService.findAll();

        LOG.info(FIND_ALL, employees.size(), Employee.class);

       employeeFaces = employees.stream()
                                .map(e -> faceRecognizer.calculateFullFeaturesForUser(e.getName(), castPhotoPathsToFiles(e)).left)
                                .toArray(FullFaceFeatures[]::new);

    }


    /**
    * test writing photo
    * */
    public void getPhoto(File image) throws IOException {
        BufferedImage res = faceRecognizer.identifyUsersOnPhoto(image, employeeFaces);

        if (res != null) {
            File file = new File("example/MYPIS1" + 111 + ".jpg");
            ImageIO.write(res, "jpg", file);
        }
    }

    private File[] castPhotoPathsToFiles(Employee employee) {
        return employee.getPhoto()
                .stream()
                .map(photo -> new File(photo.getPath())).toArray(File[]::new);
    }
}
