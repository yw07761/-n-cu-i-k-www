package com.example.ontapck.repositories;

import com.example.ontapck.PK.ProductPricePK;
import com.example.ontapck.models.Product;
import com.example.ontapck.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, ProductPricePK> {
    //    ProductPrice getProductPriceByProduct_ProductIdAndAndPriceDateTime_Max(long productId);
    @Query("select pp from ProductPrice pp where pp.product.productId=:id")
    List<ProductPrice> findProductPricesByProduct(@Param("id") long id);
}