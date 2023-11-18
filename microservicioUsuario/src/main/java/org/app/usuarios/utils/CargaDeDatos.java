package org.app.usuarios.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.entities.Usuario;
import org.app.usuarios.repositories.AuthorityRepository;
import org.app.usuarios.repositories.UsuarioRepository;
import org.app.usuarios.services.AuthorityService;
import org.app.usuarios.services.CuentaService;

//import org.app.usuarios.services.RolService;
import org.app.usuarios.services.UsuarioService;
import org.app.usuarios.services.dto.user.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class CargaDeDatos {

    private final UsuarioRepository usuarioRepository;
    private final AuthorityRepository authorityRepository;
    private final UsuarioService usuarioService;
    private final CuentaService cuentaService;

    private final AuthorityService authorityService;

    @Autowired
    public CargaDeDatos(UsuarioRepository usuarioRepository, AuthorityRepository authorityRepository, UsuarioService usuarioService, CuentaService cuentaService, AuthorityService rolService) {
        this.usuarioRepository = usuarioRepository;
        this.authorityRepository = authorityRepository;
        this.usuarioService = usuarioService;
        this.cuentaService = cuentaService;
        this.authorityService = rolService;
    }

    public void cargarDatos() throws ParseException {
        String usuariosCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/usuarios.csv";
        String cuentasCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/cuentas.csv";
        String rolCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/rol.csv";
        String rolUsuarioCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/rolUsuario.csv";
        String cuentaUsuarioCSV = "microservicioUsuario/src/main/java/org/app/usuarios/utils/cuentaUsuario.csv";

        CSVParser parserUsuarios = null;
        CSVParser parserCuentas = null;
        CSVParser parserRoles = null;
        CSVParser parserRolUsuario = null;
        CSVParser parserCuentaUsuario = null;

        List<UserRequestDTO> usuarios = new ArrayList<UserRequestDTO>();
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        List<Authority> roles = new ArrayList<Authority>();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        try {
            parserUsuarios = CSVFormat.DEFAULT.withHeader().parse(new FileReader(usuariosCSV));
            parserCuentas = CSVFormat.DEFAULT.withHeader().parse(new FileReader(cuentasCSV));
            parserRoles = CSVFormat.DEFAULT.withHeader().parse(new FileReader(rolCSV));
            parserRolUsuario = CSVFormat.DEFAULT.withHeader().parse(new FileReader(rolUsuarioCSV));
            parserCuentaUsuario = CSVFormat.DEFAULT.withHeader().parse(new FileReader(cuentaUsuarioCSV));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //cuentas
        for (CSVRecord row: parserCuentas) {
            java.util.Date utilDate = formato.parse(row.get(0));
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            cuentas.add(new Cuenta(sqlDate, Float.parseFloat(row.get(1))));
        }
        for(Cuenta c : cuentas){
            cuentaService.addCuenta(c);
        }


        //roles
        for (CSVRecord row: parserRoles){
            roles.add(new Authority(row.get(0)));
            authorityService.add(new Authority(row.get(0)));
        }


        //usuarios
        for (CSVRecord row: parserUsuarios) {
            UserRequestDTO userRequestDTO = new UserRequestDTO(row.get(0), row.get(1), row.get(2), Long.parseLong(row.get(3)), row.get(4));
            usuarioService.createUser(userRequestDTO);
        }

        //CUENTAUSUARIO
        for(CSVRecord row: parserCuentaUsuario){
        Optional<Usuario> u = this.usuarioService.getById(Long.parseLong(row.get(0)));
        Optional<Cuenta> c = this.cuentaService.getById(Long.parseLong(row.get(1)));
            if(u.isPresent() & c.isPresent()){
                Cuenta account = c.get();
                Usuario user = u.get();
                user.addCuenta(account);
                this.usuarioRepository.save(user);
            }
        }

        //ROL USUARIO
        for(CSVRecord row: parserRolUsuario){
            Optional<Usuario> u = this.usuarioService.getById(Long.parseLong(row.get(0)));
            Optional<Authority> a = this.authorityRepository.findById(row.get(1));
            if(u.isPresent() && a.isPresent()){
                Usuario user = u.get();
                Authority aut = a.get();

                user.addAuthorities(aut);
                this.usuarioRepository.save(user);
            }
        }
    }
}
