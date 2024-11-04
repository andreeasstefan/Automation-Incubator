package com.endava.petclinic;

import com.endava.petclinic.TestData.TestDataProvide;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;
import com.endava.petclinic.fixture.PetClinicFixture;
import com.endava.petclinic.services.DBService;

public class TestBaseClass {

    protected OwnerClient ownerClient = new OwnerClient();

    protected PetClient petClient = new PetClient();
    protected TestDataProvide testDataProvider = new TestDataProvide();

    protected PetClinicFixture fixture = new PetClinicFixture();

    protected DBService db = new DBService();

}
