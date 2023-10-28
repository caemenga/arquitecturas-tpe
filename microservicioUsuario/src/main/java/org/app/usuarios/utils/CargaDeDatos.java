package org.app.usuarios.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.services.CuentaService;
import org.app.usuarios.services.UsuarioService;
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

    private final UsuarioService usuarioService;
    private final CuentaService cuentaService;

    @Autowired
    public CargaDeDatos(UsuarioService usuarioService, CuentaService cuentaService) {
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
    }

    public void cargarDatos() throws ParseException {
        String usuariosCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/usuarios.csv";
        String cuentasCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/cuentas.csv";

        CSVParser parserUsuarios = null;
        CSVParser parserCuentas = null;
        List<Usuario> usuarios = new ArrayList<Usuario>();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parserUsuarios = CSVFormat.DEFAULT.withHeader().parse(new FileReader(usuariosCSV));
            parserCuentas = CSVFormat.DEFAULT.withHeader().parse(new FileReader(cuentasCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (CSVRecord row: parserUsuarios) {
            usuarios.add(new Usuario(row.get(0), row.get(1), Long.parseLong(row.get(2)), row.get(3)));
        }
        for(Usuario u : usuarios){
            usuarioService.addUsuario(u);
        }

        for (CSVRecord row: parserCuentas) {
            java.util.Date utilDate = formato.parse(row.get(0));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            cuentas.add(new Cuenta(sqlDate, Float.parseFloat(row.get(1))));
        }
        for(Cuenta c : cuentas){
            cuentaService.addCuenta(c);
        }
    }
}
