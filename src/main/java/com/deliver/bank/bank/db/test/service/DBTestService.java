package com.deliver.bank.bank.db.test.service;

import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class DBTestService {

    private PersonRepository personRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DBTestService(PersonRepository personRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void instantiateTestDatabase() throws ParseException {
        Person person = new Person();

        person.setIdentifier("56728818073");
        person.setFullName("João da Silva");
        person.setAddress("Rua Desconhecida, 1234, Centro, Porto Alegre, RS");
        person.setEmail("joao.silva@gmail.com");
        person.setTelephoneNumber("51912345678");
        person.setPassword(passwordEncoder.encode("1234"));
        this.personRepository.save(person);

        person.setPersonId(null);
        person.setIdentifier("44768992005");
        person.setFullName("Maria da Silva");
        person.setAddress("Rua Desconhecida, 1234, Centro, Porto Alegre, RS");
        person.setEmail("maria.silva@gmail.com");
        person.setTelephoneNumber("51987654321");
        person.setPassword(passwordEncoder.encode("1234"));
        this.personRepository.save(person);

        person.setPersonId(null);
        person.setIdentifier("03261450010");
        person.setFullName("José da Silva");
        person.setAddress("Rua Desconhecida, 1234, Centro, Porto Alegre, RS");
        person.setEmail("jose.silva@gmail.com");
        person.setTelephoneNumber("5988887777");
        person.setPassword(passwordEncoder.encode("1234"));
        this.personRepository.save(person);
    }
}
