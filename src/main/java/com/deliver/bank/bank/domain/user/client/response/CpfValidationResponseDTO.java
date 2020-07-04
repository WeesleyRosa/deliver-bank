package com.deliver.bank.bank.domain.user.client.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CpfValidationResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
}
