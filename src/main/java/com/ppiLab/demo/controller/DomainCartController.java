package com.ppiLab.demo.controller;

import com.ppiLab.demo.model.Item;
import com.ppiLab.demo.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DomainCartController {

    @GetMapping("/cart/")
    public String processCart(Model model, HttpSession session) {

        List<String> messageList = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messageList);
        model.addAttribute("sessionId", session.getId());
        Item storedItem = (Item) session.getAttribute("SESSION_ITEM_DATA");
        if (storedItem != null) {
            model.addAttribute("itemData", storedItem);
        }
        return "domainCart";

    }

    @PostMapping("/cart/persistObject")
    public String persistMessage(@RequestParam("item") Item item, HttpSession session) {

        List<Item> storedItems = (List<Item>) session.getAttribute("SESSION_ITEM_DATA");
        if (storedItems == null) {
            storedItems = new ArrayList<>();
            session.setAttribute("SESSION_ITEM_DATA", storedItems);
        }

        storedItems.add(item);
        session.setAttribute("SESSION_ITEM_DATA", storedItems);

        return "";
    }


}
