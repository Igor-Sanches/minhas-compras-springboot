package com.shopping.mylist.domain.service;

import com.shopping.mylist.domain.models.User;

import java.util.List;

public interface UserService {

    User createOrUpdate(User user);

    User findById(Long id);

    User findByUsername(String username);

    User findByMyListName(String myListName);

    List<User> findsByMyListName(String myListName);

    Object delete(Long id);

    List<User> findByAll();
}
