package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.ImageService;
import util.PropertyApp;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadForm(Model model, HttpServletRequest request){
        List<MultipartFile> upload = Arrays.asList();
        model.addAttribute("uploadList", upload);
        model.addAttribute("productId", request.getParameter("prod"));
        return "upload";
    }

    @RequestMapping(value = "/saveImage", method = RequestMethod.POST)
    public String save(@RequestParam("uploadList") List<MultipartFile> uploadFiles,
                       @RequestParam("productId") int productId){
        String uploadFolder = PropertyApp.PATH_PRODUCT_IMAGE + productId + "/";
        imageService.save(uploadFiles, uploadFolder);
        return "redirect:/home";
    }

}
