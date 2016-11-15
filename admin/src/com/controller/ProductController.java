package com.controller;

import dto.ProductDTO;
import dto.util.PaginationBuilder;
import model.DiscountType;
import model.Product;
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
        System.out.println("TYPE = === > " + DiscountType.Auto.ordinal());
        return "index";
    }

    @ModelAttribute("paginator")
    PaginationBuilder getPaginationBuilder(){
        int numberAllRows = productService.getNumberAllRowsProduct();
        PaginationBuilder paginationBuilder = new PaginationBuilder(numberAllRows);
        return paginationBuilder;
    }

    @RequestMapping(value = "/home")
    public String home(Model model, HttpServletRequest request,
                       @ModelAttribute("paginator")PaginationBuilder paginationBuilder){
        String info = request.getParameter("info");
        model.addAttribute("info", info);
        paginationBuilder.updateNumberFirstSamplingElement();
        int numberOfPages = paginationBuilder.getNumberOfPages();
        model.addAttribute("numberOfPages", numberOfPages);
        List<ProductDTO> productList = productService.listProducts(paginationBuilder);
        model.addAttribute("productList", productList);
        model.addAttribute("url", "/home");
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
        Product productEntity;
        int id = productDTO.getId();
        if(bindingResult.hasErrors()){
            return "productForm";
        }
        if(productService.getProduct(id) == null){
            productEntity = new Product();
        } else {
            productEntity = productService.getProduct(id);
        }

        System.out.println("ID = " + productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productService.saveOrUpdate(productEntity);
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
    public String deleteProduct(HttpServletRequest request, Model model){
        int product_id = Integer.parseInt(request.getParameter("id"));
        if(productService.getProduct(product_id).getSales().isEmpty()
                & productService.getProduct(product_id).getDiscounts().isEmpty()){
            model.addAttribute("info", "true");
            productService.deleteProduct(product_id);
            return "redirect:/home";
        } else {
            model.addAttribute("info", "errorDelete");
            return "redirect:/home";
        }
    }
}
