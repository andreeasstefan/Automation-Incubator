package com.endava.petclinic.TestData;

import com.endava.petclinic.model.Owner;
import com.endava.petclinic.model.Pet;
import com.endava.petclinic.model.Type;
import com.endava.petclinic.model.Visit;
import com.github.javafaker.Faker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestDataProvide {

    private static final Logger log = LogManager.getLogger(TestDataProvide.class);
    private Faker faker = new Faker();
    public Owner getOwner(){
        Owner owner = new Owner();
        owner.setFirstName( faker.name().firstName());
        owner.setLastName(faker.name().lastName());
        owner.setAddress(faker.address().streetAddress());
        owner.setCity(faker.address().city());
        //owner.setTelephone(faker.number().digits(7));
        owner.setTelephone(faker.number().digits(faker.number().numberBetween(1,11)));

        return owner;

    }

    List<String> animals = Arrays.asList("Dog", "Cat", "Lion");

    Random random = new Random();
    String randomAnimal = animals.get(random.nextInt(animals.size()));

    public Type getType(){
        Type type = new Type();
        String randomAnimal = animals.get(random.nextInt(animals.size()));
        type.setName(randomAnimal);
        type.setId(faker.number().numberBetween(1,3));
        return type;
    }

    public Pet getPet(){
        Pet pet = new Pet();
        pet.setName( faker.name().firstName());
        Date birthDate = faker.date().past(3650, TimeUnit.DAYS);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formattedBirthDate = formatter.format(birthDate);
        pet.setBirthDate(formattedBirthDate);
        pet.setOwner(getOwner());
        getOwner().setId(faker.number().numberBetween(1L,10L));
        pet.setType(getType());
        return pet;
    }

    public Visit getVisit(){
        Visit visit = new Visit();
        visit.setId(faker.number().numberBetween(1,10));
        Date date = faker.date().past(3650, TimeUnit.DAYS);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String formattedVisitDate = formatter.format(date);
        visit.setDate(String.valueOf(formattedVisitDate));
        visit.setDescription(faker.medical().diseaseName());
        visit.setPet(getPet());
        return visit;

    }



    public String getNumberWithDigits(int min, int max){
        return faker.number().digits(faker.number().numberBetween(min,max));
    }

}