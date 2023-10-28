package org.app.monopatin.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.services.MonopatinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CargaDeDatos {

    private final MonopatinService monopatinService;

    @Autowired
    public CargaDeDatos(MonopatinService monopatinService) {
        this.monopatinService = monopatinService;
    }

    public void cargarDatos() throws ParseException {
        String monopatinCSV = "microservicioMonopatin/src/main/java/org/app/monopatin/utils/monopatin.csv";

        CSVParser parser = null;
        List<Monopatin> monopatines = new ArrayList<Monopatin>();

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(monopatinCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parser) {
            monopatines.add(new Monopatin(Integer.parseInt(row.get(0)), Long.parseLong(row.get(1)), Long.parseLong(row.get(2))));
        }
        for(Monopatin m : monopatines){
            monopatinService.addMonopatin(m);
        }
    }
}
