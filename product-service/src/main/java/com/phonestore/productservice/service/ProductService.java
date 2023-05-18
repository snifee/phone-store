package com.phonestore.productservice.service;


import com.phonestore.productservice.model.Product;
import com.phonestore.productservice.model.dto.ProductRequest;
import com.phonestore.productservice.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {


    private ProductRepository productRepository;

    private ModelMapper modelMapper;
    public Product create(ProductRequest productRequest) throws Exception{
        Product product = modelMapper.map(productRequest, Product.class);

        return productRepository.save(product);
    }

    public List<Product> getAll() throws Exception{
        return productRepository.findAll();
    }
}
