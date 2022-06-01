package com.letscode.saleapi.dto;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private String phoneNumber;

}
