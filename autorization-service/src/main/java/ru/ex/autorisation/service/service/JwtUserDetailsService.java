package ru.ex.autorisation.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ex.autorisation.service.model.dao.Person;
import ru.ex.autorisation.service.security.JwtUser;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public JwtUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUser.fromPerson(Person.builder()
                .email("sam@gmail.com")
                .name("Sam")
                .pass("sdfsdf")
                .role("user")
                .build());
    }
}
