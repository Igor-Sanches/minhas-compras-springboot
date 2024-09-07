package com.shopping.mylist.domain.controller;

import com.shopping.mylist.domain.models.Item;
import com.shopping.mylist.domain.models.ItemUpdate;
import com.shopping.mylist.domain.service.ItemService;
import com.shopping.mylist.domain.service.MyListService;
import com.shopping.mylist.handler.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private MyListService myListService;

    @Autowired
    private ItemService service;

    @GetMapping("/{listId}")
    public ResponseEntity<?> listen(@PathVariable Long listId) {
        var itens = service.findByMyListId(listId);
        return ResponseEntity.ok(itens);
    }

    @GetMapping("/listen/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
       var item = service.findById(id);

       return ResponseEntity.ok(item);
    }

    @GetMapping("/search-item/{listId}")
    public ResponseEntity<?> get(@PathVariable Long listId, @RequestParam("search") String search) {
      var itens = service.findByItemName(search, listId);
      return ResponseEntity.ok(itens);
    }

    @PostMapping("create/{listId}")
    public ResponseEntity<?> create(@RequestBody Item item, @PathVariable Long listId) {
        item.setMyList(myListService.findById(listId));
        var result = service.createOrUpdate(item);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(result).toUri()).body(result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ItemUpdate itemUpdate, @PathVariable Long id) {
        var item = service.findById(id);
        item.setAmount(itemUpdate.getAmount());
        item.setCount(itemUpdate.getCount());
        item.setName(itemUpdate.getName());
        service.createOrUpdate(item);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);

        var result = new ResponseStatus();
        result.setDate(LocalDate.now());
        result.setDetails("Item apagada com sucesso!");
        result.setStatus("deleted");
        result.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(result);
    }

}

