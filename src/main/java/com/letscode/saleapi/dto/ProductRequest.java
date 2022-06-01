package com.letscode.saleapi.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String cartId;
    private String userId;
    private String productId;
    private Integer quantity;
}
