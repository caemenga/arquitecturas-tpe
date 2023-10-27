package org.app.paradas.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import org.app.paradas.entities.Parada;

public class CSVHelperParada {
    private static String paradaCSV = "utils/paradaCSV.csv";
    public static List<Parada> clientesCSV() {

        CSVParser parser = null;
        List<Parada> paradas = new ArrayList<Parada>();

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(paradaCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parser) {
            paradas.add(new Parada(Long.parseLong(row.get(0)), Long.parseLong(row.get(1)), Long.parseLong(row.get(2)));
        }
        return paradas;
    }
}
