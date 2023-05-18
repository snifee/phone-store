package com.phonestore.orderservice.model.dto;

import com.phonestore.orderservice.model.OrderLineItems;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}
