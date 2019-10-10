package com.first.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 10
 */
@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false , defaultValue = "world")String name , Model model){
        model.addAttribute("name", name);
        return "hello";
    }
}
