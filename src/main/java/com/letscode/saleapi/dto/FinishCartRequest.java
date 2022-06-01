package com.letscode.saleapi.dto;

import lombok.Data;

@Data
public class FinishCartRequest {
    private String cartId;
    private String userId;
    private String cpf;
    private String password;
}
