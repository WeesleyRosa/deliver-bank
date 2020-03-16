package com.deliver.bank.bank.person.controller;

import com.deliver.bank.bank.account.dto.AccountDTO;
import com.deliver.bank.bank.account.entities.Account;
import com.deliver.bank.bank.account.service.AccountService;
import com.deliver.bank.bank.person.dto.PersonDTO;
import com.deliver.bank.bank.person.entities.Person;
import com.deliver.bank.bank.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> createNewPerson(@RequestBody PersonDTO personDTO) {
    personService.save(personDTO);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
}
    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<Person> list = personService.getAll();
        List<PersonDTO> listDto = list.stream().map(obj -> new PersonDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<PersonDTO> findById(@PathVariable String identifier) {
        PersonDTO personDTO = new PersonDTO(personService.findByIdentifier(identifier));
        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(value = "/{identifier}")
    public ResponseEntity<Void> update(@PathVariable String identifier, @RequestBody PersonDTO personDTO) {
        personService.update(identifier, personDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{identifier}")
    public ResponseEntity<Void> remove(@PathVariable String identifier) {
        personService.remove(identifier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}