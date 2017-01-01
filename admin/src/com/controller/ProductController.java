package com.controller;

import dto.ProductDTO;
import util.Pagination;
import service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = {"/", "/index"})
    public String index(){
        return "index";
    }

    @ModelAttribute("paginator")
    Pagination getPagination(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        return new Pagination(numberAllRows);
    }

    @RequestMapping(value = "/home")
    public String home(Model model, HttpServletRequest request,
                       @ModelAttribute("paginator") Pagination pagination){
        String info = request.getParameter("info");
        model.addAttribute("info", info);
        pagination.updateNumberFirstSamplingElement();
        int numberOfPages = pagination.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<ProductDTO> productList = productService.listProducts(pagination);
        model.addAttribute("productList", productList);
        model.addAttribute("url", "/admin/home");
        return "home";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(Model model){
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "productForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid @ModelAttribute ProductDTO productDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "productForm";
        }
        productService.saveOrUpdate(productDTO);
        return "redirect:/home";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(HttpServletRequest request, Model model){
        int product_id = Integer.parseInt(request.getParameter("id"));
        ProductDTO productDTO = productService.getProductDTOById(product_id);
        model.addAttribute("productDTO", productDTO);
        return "productForm";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request){
        int product_id = Integer.parseInt(request.getParameter("id"));
        try {
            productService.deleteProduct(product_id);
            return "redirect:/home";
        } catch (Exception e){
            return "redirect:/home";
        }
    }
}
