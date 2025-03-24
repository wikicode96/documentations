package com.github.wikicode96.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private UUID id;
    private String product;
    private int quantity;
}
