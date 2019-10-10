package com.first.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Chiayhon
 * @create 2019 - 10 - 10
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String Index(){return "Index";}
}
