package com.shopping.mylist.domain.service;

import com.shopping.mylist.domain.models.MyList;

import java.util.List;

public interface MyListService {

    MyList createOrUpdate(MyList myList);

    void delete(Long id);

    MyList findById(Long id);

    List<MyList> findByUserId(Long userId);

    List<MyList> findByItemName(String search, Long userId);
}
