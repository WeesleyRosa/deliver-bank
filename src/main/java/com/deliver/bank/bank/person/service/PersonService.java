package com.deliver.bank.bank.person.service;

import com.deliver.bank.bank.person.dto.PersonDTO;
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

    public void save(PersonDTO personDTO) {
        personRepository.save(fromDTO(personDTO));
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findByIdentifier (String identifier) {
        return personRepository.findByIdentifier(identifier);
    }

    public void update(String identifier, PersonDTO personDTO) {
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

    public Person fromDTO(PersonDTO personDTO) {
        return new Person(personDTO.getPersonId(), personDTO.getIdentifier(), personDTO.getFullName(),
                personDTO.getAddress(), personDTO.getEmail(), personDTO.getTelephoneNumber());
    }
}
