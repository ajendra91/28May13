package com.example.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AppController {

    @RequestMapping("/")
    public String hello(){
        return "ajendra is here";
    }


    //this endpoint secure by user and
    //Using generated security password: 96a1155f-62d0-4b01-bbec-0abdbe9e483f

    @RequestMapping("/name")
    public String demo() {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return auth.getName()+"\t"+auth.getDetails();
    }

    @RequestMapping("/getName")
    public String getName(Principal pal) {
        return pal.getName();
    }

}
