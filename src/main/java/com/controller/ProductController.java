package com.controller;

import com.dto.ProductDTO;
import com.model.Product;
import com.service.ProductService;
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

    @RequestMapping("/")
    public String index(Model model){
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model){
        List<ProductDTO> productList = productService.listProducts();
        model.addAttribute("productList", productList);

        ProductDTO product = productService.getProduct(41);
        if (product == null){
            model.addAttribute("prod", "-");
        } else {
            model.addAttribute("prod", product.getProductName());
        }

        ProductDTO lasProduct = productService.getLastProduct();
        if (lasProduct == null){
            model.addAttribute("lastP", "-");
        } else {
            model.addAttribute("lastP", lasProduct.getProductName());
        }

        return "home";
    }

    @RequestMapping("/setting")
    public String setting(Model model){
        model.addAttribute("sett", "Настройка магазина");
        return "setting";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "productForm";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "productForm";
        }
        productService.saveOrUpdate(product);
        return "redirect:/home";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProduct(HttpServletRequest request, Model model){
        int product_id = Integer.parseInt(request.getParameter("id"));
        ProductDTO product = productService.getProduct(product_id);
        model.addAttribute("product", product);
        return "productForm";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteProduct(HttpServletRequest request){
        int product_id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(product_id);
        return "redirect:/home";
    }
}
