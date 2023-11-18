package org.app.usuarios.entities.test;

import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.services.CuentaService;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@DataJpaTest
public class CuentaControllerTest {
    @Autowired
    private CuentaService cuentaService;

    @Test
    @Transactional
    public void inhabilitarCuentaTest(){
        Date dia = Date.valueOf("2023-11-17");
        Cuenta cuentaTest = new Cuenta(dia, 2000);
        cuentaService.addCuenta(cuentaTest);
        Assert.assertTrue(cuentaTest.isHabilitada());
        cuentaService.inhabilitarCuenta(cuentaTest.getId());
        Assert.assertFalse(cuentaTest.isHabilitada());
    }

}
