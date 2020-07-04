package com.deliver.bank.bank.domain.user.client;

import com.deliver.bank.bank.domain.user.client.response.CpfValidationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cpfValidation", url = "${integration.cpf}")
public interface CpfValidationClient {

    @GetMapping("/users/{cpf}")
    CpfValidationResponseDTO validateCpf(@PathVariable String cpf);
}
