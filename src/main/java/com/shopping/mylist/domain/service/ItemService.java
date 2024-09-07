package com.shopping.mylist.domain.service;

import com.shopping.mylist.domain.models.Item;

import java.util.List;

public interface ItemService {

    Item createOrUpdate(Item item);

    void delete(Long id);

    Item findById(Long id);

    List<Item> findByMyListId(Long myListId);

    List<Item> findByItemName(String search, Long myListId);
}
