package com.shopping.mylist.domain.controller;

import com.shopping.mylist.domain.models.MyList;
import com.shopping.mylist.domain.models.MyListUpdate;
import com.shopping.mylist.domain.service.MyListService;
import com.shopping.mylist.domain.service.UserService;
import com.shopping.mylist.handler.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/my-list")
public class ListController {

    @Autowired
    private MyListService myListService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> listen(@PathVariable Long userId) {
        var lists = myListService.findByUserId(userId);
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/listen/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var list = myListService.findById(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/search-item/{userId}")
    public ResponseEntity<?> get(@PathVariable Long userId, @RequestParam("search") String search) {
        var list = myListService.findByItemName(search, userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("create/{userId}")
    public ResponseEntity<?> create(@RequestBody MyList myList, @PathVariable Long userId) {
        myList.setUser(userService.findById(userId));
        var result = myListService.createOrUpdate(myList);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(result).toUri()).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody MyListUpdate myList, @PathVariable Long id) {
        var list = myListService.findById(id);

        list.setName(myList.getName());
        var result = myListService.createOrUpdate(list);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        myListService.delete(id);

        var result = new ResponseStatus();
        result.setDate(LocalDate.now());
        result.setDetails("Lista apagada com sucesso!");
        result.setStatus("deleted");
        result.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(result);
    }

}

