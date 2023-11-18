package org.app.usuarios.entities.test;

import org.app.usuarios.entities.Usuario;
import org.app.usuarios.repositories.UsuarioRepository;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


    @DataJpaTest
    public class UsuarioServiceTest {

        @Autowired
        private UsuarioRepository usuarioRepository;

        @Test
        @Transactional
        public void testFindById_Exists() {
            Usuario savedUser = new Usuario();
            savedUser.setNombre("Juan");
            savedUser.setApellido("Roman");
            savedUser.setTelefono(123456789);
            savedUser.setEmail("juan.roman@example.com");
            usuarioRepository.save(savedUser);

            Long userId = savedUser.getId();
            Optional<Usuario> result = usuarioRepository.findById(userId);
            assertEquals(userId, result.get().getId());
            assertEquals("Juan", result.get().getNombre());
            assertEquals("Roman", result.get().getApellido());
            assertEquals(123456789, result.get().getTelefono());
            assertEquals("juan.roman@example.com", result.get().getEmail());
        }

        @Test
        @Transactional
        public void testFindById_NotExists() {
            Long nonExistentUserId = Long.valueOf(100);
            Optional<Usuario> result = usuarioRepository.findById(nonExistentUserId);
            assertNull(result.get());
        }

    }

