package com.phonestore.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_order_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;
    private Integer price;
    private Integer qty;
}
