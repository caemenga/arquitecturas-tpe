package org.app.mantenimiento.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.mantenimiento.Controllers.MantenimientoController;
import org.app.mantenimiento.Repositories.MantenimientoRepository;
import org.app.mantenimiento.Services.MantenimientoService;
import org.app.mantenimiento.entities.Mantenimiento;
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
    private final MantenimientoService mantenimientoService;
    @Autowired

    public CargaDeDatos(MantenimientoService mantenimientoService) {
        this.mantenimientoService = mantenimientoService;
    }

    public void cargarDatos() throws ParseException {
        String mantenimientoCSV = "microservicioMantenimiento/src/main/java/org/app/mantenimiento/utils/mantenimiento.csv";

        CSVParser parser = null;
        List<Mantenimiento> mantenimientos = new ArrayList<Mantenimiento>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(mantenimientoCSV));
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
        for(Mantenimiento m : mantenimientos){
            mantenimientoService.addMantenimiento(m);
        }
    }
}
