package org.app.mantenimiento;

import jakarta.annotation.PostConstruct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.mantenimiento.Controllers.MantenimientoController;
import org.app.mantenimiento.Repositories.MantenimientoRepository;
import org.app.mantenimiento.entities.Mantenimiento;
import org.app.mantenimiento.utils.CargaDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MicroservicioMantenimientoApplication {

    @Autowired
    private CargaDeDatos carga;
    public static void main(String[] args) throws ParseException, FileNotFoundException {

        SpringApplication.run(MicroservicioMantenimientoApplication.class, args);
    }
    @PostConstruct
    public void init() throws IOException, ParseException {
        carga.cargarDatos();
    }
}

