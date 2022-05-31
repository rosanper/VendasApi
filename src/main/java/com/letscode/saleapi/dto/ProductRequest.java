package com.letscode.saleapi.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String productId;
    private Integer quantity;
}
