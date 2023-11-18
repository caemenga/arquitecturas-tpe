package org.app.usuarios.security;
//
//import lombok.RequiredArgsConstructor;
//import org.app.usuarios.entities.Authority;
//import org.app.usuarios.entities.Usuario;
//import org.app.usuarios.repositories.UsuarioRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class DomainUserDetailsService implements UserDetailsService {
//
//    private final UsuarioRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository
//                .findByEmailIgnoreCase( email )
//                .map(this::createSpringSecurityUser)
//                .orElseThrow(() -> new UsernameNotFoundException("No existe el usuario con email " + email ));
//    }
//
//
//    private Usuario createSpringSecurityUser(Usuario user) {
//        String authority = user.getRol().getName();
//
//        return new Usuario(user.getEmail(), user.getPassword(), user.getRol());
//    }
//}
