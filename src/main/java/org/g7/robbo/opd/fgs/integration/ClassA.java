package org.g7.robbo.opd.fgs.integration;

import org.springframework.stereotype.Service;

/**
 * @author Orlov Diga
 */
@Service("aService")
public class ClassA {

    public ClassA() {
    }

    public Animal process(Animal animal) {
        System.out.println("A is processing animal: " + animal.getName());

        animal.setName("lol");

        return animal;
    }
}
