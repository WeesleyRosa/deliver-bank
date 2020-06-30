package com.deliver.bank.bank.person.controller;

import com.deliver.bank.bank.person.dto.PersonRequestDTO;
import com.deliver.bank.bank.person.dto.PersonResponseDTO;
import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Void> createNewPerson(@RequestBody PersonRequestDTO personDTO) {
        Person person = personService.save(personDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getPersonId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<PersonResponseDTO>> getAll() {
        List<Person> list = personService.getAll();
        List<PersonResponseDTO> listDto = list.stream().map(PersonResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<PersonResponseDTO> findByIdentifier(@PathVariable String identifier) {
        PersonResponseDTO personDTO = new PersonResponseDTO(personService.findByIdentifier(identifier));
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(value = "/{identifier}")
    public ResponseEntity<Void> updateByIdentifier(@PathVariable String identifier, @RequestBody PersonRequestDTO personDTO) {
        personService.update(identifier, personDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{identifier}")
    public ResponseEntity<Void> removeByIdentifier(@PathVariable String identifier) {
        personService.remove(identifier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}