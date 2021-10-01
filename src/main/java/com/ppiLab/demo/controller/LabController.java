package com.ppiLab.demo.controller;

import com.ppiLab.demo.model.Item;
import com.ppiLab.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/lab04")
public class LabController {

    @GetMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        return n.toString();
    }

    @GetMapping(path = "/addItem")
    public @ResponseBody
    String addItem(@RequestParam String name, @RequestParam String description, @RequestParam long price, @RequestParam int quantity) {

        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setQuantity(quantity);

        return item.toString();


    }

    @GetMapping(path = "/addValid")
    public String addValid(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/result";
    }

    @GetMapping(path = "/addValidItem")
    public String addValidItem(@Valid Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formItem";
        }
        return "redirect:/result";
    }

}
