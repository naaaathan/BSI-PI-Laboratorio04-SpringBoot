package com.ppiLab.demo.controller;

import com.ppiLab.demo.model.Item;
import com.ppiLab.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/item")
public class ItemRestController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> findAllItems() {

        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable(value = "id") long id) {

        Optional<Item> item = itemRepository.findById(id);
        return item.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Item editItem(@RequestBody Item item, @PathVariable Long id) {

        return itemRepository.findById(id)
                .map(itemEdited -> {
                    itemEdited.setQuantity(item.getQuantity());
                    itemEdited.setName(item.getName());
                    itemEdited.setQuantity(item.getQuantity());
                    itemEdited.setDescription(item.getDescription());
                    itemEdited.setDate(item.getDate());
                    return itemRepository.save(itemEdited);
                })
                .orElseGet(() -> {
                    item.setId(id);
                    return itemRepository.save(item);
                });
    }

    @PostMapping
    public Item saveItem(@Validated @RequestBody Item item) {

        return itemRepository.save(item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemById(@PathVariable(value = "id") long id) {

        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
