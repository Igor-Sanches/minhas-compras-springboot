package com.shopping.mylist.domain.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserUpdate{

    @Column(length = 199, nullable = false)
    private String username;

}
