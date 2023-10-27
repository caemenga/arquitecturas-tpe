package org.app.mantenimiento;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.mantenimiento.entities.Mantenimiento;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MicroservicioMantenimientoApplication {
    public static void main(String[] args) throws ParseException {

        SpringApplication.run(MicroservicioMantenimientoApplication.class, args);
        cargarDatos();


    }
    public static List<Mantenimiento> cargarDatos() throws ParseException {
       String paradaCSV = "utils/mantenimiento.csv";


            CSVParser parser = null;
            List<Mantenimiento> mantenimientos = new ArrayList<Mantenimiento>();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            try {
                parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(paradaCSV));
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (CSVRecord row: parser) {
                java.util.Date utilDate = formato.parse(row.get(1));
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                java.util.Date utilDate2 = formato.parse(row.get(2));
                java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());
                mantenimientos.add(new Mantenimiento(Long.parseLong(row.get(0)), sqlDate, sqlDate2));
            }
            return mantenimientos;
    }
}

