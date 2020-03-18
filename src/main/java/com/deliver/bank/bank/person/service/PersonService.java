package com.deliver.bank.bank.person.service;

import com.deliver.bank.bank.person.dto.PersonRequestDTO;
import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public void personService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(PersonRequestDTO personDTO) {
        return personRepository.save(fromDTO(personDTO));
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findByIdentifier (String identifier) {
        return personRepository.findByIdentifier(identifier);
    }

    public void update(String identifier, PersonRequestDTO personDTO) {
        Person person = this.findByIdentifier(identifier);
        person.setFullName(personDTO.getFullName());
        person.setAddress(personDTO.getAddress());
        person.setEmail(personDTO.getEmail());
        person.setTelephoneNumber(personDTO.getTelephoneNumber());
        personRepository.save(person);
    }

    public void remove(String identifier) {
        Person person = personRepository.findByIdentifier(identifier);
        personRepository.delete(person);
    }

    public Person fromDTO(PersonRequestDTO personDTO) {
        return new Person(null,
                personDTO.getIdentifier(),
                personDTO.getFullName(),
                personDTO.getAddress(),
                personDTO.getEmail(),
                personDTO.getTelephoneNumber(),
                personDTO.getPassword());
    }
}
