package com.deliver.bank.bank.domain.user.service;

import com.deliver.bank.bank.domain.user.api.v1.controller.request.AddressDTO;
import com.deliver.bank.bank.domain.user.api.v1.controller.request.UserSaveDTO;
import com.deliver.bank.bank.domain.user.client.CpfValidationClient;
import com.deliver.bank.bank.domain.user.client.response.CpfValidationResponseDTO;
import com.deliver.bank.bank.domain.user.entities.Address;
import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import com.deliver.bank.bank.domain.user.repository.AddressRepository;
import com.deliver.bank.bank.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CpfValidationClient cpfValidationClient;

    @InjectMocks
    private UserService subject;

    @Test
    void shouldReturnAllUsersWhenRequested() {
        // Entrada
        Address address01 = Address.builder()
                .id(1L)
                .cep("93123456")
                .streetName("Rua Desconhecida")
                .houseNumber("123")
                .city("Porto Alegre")
                .country("Brasil")
                .build();

        Address address02 = Address.builder()
                .id(2L)
                .cep("93654321")
                .streetName("Rua Sem Nome")
                .houseNumber("456")
                .city("Porto Alegre")
                .country("Brasil")
                .build();

        Address address03 = Address.builder()
                .id(3L)
                .cep("93456789")
                .streetName("Rua Comprida")
                .houseNumber("789")
                .city("Porto Alegre")
                .country("Brasil")
                .build();

        User user01 = User.builder()
                .id(1L)
                .identifier("08325866098")
                .fullName("João da Silva")
                .address(address01)
                .birthday(LocalDate.of(1975, Month.JANUARY, 1))
                .userType(UserType.CPF)
                .email("joao.silva@gmail.com")
                .telephone("51987654321")
                .password(bCryptPasswordEncoder.encode("1234"))
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        User user02 = User.builder()
                .id(1L)
                .identifier("00339000007")
                .fullName("Maria da Silva")
                .address(address02)
                .birthday(LocalDate.of(1977, Month.FEBRUARY, 2))
                .userType(UserType.CPF)
                .email("maria.silva@gmail.com")
                .telephone("51998765432")
                .password(bCryptPasswordEncoder.encode("1234"))
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        User user03 = User.builder()
                .id(3L)
                .identifier("58959320021")
                .fullName("José da Silva")
                .address(address02)
                .birthday(LocalDate.of(2001, Month.MARCH, 3))
                .userType(UserType.CPF)
                .email("jose.silva@gmail.com")
                .telephone("51999887766")
                .password(bCryptPasswordEncoder.encode("1234"))
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        List<User> expected = Arrays.asList(user01, user02, user03);

        // mock do comportamento
        when(userRepository.findAll()).thenReturn(expected);

        // Execução
        List<User> users = subject.getAll();

        // Validação
        assertEquals(3, expected.size());
        assertEquals(expected.get(0), users.get(0));
        assertEquals(expected.get(1), users.get(1));
        assertEquals(expected.get(2), users.get(2));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnAUserWhenProvidedAFoundIdentifier() {
        // Entrada
        String identifier = "08325866098";

        Address address = Address.builder()
                .id(1L)
                .cep("93123456")
                .streetName("Rua Desconhecida")
                .houseNumber("123")
                .city("Porto Alegre")
                .country("Brasil")
                .build();

        User userExpected = User.builder()
                .id(1L)
                .identifier("08325866098")
                .fullName("João da Silva")
                .address(address)
                .birthday(LocalDate.of(1975, Month.JANUARY, 1))
                .userType(UserType.CPF)
                .email("joao.silva@gmail.com")
                .telephone("51987654321")
                .password(bCryptPasswordEncoder.encode("1234"))
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(LocalDateTime.now())
                .build();

        // mock do comportamento
        when(userRepository.findByIdentifier(identifier)).thenReturn(userExpected);

        // Execução
        User user = subject.findByIdentifier(identifier);

        // Validação
        assertEquals(userExpected.getIdentifier(), user.getIdentifier());
        assertEquals(userExpected.getFullName(), user.getFullName());
        assertEquals(userExpected.getId(), user.getId());
        assertEquals(userExpected.getEmail(), user.getEmail());
        verify(userRepository, times(1)).findByIdentifier(identifier);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldReturnAUserWithStatusApprovedWhenProvidedAValidCpf() {
        // Entrada
        AddressDTO addressDto = AddressDTO.builder()
                .cep("93123456")
                .city("Porto Alegre")
                .country("Brasil")
                .houseNumber("123")
                .streetName("Rua Desconhecida")
                .build();

        Address addressToSave = Address.builder()
                .id(null)
                .cep(addressDto.getCep())
                .streetName(addressDto.getStreetName())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        Address addressExpected = Address.builder()
                .id(1L)
                .cep(addressDto.getCep())
                .streetName(addressDto.getStreetName())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        UserSaveDTO userDto = new UserSaveDTO();
        userDto.setIdentifier("08325866098");
        userDto.setFullName("João da Silva");
        userDto.setAddressDTO(addressDto);
        userDto.setBirthday(LocalDate.of(1975, Month.JANUARY, 1));
        userDto.setUserType(UserType.CPF);
        userDto.setEmail("joao.silva@gmail.com");
        userDto.setTelephone("51987654321");
        userDto.setPassword("1234");

        String password = bCryptPasswordEncoder.encode(userDto.getPassword());

        User userToSave = User.builder()
                .id(null)
                .identifier(userDto.getIdentifier())
                .fullName(userDto.getFullName())
                .address(addressExpected)
                .birthday(userDto.getBirthday())
                .userType(userDto.getUserType())
                .email(userDto.getEmail())
                .telephone(userDto.getTelephone())
                .password(password)
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(userDto.getCreatedAt())
                .build();

        User userExpected = User.builder()
                .id(1L)
                .identifier(userDto.getIdentifier())
                .fullName(userDto.getFullName())
                .address(addressExpected)
                .birthday(userDto.getBirthday())
                .userType(userDto.getUserType())
                .email(userDto.getEmail())
                .telephone(userDto.getTelephone())
                .password(password)
                .userStatus(UserStatus.APPROVED)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(userDto.getCreatedAt())
                .build();

        // mock do comportamento
        CpfValidationResponseDTO cpfValidation = new CpfValidationResponseDTO("ABLE_TO_VOTE");
        when(cpfValidationClient.validateCpf(userDto.getIdentifier())).thenReturn(cpfValidation);
        when(addressRepository.save(addressToSave)).thenReturn(addressExpected);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn(password);
        when(userRepository.save(userToSave)).thenReturn(userExpected);

        // Execução
        User user = subject.save(userDto);

        // Validação
        assertEquals(userExpected.getUserStatus(), user.getUserStatus());
        assertEquals(UserStatus.APPROVED, user.getUserStatus());
        assertEquals(BigDecimal.ZERO, user.getBalance());
        assertEquals(UserType.CPF, user.getUserType());
        assertEquals(UserProfile.USER, user.getUserProfile());
        verify(userRepository, times(1)).save(userToSave);
        verify(addressRepository, times(1)).save(addressToSave);
    }

    @Test
    void shouldReturnAUserWithStatusInAnalysisWhenProvidedAInvalidCpf() {
        // Entrada
        AddressDTO addressDto = AddressDTO.builder()
                .cep("93123456")
                .city("Porto Alegre")
                .country("Brasil")
                .houseNumber("123")
                .streetName("Rua Desconhecida")
                .build();

        Address addressToSave = Address.builder()
                .id(null)
                .cep(addressDto.getCep())
                .streetName(addressDto.getStreetName())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        Address addressExpected = Address.builder()
                .id(1L)
                .cep(addressDto.getCep())
                .streetName(addressDto.getStreetName())
                .houseNumber(addressDto.getHouseNumber())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        UserSaveDTO userDto = new UserSaveDTO();
        userDto.setIdentifier("12345678912");
        userDto.setFullName("João da Silva");
        userDto.setAddressDTO(addressDto);
        userDto.setBirthday(LocalDate.of(1975, Month.JANUARY, 1));
        userDto.setUserType(UserType.CPF);
        userDto.setEmail("joao.silva@gmail.com");
        userDto.setTelephone("51987654321");
        userDto.setPassword("1234");

        String password = bCryptPasswordEncoder.encode(userDto.getPassword());

        User userToSave = User.builder()
                .id(null)
                .identifier(userDto.getIdentifier())
                .fullName(userDto.getFullName())
                .address(addressExpected)
                .birthday(userDto.getBirthday())
                .userType(userDto.getUserType())
                .email(userDto.getEmail())
                .telephone(userDto.getTelephone())
                .password(password)
                .userStatus(UserStatus.IN_ANALYSIS)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(userDto.getCreatedAt())
                .build();

        User userExpected = User.builder()
                .id(1L)
                .identifier(userDto.getIdentifier())
                .fullName(userDto.getFullName())
                .address(addressExpected)
                .birthday(userDto.getBirthday())
                .userType(userDto.getUserType())
                .email(userDto.getEmail())
                .telephone(userDto.getTelephone())
                .password(password)
                .userStatus(UserStatus.IN_ANALYSIS)
                .userProfile(UserProfile.USER)
                .balance(BigDecimal.ZERO)
                .createdAt(userDto.getCreatedAt())
                .build();

        // mock do comportamento
        CpfValidationResponseDTO cpfValidation = new CpfValidationResponseDTO("UNABLE_TO_VOTE");
        when(cpfValidationClient.validateCpf(userDto.getIdentifier())).thenReturn(cpfValidation);
        when(addressRepository.save(addressToSave)).thenReturn(addressExpected);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn(password);
        when(userRepository.save(userToSave)).thenReturn(userExpected);

        // Execução
        User user = subject.save(userDto);

        // Validação
        assertEquals(userExpected.getUserStatus(), user.getUserStatus());
        assertEquals(UserStatus.IN_ANALYSIS, user.getUserStatus());
        assertEquals(BigDecimal.ZERO, user.getBalance());
        assertEquals(UserType.CPF, user.getUserType());
        assertEquals(UserProfile.USER, user.getUserProfile());
        verify(userRepository, times(1)).save(userToSave);
        verify(addressRepository, times(1)).save(addressToSave);
    }
}
