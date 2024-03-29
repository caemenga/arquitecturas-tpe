package org.app.monopatin;

import jakarta.annotation.PostConstruct;
import org.app.monopatin.utils.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class MicroservicioMonopatinApplication {

    @Autowired
    private CargaDeDatos carga;
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        SpringApplication.run(MicroservicioMonopatinApplication.class, args);
    }
    @PostConstruct
    public void init() throws Exception {
        carga.cargarDatos();
    }
}
