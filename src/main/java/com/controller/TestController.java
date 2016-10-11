package com.controller;

import com.service.test.GenerateTestDataService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private GenerateTestDataService generateTestDataService;

    @RequestMapping(value = "/test")
    public String generateTestData(){
        generateTestDataService.generateTestData();
        return "test";
    }



}
