package com.deliver.bank.bank.db.test.service;

import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.Address;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import com.deliver.bank.bank.domain.user.repository.AddressRepository;
import com.deliver.bank.bank.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@Service
public class DBTestService {

    private final UserRepository personRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public DBTestService(
            UserRepository personRepository,
            AddressRepository addressRepository,
            BCryptPasswordEncoder passwordEncoder
    ) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void instantiateTestDatabase() throws ParseException {
        User user = new User();
        Address address = new Address();

        address.setCep("93123456");
        address.setStreetName("Rua Desconhecida");
        address.setHouseNumber("123");
        address.setCity("Porto Alegre");
        address.setCountry("Brasil");
        addressRepository.save(address);

        user.setIdentifier("56728818073");
        user.setFullName("João da Silva");
        user.setAddress(address);
        user.setEmail("joao.silva@gmail.com");
        user.setTelephone("51912345678");
        user.setBirthday(LocalDate.of(1975, Month.JANUARY, 1));
        user.setUserType(UserType.CPF);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setUserStatus(UserStatus.IN_ANALYSIS);
        user.setUserProfile(UserProfile.USER);
        user.setCreatedAt(LocalDateTime.now());
        this.personRepository.save(user);

        address.setId(null);
        address.setUser(null);
        address.setCep("93345678");
        address.setStreetName("Rua Sem Nome");
        address.setHouseNumber("456");
        address.setCity("Porto Alegre");
        address.setCountry("Brasil");
        addressRepository.save(address);

        user.setId(null);
        user.setIdentifier("03261450010");
        user.setFullName("Maria da Silva");
        user.setAddress(address);
        user.setEmail("maria.silva@gmail.com");
        user.setTelephone("51987654321");
        user.setBirthday(LocalDate.of(1977, Month.FEBRUARY, 2));
        user.setUserType(UserType.CPF);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setUserStatus(UserStatus.IN_ANALYSIS);
        user.setUserProfile(UserProfile.USER);
        user.setCreatedAt(LocalDateTime.now());
        this.personRepository.save(user);

        address.setId(null);
        address.setUser(null);
        address.setCep("93654321");
        address.setStreetName("Rua Comprida");
        address.setHouseNumber("789");
        address.setCity("Porto Alegre");
        address.setCountry("Brasil");
        addressRepository.save(address);

        user.setId(null);
        user.setIdentifier("00608324019");
        user.setFullName("João da Silva Júnior");
        user.setAddress(address);
        user.setEmail("joao.jr@gmail.com");
        user.setTelephone("51998765432");
        user.setBirthday(LocalDate.of(2001, Month.MARCH, 3));
        user.setUserType(UserType.CPF);
        user.setPassword(passwordEncoder.encode("1234"));
        user.setUserStatus(UserStatus.IN_ANALYSIS);
        user.setUserProfile(UserProfile.USER);
        user.setCreatedAt(LocalDateTime.now());
        this.personRepository.save(user);
    }
}
