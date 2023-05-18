package com.phonestore.orderservice.model.dto;

import lombok.Data;

@Data
public class OrderLineItemsDto {

    private Long id;
    private String skuCode;
    private Integer price;
    private Integer qty;
}
