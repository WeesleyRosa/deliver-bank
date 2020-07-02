package com.deliver.bank.bank.domain.user.repository;

import com.deliver.bank.bank.domain.user.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {}
