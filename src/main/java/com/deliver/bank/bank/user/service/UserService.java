package com.deliver.bank.bank.user.service;

import com.deliver.bank.bank.user.controllers.client.CpfValidationClient;
import com.deliver.bank.bank.user.dto.AddressDTO;
import com.deliver.bank.bank.user.dto.CpfValidationResponseDTO;
import com.deliver.bank.bank.user.dto.UserSaveDTO;
import com.deliver.bank.bank.user.dto.UserUpdateDTO;
import com.deliver.bank.bank.user.entities.Address;
import com.deliver.bank.bank.user.entities.User;
import com.deliver.bank.bank.user.entities.enums.UserProfile;
import com.deliver.bank.bank.user.entities.enums.UserStatus;
import com.deliver.bank.bank.user.repository.AddressRepository;
import com.deliver.bank.bank.user.repository.UserRepository;
import com.deliver.bank.bank.user.service.exceptions.CpfValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final CpfValidationClient cpfValidationClient;
    private final BCryptPasswordEncoder passwordEncoder;

    public User save(UserSaveDTO userDTO) {
        if (validateCpf(userDTO).getStatus().equalsIgnoreCase("UNABLE_TO_VOTE")) {
            throw new CpfValidationException("Falha na validação do CPF");
        }
        return userRepository.save(fromDTO(userDTO));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findByIdentifier (String identifier) {
        return userRepository.findByIdentifier(identifier);
    }

    public void update(String identifier, UserUpdateDTO userDTO) {
        User user = this.findByIdentifier(identifier);
        user.setIdentifier(userDTO.getIdentifier());
        user.setFullName(userDTO.getFullName());
        user.setAddress(fromAddressDTO(userDTO.getAddressDTO()));
        user.setBirthday(userDTO.getBirthday());
        user.setUserType(userDTO.getUserType());
        user.setEmail(userDTO.getEmail());
        user.setTelephone(userDTO.getTelephone());
        user.setUserStatus(userDTO.getUserStatus());
        user.setUserProfile(userDTO.getUserProfile());
        userRepository.save(user);
    }

    public void remove(String identifier) {
        User user = userRepository.findByIdentifier(identifier);
        userRepository.delete(user);
    }

    public CpfValidationResponseDTO validateCpf(UserSaveDTO userDTO) {
        return cpfValidationClient.validateCpf(userDTO.getIdentifier());
    }

    public User fromDTO(UserSaveDTO userDTO) {
        return new User(
                null,
                userDTO.getIdentifier(),
                userDTO.getFullName(),
                fromAddressDTO(userDTO.getAddressDTO()),
                userDTO.getBirthday(),
                userDTO.getUserType(),
                userDTO.getEmail(),
                userDTO.getTelephone(),
                passwordEncoder.encode(userDTO.getPassword()),
                UserStatus.CREATED,
                UserProfile.USER,
                LocalDateTime.now()
        );
    }

    public Address fromAddressDTO(AddressDTO addressDTO) {
        Address address = new Address();
        address.setCep(addressDTO.getCep());
        address.setStreetName(addressDTO.getStreetName());
        address.setHouseNumber(addressDTO.getHouseNumber());
        address.setCity(addressDTO.getCity());
        address.setCountry(addressDTO.getCountry());
        return this.addressRepository.save(address);
    }
}
