package com.deliver.bank.bank.domain.user.service;

import com.deliver.bank.bank.domain.user.client.CpfValidationClient;
import com.deliver.bank.bank.domain.user.api.v1.controller.request.AddressDTO;
import com.deliver.bank.bank.domain.user.client.response.CpfValidationResponseDTO;
import com.deliver.bank.bank.domain.user.api.v1.controller.request.UserSaveDTO;
import com.deliver.bank.bank.domain.user.api.v1.controller.request.UserUpdateDTO;
import com.deliver.bank.bank.domain.user.entities.Address;
import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.repository.AddressRepository;
import com.deliver.bank.bank.domain.user.repository.UserRepository;
import com.deliver.bank.bank.domain.user.service.exceptions.CpfValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
          return userRepository.save(fromDTO(userDTO, UserStatus.IN_ANALYSIS));
        }
        else return userRepository.save(fromDTO(userDTO, UserStatus.APPROVED));
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

    public User fromDTO(UserSaveDTO userDTO, UserStatus userStatus) {
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
                userStatus,
                UserProfile.USER,
                LocalDateTime.now(),
                BigDecimal.ZERO
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
