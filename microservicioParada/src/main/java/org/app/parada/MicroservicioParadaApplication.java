package org.app.parada;

import jakarta.annotation.PostConstruct;
import org.app.parada.utils.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class MicroservicioParadaApplication {

    @Autowired
    private CargaDeDatos carga;
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        SpringApplication.run(MicroservicioParadaApplication.class, args);
    }
    @PostConstruct
    public void init() throws IOException, ParseException {
        carga.cargarDatos();
    }
}
