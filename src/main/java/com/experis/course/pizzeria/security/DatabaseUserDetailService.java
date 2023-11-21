package com.experis.course.pizzeria.security;

import com.experis.course.pizzeria.model.User;
import com.experis.course.pizzeria.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        prendo lo username che viene dalla login e lo cerco nel database
        Optional<User> loggedUser = userRepository.findByEmail(username);
        if (loggedUser.isPresent()) {
            // se lo trovo
            // restituisco un oggetto di tipo DatabaseUserDetails con i dati dello user
            return new DatabaseUserDetails(loggedUser.get());
        } else {
            // se non lo trovo lancio un'eccezione
            throw new UsernameNotFoundException(username);
        }

    }
}
