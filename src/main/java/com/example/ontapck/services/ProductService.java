package com.example.ontapck.services;

import com.example.ontapck.models.Product;
import com.example.ontapck.models.ProductPrice;
import com.example.ontapck.repositories.ProductPriceRepository;
import com.example.ontapck.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;

    public Page<Product> findAll(Pageable pageable){
        return  productRepository.findAll(pageable);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public List<ProductPrice> findPricesById(long id) {
        return productPriceRepository.findProductPricesByProduct(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public ProductPrice saveProductPrice(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    public Page<Product> findByName(Pageable pageable, String name) {
        return productRepository.findAllByNameContainingIgnoreCase(pageable,name);
    }
}