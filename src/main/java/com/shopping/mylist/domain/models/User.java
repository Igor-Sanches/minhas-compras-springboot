package com.shopping.mylist.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shopping.mylist.handler.Exceptions.InputRequiredException;
import com.shopping.mylist.handler.Exceptions.MinMaxInputException;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 199, nullable = false)
    private String username;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<MyList> myLists = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @CreatedDate
    private LocalDateTime updatedAt = LocalDateTime.now();

    public void valid() {
        if (username == null || username.isEmpty()) {
            throw new InputRequiredException("username");
        }

        if (username.length() < 5 || username.length() > 100) {
            throw new MinMaxInputException("username");
        }

        if (myLists == null || myLists.isEmpty()) {
            myLists = new ArrayList<>();
        }
    }
}

