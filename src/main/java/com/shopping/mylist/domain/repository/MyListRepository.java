package com.shopping.mylist.domain.repository;

import com.shopping.mylist.domain.models.MyList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyListRepository extends JpaRepository<MyList, Long> {

    List<MyList> findMyListsByUserId(Long userId);

    @Query("SELECT l FROM MyList l JOIN l.items i WHERE l.user.id = :userId AND l.name LIKE %:name%")
    List<MyList> findMyListsByItemsLikeAndName(@Param("name") String name, @Param("userId") Long userId);

}
