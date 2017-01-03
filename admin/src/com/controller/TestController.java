package com.controller;

import org.springframework.web.bind.annotation.RequestMethod;
import service.test.GenerateTestDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @Autowired
    private GenerateTestDataService generateTestDataService;

    @RequestMapping(value = "/setting/test", method = RequestMethod.GET)
    public String generateTestData(){
        generateTestDataService.generateAndSaveProduct();
        generateTestDataService.generateAndSaveDiscount();
        return "setting/test";
    }

}
