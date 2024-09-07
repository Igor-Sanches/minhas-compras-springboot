package com.shopping.mylist.handler;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseStatus {
    private int statusCode = 400;
    private String details;
    private String status = "error";
    private LocalDate date = LocalDate.now();

}
