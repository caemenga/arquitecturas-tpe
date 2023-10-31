package org.app.monopatin.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.monopatin.entities.Monopatin;
import org.app.monopatin.entities.Viaje;
import org.app.monopatin.services.MonopatinService;
import org.app.monopatin.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CargaDeDatos {

    private final MonopatinService monopatinService;
    private final ViajeService viajeService;

    @Autowired
    public CargaDeDatos(MonopatinService monopatinService, ViajeService viajeService) {
        this.monopatinService = monopatinService;
        this.viajeService = viajeService;
    }

    public void cargarDatos()throws ParseException {
        this.cargarDatosMonopatin();
        this.cargarDatosViaje();
    }

    public void cargarDatosMonopatin() throws ParseException {
        String monopatinCSV = "microservicioMonopatin/src/main/java/org/app/monopatin/utils/monopatin.csv";

        CSVParser parser = null;
        List<Monopatin> monopatines = new ArrayList<Monopatin>();

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(monopatinCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parser) {
            monopatines.add(new Monopatin(Long.parseLong(row.get(0)), Long.parseLong(row.get(1)), Long.parseLong(row.get(2))));
        }

        for(Monopatin m : monopatines){
            monopatinService.addMonopatin(m);
        }
    }

    public void cargarDatosViaje() throws ParseException {
        String monopatinCSV = "microservicioMonopatin/src/main/java/org/app/monopatin/utils/viaje.csv";

        CSVParser parser = null;
        List<Viaje> viajes = new ArrayList<Viaje>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(monopatinCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parser) {
            java.util.Date utilDate = formato.parse(row.get(2));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            java.util.Date utilDate2 = formato.parse(row.get(3));
            java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());

            Optional<Monopatin> m = monopatinService.getById(Long.parseLong(row.get(1)));
            if(m.isPresent()){
                Monopatin m2 = m.get();
                    viajes.add(new Viaje(
                        Long.parseLong(row.get(0)),
                        m2,
                        sqlDate,
                        sqlDate2,
                        Double.parseDouble(row.get(4)),
                        Long.parseLong(row.get(5) )));
            }
        }

        for(Viaje m : viajes){
            System.out.println(m.toString());
            viajeService.addViaje(m);
        }
    }
}
