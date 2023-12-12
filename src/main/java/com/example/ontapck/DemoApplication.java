package com.example.ontapck;

import com.example.ontapck.enums.ProductStatus;
import com.example.ontapck.models.Product;
import com.example.ontapck.models.ProductPrice;
import com.example.ontapck.repositories.ProductPriceRepository;
import com.example.ontapck.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductPriceRepository productPriceRepository;

    @GetMapping("/add")
    CommandLineRunner init(){
        return args -> {
            Faker faker =  new Faker();
            Device device = faker.device();
            Random random = new Random();
            for(int i=0; i<200; i++){
                Product product = new Product(device.modelName(), device.platform(), device.platform(), device.manufacturer(), ProductStatus.ACTIVE);
                int ran = random.nextInt(2);
                if(ran==0) product.setStatus(ProductStatus.ACTIVE);
                if(ran==1) product.setStatus(ProductStatus.IN_ACTIVE);

                productRepository.save(product);
                for(int j=0;j<3;j++){
                    ProductPrice productPrice = new ProductPrice();
                    productPrice.setProduct(product);
                    productPrice.setPrice(random.nextInt(200));
                    productPrice.setPriceDateTime(LocalDateTime.now().plusDays(j));
                    productPrice.setNote("");
                    productPriceRepository.save(productPrice);
                }

            }
        };
    }

}