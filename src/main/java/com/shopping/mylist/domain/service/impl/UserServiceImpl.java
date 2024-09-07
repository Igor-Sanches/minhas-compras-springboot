package com.shopping.mylist.domain.service.impl;

import com.shopping.mylist.domain.models.User;
import com.shopping.mylist.domain.repository.UserRepository;
import com.shopping.mylist.domain.service.UserService;
import com.shopping.mylist.handler.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createOrUpdate(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.valid();
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        var user = userRepository.findById(id).orElse(null);
        if(user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByMyListName(String myListName) {
        return null;
    }

    @Override
    public List<User> findsByMyListName(String myListName) {
        return List.of();
    }

    @Override
    public Object delete(Long id) {
        return null;
    }

    @Override
    public List<User> findByAll() {
        return userRepository.findAll();
    }

}
