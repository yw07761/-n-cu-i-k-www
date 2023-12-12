package com.example.ontapck.PK;

import com.example.ontapck.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ProductPricePK implements Serializable {
    private Product product;
    private LocalDateTime priceDateTime;
}
