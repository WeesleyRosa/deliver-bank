package com.deliver.bank.bank.user.repository;

import com.deliver.bank.bank.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   User findByIdentifier (String identifier);

   @Transactional(readOnly = true)
   User findByEmail(String email);
}