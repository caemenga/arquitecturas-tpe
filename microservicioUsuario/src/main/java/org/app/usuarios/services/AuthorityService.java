package org.app.usuarios.services;

import lombok.RequiredArgsConstructor;
import org.app.usuarios.entities.Authority;
import org.app.usuarios.entities.Cuenta;
import org.app.usuarios.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("Authority")
@RequiredArgsConstructor
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;


    public Authority add(Authority a) {
        return authorityRepository.save(a);
    }

}
