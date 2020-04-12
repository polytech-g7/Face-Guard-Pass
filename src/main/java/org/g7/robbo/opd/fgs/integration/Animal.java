package org.g7.robbo.opd.fgs.integration;

/**
 * @author Orlov Diga
 */
public class Animal {
    private String name;
    private String surname;

    public Animal() {
    }

    public Animal(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
