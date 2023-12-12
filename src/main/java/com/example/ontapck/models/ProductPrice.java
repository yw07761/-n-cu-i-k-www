package com.example.ontapck.models;

import com.example.ontapck.PK.ProductPricePK;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ProductPrice")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@IdClass(ProductPricePK.class)
@ToString
public class ProductPrice {
    @Id
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id")
    private Product product;
    @Id
    @Column(name = "price_date_time")
    private LocalDateTime priceDateTime;
    private double price;
    private String note;


}
