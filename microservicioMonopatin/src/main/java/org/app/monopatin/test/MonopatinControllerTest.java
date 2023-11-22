package org.app.monopatin.test;
import org.app.monopatin.services.MonopatinService;


import org.app.monopatin.entities.Monopatin;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@DataJpaTest
public class MonopatinControllerTest {
    private MonopatinService monopatinService;

    public void addMonopatinTest() throws Exception {
        Monopatin monopatinTest = new Monopatin(12L, 1000, 2000);
        monopatinService.addMonopatin(monopatinTest);
        Optional<Monopatin> result = monopatinService.getById(monopatinTest.getId());

        Assert.assertTrue(result.isPresent());
        Assert.assertEquals(result.get().getId(), monopatinTest.getId());

    }
}
