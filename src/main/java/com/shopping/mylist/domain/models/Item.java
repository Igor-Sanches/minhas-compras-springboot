package com.shopping.mylist.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shopping.mylist.handler.Exceptions.InputRequiredException;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer count = 0;

    @Column(precision = 13, scale = 2, nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @ManyToOne
    @JsonBackReference
    private MyList myList;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedDate
    private LocalDateTime updatedAt = LocalDateTime.now();


    public void valid(){

        if (name == null || name.isEmpty()) {
            throw new InputRequiredException("name");
        }

        if(count == null){
            count = 0;
        }

        if(amount == null){
            amount = BigDecimal.ZERO;
        }

    }
}
