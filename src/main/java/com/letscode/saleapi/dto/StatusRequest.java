package com.letscode.saleapi.dto;

import com.letscode.saleapi.enums.Status;
import lombok.Data;

@Data
public class StatusRequest {
    private String cartId;
    private Status statusCart;
}
