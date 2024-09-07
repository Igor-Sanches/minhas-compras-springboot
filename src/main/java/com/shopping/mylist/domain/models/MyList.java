package com.shopping.mylist.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopping.mylist.handler.BusinessException;
import com.shopping.mylist.handler.Exceptions.InputRequiredException;
import com.shopping.mylist.handler.Exceptions.MinMaxInputException;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "my_lists")
@Data
public class MyList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Item> items = new ArrayList<>();

    public void valid() {

        if (name == null || name.isEmpty()) {
            throw new InputRequiredException("name");
        }

        if (name.length() < 5 || name.length() > 100) {
            throw new MinMaxInputException("name");
        }

//        if (items == null || items.isEmpty()) {
//            throw new BusinessException("A lista não pode ficar vazia, adicione itens à sua lista de compras.");
//        }
    }
}
