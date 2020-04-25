package org.g7.robbo.opd.fgs.workerdb;

import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FaceRecognizer;
import org.g7.robbo.opd.fgs.faceRecognition.JFaceDetection;
import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.g7.robbo.opd.fgs.workerdb.entity.Photo;
import org.g7.robbo.opd.fgs.workerdb.service.EmployeeService;
import org.g7.robbo.opd.fgs.workerdb.service.PhotoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Set;

import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.FIND_ALL;
import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.METHOD_CALL;

/**
 * @author Orlov Diga
 */
public class StartInit {

    private final FaceRecognizer faceRecognizer;

    private final EmployeeService employeeService;

    private static Logger LOG = LoggerFactory.getLogger(StartInit.class);

    @Autowired
    public StartInit(FaceRecognizer faceRecognizer, EmployeeService employeeService) {
        LOG.info("Start initialization neural network");

        this.faceRecognizer = faceRecognizer;
        this.employeeService = employeeService;

        neuralNetworkInit();
    }
    private void neuralNetworkInit() {
        LOG.info(METHOD_CALL, "neuralNetworkInit");

        List<Employee> employees = employeeService.findAll();

        LOG.info(FIND_ALL, employees.size(), Employee.class);

        employees.forEach(e -> faceRecognizer.calculateFullFeaturesForUser(e.getName(), castPhotoPathsToFiles(e)));
    }
    private File[] castPhotoPathsToFiles(Employee employee) {
        return (File[]) employee.getPhoto()
                .stream()
                .map(photo -> new File(photo.getPath())).toArray();
    }


}
