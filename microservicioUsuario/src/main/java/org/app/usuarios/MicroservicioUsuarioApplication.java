package org.app.usuarios;

import jakarta.annotation.PostConstruct;
import org.app.usuarios.utils.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.text.ParseException;

@SpringBootApplication
public class MicroservicioUsuarioApplication {

    @Autowired
    private CargaDeDatos carga;
    public static void main(String[] args) {

        SpringApplication.run(MicroservicioUsuarioApplication.class, args);
    }

    @PostConstruct
    public void init() throws IOException, ParseException {
        carga.cargarDatos();
    }
}