package com.deliver.bank.bank.domain.user.api.v1.controller;

import com.deliver.bank.bank.domain.user.entities.Address;
import com.deliver.bank.bank.domain.user.entities.User;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserProfile;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserStatus;
import com.deliver.bank.bank.domain.user.entities.enumerator.UserType;
import com.deliver.bank.bank.domain.user.repository.UserRepository;
import com.deliver.bank.bank.domain.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerRequestTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldReturnAllUsersFromService() throws Exception {
        // Entrada
        String endpoint = "/users";

        Address address01 = Address.builder()
                .id(1L)
                .cep("93123456")
                .streetName("Rua Desconhecida")
                .houseNumber("123")
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

        List<User> users = Arrays.asList(user01);

        // mock
        when(userService.getAll()).thenReturn(users);

        // Execução/Validação
        mockMvc.perform(get(endpoint)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].identifier", is(user01.getIdentifier())));
    }
}
