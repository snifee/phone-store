package com.phonestore.orderservice.service;

import com.phonestore.orderservice.model.Order;
import com.phonestore.orderservice.model.OrderLineItems;
import com.phonestore.orderservice.model.dto.InventoryResponse;
import com.phonestore.orderservice.model.dto.OrderLineItemsDto;
import com.phonestore.orderservice.model.dto.OrderRequest;
import com.phonestore.orderservice.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;

    private final WebClient.Builder webClientBuilder;

    public Order placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderName(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemsDto -> modelMapper.map(orderLineItemsDto, OrderLineItems.class))
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> orderSkuCodes = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        //WEB CLIENT API CALL
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-server/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", orderSkuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponses)
                .allMatch(InventoryResponse::isInStock);

        if (allProductInStock){
            return orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("product is not available");
        }
    }
}
