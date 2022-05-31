package com.letscode.saleapi.models;

import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.dto.Product;
import com.letscode.saleapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String id = UUID.randomUUID().toString();
    private String userId;
    private Double totalPrice;
    private Cep cep;
    private Status statusCart = Status.MONTANDO;
    private List<Product> products = new ArrayList<>();

    public Cart(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.statusCart = Status.MONTANDO;
    }
}
