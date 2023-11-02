package org.app.parada.utils;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

//import com.opencsv.CSVParser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.app.parada.entities.Parada;
import org.app.parada.services.ParadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargaDeDatos {

    private final ParadaService paradaService;

    @Autowired
    public CargaDeDatos(ParadaService paradaService) {
        this.paradaService = paradaService;
    }

    public void cargarDatos() throws ParseException {
        String paradasCSV = "microservicioParada/src/main/java/org/app/parada/utils/paradas.csv";

        CSVParser parser = null;
        List<Parada> paradas = new ArrayList<Parada>();

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(paradasCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parser) {
            paradas.add(new Parada(Double.parseDouble(row.get(0)), Double.parseDouble(row.get(1))));
        }
        for(Parada p : paradas){
            paradaService.addParada(p);
        }
    }
}
