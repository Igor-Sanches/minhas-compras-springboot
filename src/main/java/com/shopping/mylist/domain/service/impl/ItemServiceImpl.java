package com.shopping.mylist.domain.service.impl;

import com.shopping.mylist.domain.models.Item;
import com.shopping.mylist.domain.repository.ItemRepository;
import com.shopping.mylist.domain.service.ItemService;
import com.shopping.mylist.handler.Exceptions.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createOrUpdate(Item item) {

        item.setUpdatedAt(LocalDateTime.now());
        item.valid();

        return itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item findById(Long id) {
        var item = itemRepository.findById(id).orElse(null);
        if(item == null) {
            throw new ItemNotFoundException();
        }
        return item;
    }

    @Override
    public List<Item> findByMyListId(Long myListId) {
        return itemRepository.findByMyListId(myListId);
    }

    @Override
    public List<Item> findByItemName(String search, Long myListId) {
        return itemRepository.findByItensSearch(search, myListId);
    }
}
