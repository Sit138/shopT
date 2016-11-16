package com.controller;

import service.test.GenerateTestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private GenerateTestDataService generateTestDataService;

    @RequestMapping(value = "/setting/test")
    public String generateTestData(){
        generateTestDataService.generateTestData();
        return "admin/test";
    }



}
