package com.shopping.mylist.domain.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemUpdate {
    private String name;
    private int count;
    private BigDecimal amount;
}
