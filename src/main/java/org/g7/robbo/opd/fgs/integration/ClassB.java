package org.g7.robbo.opd.fgs.integration;

import org.springframework.stereotype.Service;

/**
 * @author Orlov Diga
 */
/*
@Service("bService")
*/
public class ClassB {

    public ClassB() {
    }

    public Animal process(Animal animal) {
        System.out.println("B is processing animal: " + animal.getName());

        animal.setSurname("kek");

        return animal;
    }


}
