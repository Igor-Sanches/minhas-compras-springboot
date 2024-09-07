package com.shopping.mylist.domain.repository;

import com.shopping.mylist.domain.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByMyListId(Long myListId);

    @Query("SELECT l FROM Item l WHERE l.myList.id = :myListId AND l.name LIKE %:search%")
    List<Item> findByItensSearch(@Param("search") String search, @Param("myListId") Long myListId);

}
