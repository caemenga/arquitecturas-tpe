package administrador.test;
import org.app.administrador.Services.AdministradorService;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
@DataJpaTest
public class AdministradorControllerTest {

    @Autowired
    private AdministradorService administradorService;

    @Test
    @Transactional
    public void getMonopatinesPorXViajesTest(){

    }


}
