package com.letscode.saleapi.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private String category;
    private String size;

}
