package com.deliver.bank.bank.domain.user.api.v1.controller;

import com.deliver.bank.bank.domain.user.api.v1.controller.request.AddressDTO;
import com.deliver.bank.bank.domain.user.api.v1.controller.request.UserSaveDTO;
import com.deliver.bank.bank.domain.user.api.v1.controller.response.UserGetDTO;
import com.deliver.bank.bank.domain.user.entities.Address;
import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import com.deliver.bank.bank.domain.user.service.UserService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class UserControllerUnitTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private UserService userService = Mockito.mock(UserService.class);
    private ServletUriComponentsBuilder servletUriComponentsBuilder = Mockito.mock(ServletUriComponentsBuilder.class);
    private UserController userController = new UserController(userService);

    @Test
    public void shouldReturnAllUsersFromService() {
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

        List<User> users = Arrays.asList(user01, user02, user03);

        ResponseEntity<List<UserGetDTO>> expected = ResponseEntity
                .ok(users.stream().map(UserGetDTO::new).collect(Collectors.toList()));

        // mocks
        when(userService.getAll()).thenReturn(users);

        // execução
        ResponseEntity<List<UserGetDTO>> actual = userController.getAll();

        // validação
        assertEquals(expected.getBody(), actual.getBody());
    }

    @Test
    public void shouldReturnAUserFromServiceWhenProvidedAFoundIdentifier() {
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

        User user = User.builder()
                .id(1L)
                .identifier(identifier)
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

        UserGetDTO expected = new UserGetDTO(user);

        // mocks
        when(userService.findByIdentifier(identifier)).thenReturn(user);

        // execução
        UserGetDTO actual = new UserGetDTO(userService.findByIdentifier(identifier));

        // validação
        assertEquals(expected.getIdentifier(), actual.getIdentifier());
    }
}
