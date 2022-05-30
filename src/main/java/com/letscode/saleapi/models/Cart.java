package com.letscode.saleapi.models;

import com.letscode.saleapi.dto.Cep;
import com.letscode.saleapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String id;

    private String userId;
    private Double totalPrice;
    private Cep cep;
    private Status statusCart;

    public Cart(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.statusCart = Status.MONTANDO;
    }
}
