package com.example.ontapck.models;

import com.example.ontapck.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
//@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long productId;
    private String name;
    private String description;
    private String unit;
    @Column(name = "manufacture_name")
    private String manufactureName;
    private ProductStatus status;
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<ProductPrice> productPrices;

    public Product(String name, String description, String unit, String manufactureName, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.unit = unit;
        this.manufactureName = manufactureName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unit='" + unit + '\'' +
                ", manufactureName='" + manufactureName + '\'' +
                ", status=" + status +
                '}';
    }
}