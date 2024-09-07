package com.shopping.mylist.domain.service.impl;

import com.shopping.mylist.domain.models.MyList;
import com.shopping.mylist.domain.repository.MyListRepository;
import com.shopping.mylist.domain.service.MyListService;
import com.shopping.mylist.handler.Exceptions.ListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MyListServiceImpl implements MyListService {

    @Autowired
    private MyListRepository listRepository;

    @Override
    public MyList createOrUpdate(MyList myList) {

        myList.setUpdatedAt(LocalDateTime.now());
        myList.valid();

        return listRepository.save(myList);
    }

    @Override
    public void delete(Long id) {
        listRepository.deleteById(id);
    }

    @Override
    public MyList findById(Long id) {
        var list = listRepository.findById(id).orElse(null);
        if(list == null) {
            throw new ListNotFoundException();
        }
        return list;
    }

    @Override
    public List<MyList> findByUserId(Long userId) {
        return listRepository.findMyListsByUserId(userId);
    }

    @Override
    public List<MyList> findByItemName(String search, Long userId) {
        return listRepository.findMyListsByItemsLikeAndName(search, userId);
    }

}
