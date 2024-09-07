package com.shopping.mylist.domain.controller;

import com.shopping.mylist.domain.models.User;
import com.shopping.mylist.domain.models.UserUpdate;
import com.shopping.mylist.domain.service.UserService;
import com.shopping.mylist.handler.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> listen() {
        var users = userService.findByAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        user.valid();

        var userSaved = userService.createOrUpdate(user);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentContextPath()
                .buildAndExpand(userSaved.getId())
                .toUri()).body(userSaved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody UserUpdate userUpdate, @PathVariable Long id){
        var user = userService.findById(id);
        user.setUsername(userUpdate.getUsername());

        userService.createOrUpdate(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.delete(id);

        var result = new ResponseStatus();
        result.setDate(LocalDate.now());
        result.setDetails("Usuário apagado com sucesso!");
        result.setStatus("deleted");
        result.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(result);
    }
}
