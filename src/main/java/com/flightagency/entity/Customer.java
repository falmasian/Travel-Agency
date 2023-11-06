package com.flightagency.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private LocalDate birthDate;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setFirstName(String name) {
        firstName = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String name) {
        lastName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setNationalcode(String code) {
        nationalCode = code;
    }

    public String getNationalcode() {
        return nationalCode;
    }

    public void setBirthDate(LocalDate date) {
        birthDate = date;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

}

