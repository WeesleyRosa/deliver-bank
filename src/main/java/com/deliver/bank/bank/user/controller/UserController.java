package com.deliver.bank.bank.user.controller;

import com.deliver.bank.bank.user.dto.UserSaveDTO;
import com.deliver.bank.bank.user.dto.UserGetDTO;
import com.deliver.bank.bank.user.dto.UserUpdateDTO;
import com.deliver.bank.bank.user.entities.User;
import com.deliver.bank.bank.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createNewUser(@RequestBody UserSaveDTO userDTO) {
        User user = userService.save(userDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<UserGetDTO>> getAll() {
        List<User> users = userService.getAll();
        List<UserGetDTO> usersDTO = users.stream().map(UserGetDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<UserGetDTO> findByIdentifier(@PathVariable String identifier) {
        UserGetDTO userDTO = new UserGetDTO(userService.findByIdentifier(identifier));
        return ResponseEntity.ok().body(userDTO);
    }

    @PutMapping(value = "/{identifier}")
    public ResponseEntity<Void> updateByIdentifier(@PathVariable String identifier, @RequestBody UserUpdateDTO personDTO) {
        userService.update(identifier, personDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/{identifier}")
    public ResponseEntity<Void> removeByIdentifier(@PathVariable String identifier) {
        userService.remove(identifier);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}