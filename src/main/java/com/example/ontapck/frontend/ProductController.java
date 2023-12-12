package com.example.ontapck.frontend;

import com.example.ontapck.enums.ProductStatus;
import com.example.ontapck.models.Product;
import com.example.ontapck.models.ProductPrice;
import com.example.ontapck.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private  ProductService productService;


    @GetMapping("/product-paging")
    public String findAll(Model model,
                          @RequestParam("pageNumber") Optional<Integer> pageNumber,
                          @RequestParam("pageSize") Optional<Integer> pageSize){
        int page = pageNumber.orElse(0);
        int size = pageSize.orElse(10);
        Sort sort = Sort.by(Sort.Direction.fromString("desc"),"productId");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> paging = productService.findAll(pageable);
        model.addAttribute("paging", paging);

        int totalPage = paging.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(0,totalPage-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return  "products";
    }
    @GetMapping("/detail/{id}")
    public String getDetail(Model model, @PathVariable("id") long id){
        Product product = productService.findById(id).orElse(null);
        model.addAttribute("product",product);
        List<ProductPrice>  productPrices = productService.findPricesById(id);
        model.addAttribute("productPrices",productPrices);

        return "product_detail";
    }
    @GetMapping("/add")
    public String showAddPage(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("listStatus", ProductStatus.values());
        model.addAttribute("productPrice", new ProductPrice());
        return "product_add";
    }
    @PostMapping("/add-product")
    public String add(Model model, @ModelAttribute("product") Product product,
                      @ModelAttribute("productPrice") ProductPrice productPrice){
        productService.saveProduct(product);
        productPrice.setProduct(product);
        productPrice.setPriceDateTime(LocalDateTime.now());
        productPrice.setNote("");
        productService.saveProductPrice(productPrice);
        return "redirect:/product-paging";
    }
    @GetMapping("/search-by-name")
    public String searchByName(Model model, @RequestParam("pageNumber") Optional<Integer> pageNumber,
                               @RequestParam("pageSize") Optional<Integer> pageSize, @RequestParam("name") String name){
        int page = pageNumber.orElse(0);
        int size = pageSize.orElse(10);
        Sort sort = Sort.by(Sort.Direction.fromString("desc"),"productId");
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Product> paging = productService.findByName(pageable,name);
        model.addAttribute("paging", paging);

        int totalPage = paging.getTotalPages();
        model.addAttribute("totalPage",totalPage);
        if(totalPage>0){
            List<Integer> pageNumbers = IntStream.rangeClosed(0,totalPage-1).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("nameSearch",name);
        return  "products_search";
    }

}