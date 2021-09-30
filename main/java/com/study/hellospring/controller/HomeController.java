package com.study.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {


    //컨트롤러가 없을때는 index.html이 자동으로 메인페이지가 되지만
    //컨트롤러가 있을때는 컨트롤러에 설정되있는페이지가 메인페이지가 된다
    @GetMapping("/")
    public String home() {
        return "home";
    }


}
