package com.letscode.saleapi.dto;

import lombok.Data;

@Data
public class UserRequest {
    private String cpf;
    private String password;
}
