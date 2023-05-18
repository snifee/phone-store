package com.phonestore.productservice.controller;

import com.phonestore.productservice.model.Product;
import com.phonestore.productservice.model.dto.ProductRequest;
import com.phonestore.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody ProductRequest productRequest){
        try {
            return productService.create(productRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAll(){
        try{
            return productService.getAll();
        }catch (Exception e){
            return null;
        }
    }
}
