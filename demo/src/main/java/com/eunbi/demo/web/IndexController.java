package com.eunbi.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// mustache 맵핑
public class IndexController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
