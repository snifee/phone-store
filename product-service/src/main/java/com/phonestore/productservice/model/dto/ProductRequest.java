package com.phonestore.productservice.model.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private Integer price;
}
