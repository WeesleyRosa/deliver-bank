package com.deliver.bank.bank.auth.service;

import com.deliver.bank.bank.auth.security.UserSecurity;
import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findByEmail(email);
        if (person == null) throw new UsernameNotFoundException(email);
        return new UserSecurity(person.getPersonId(), person.getEmail(), person.getPassword(), person.getProfiles());
    }
}
