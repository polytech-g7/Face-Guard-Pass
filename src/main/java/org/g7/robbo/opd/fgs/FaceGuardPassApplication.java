package org.g7.robbo.opd.fgs;

import org.g7.robbo.opd.fgs.faceInit.neural_network.face_identifying.FaceRecognizer;
import org.g7.robbo.opd.fgs.workerdb.entity.Employee;
import org.g7.robbo.opd.fgs.workerdb.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.FIND_ALL;
import static org.g7.robbo.opd.fgs.l10n.LogEventMessage.METHOD_CALL;

/**
 * @author Orlov Diga
 */
@SpringBootApplication
public class FaceGuardPassApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceGuardPassApplication.class, args);
    }

  /*  @Bean
    @Transactional
    public CommandLineRunner mappingDemo(EmployeeService employeeService) {

        return args -> {
*//*
            Employee employee = new Employee();

            employee.setName("Dima");
            employee.setSurname("Privalov");

            Employee saved = employeeService.saveEmployee(employee);

            Photo photo = new Photo();
            photo.setPath("/Users/macbook/Desktop/IdeaProjects/face-guard-pass/src/main/resources/photo/employee/faces/1/dim6.jpg");
            photo.setEmployee(saved);

            Photo photo1 = new Photo();
            photo1.setPath("/Users/macbook/Desktop/IdeaProjects/face-guard-pass/src/main/resources/photo/employee/faces/1/dimCentre.jpg");
            photo1.setEmployee(saved);

            Photo photo2 = new Photo();
            photo2.setPath("/Users/macbook/Desktop/IdeaProjects/face-guard-pass/src/main/resources/photo/employee/faces/1/dimLeft2.jpg");
            photo2.setEmployee(saved);

            Photo photo3 = new Photo();
            photo3.setPath("/Users/macbook/Desktop/IdeaProjects/face-guard-pass/src/main/resources/photo/employee/faces/1/dimLeft3.jpg");
            photo3.setEmployee(saved);

            Photo photo4 = new Photo();
            photo4.setPath("/Users/macbook/Desktop/IdeaProjects/face-guard-pass/src/main/resources/photo/employee/faces/1/dimLeft4.jpg");
            photo4.setEmployee(saved);

            photoService.savePhoto(photo);
            photoService.savePhoto(photo1);
            photoService.savePhoto(photo2);
            photoService.savePhoto(photo3);
            photoService.savePhoto(photo4);*//*
        };
    }*/

}
