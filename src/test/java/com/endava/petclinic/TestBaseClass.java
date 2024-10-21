package com.endava.petclinic;

import com.endava.petclinic.TestData.TestDataProvide;
import com.endava.petclinic.client.OwnerClient;
import com.endava.petclinic.client.PetClient;

public class TestBaseClass {

    protected OwnerClient ownerClient = new OwnerClient();

    protected PetClient petClient = new PetClient();
    protected TestDataProvide testDataProvider = new TestDataProvide();

}
