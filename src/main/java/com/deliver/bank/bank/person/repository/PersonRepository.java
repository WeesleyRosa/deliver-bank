package com.deliver.bank.bank.person.repository;

import com.deliver.bank.bank.person.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
   Person findByIdentifier (String identifier);

   @Transactional(readOnly = true)
   Person findByEmail(String email);
}