package org.example;

import com.endava.petclinic.model.Owner;
import com.github.javafaker.Faker;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");


        Faker faker = new Faker();

        Owner owner = new Owner();
        owner.setAddress(faker.address().streetAddress());  // Setăm adresa

        // Verificăm dacă adresa este setată corect
        System.out.println("Adresa setată: " + owner.getAddress());
    }
    }
