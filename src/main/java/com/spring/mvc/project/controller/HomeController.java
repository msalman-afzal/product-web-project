package com.spring.mvc.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.mvc.project.product.rpo.ProductRepoClass;
import com.spring.mvc.project.productdao.ProductDao;

@Controller
public class HomeController {

    @Autowired
    private ProductRepoClass repoClass;

    //  Show product form (Add new product page)
    @RequestMapping("/product")
    public String productForm() {
        System.out.println("Opening Add Product Page");
        return "product";
    }

    
    //   Display all products
    @RequestMapping("/display")
    public String getAllData(Model model) {
        List<ProductDao> allProducts = repoClass.getAllProduct();
        for (ProductDao product : allProducts) {
            System.out.println(product);
        }
        model.addAttribute("data", allProducts);
        return "AllData";
    }

    
    //  Save new product
    @RequestMapping(path = "/productForm", method = RequestMethod.POST)
    public String collectData(@ModelAttribute ProductDao product, Model model) {
        int insertData = repoClass.insertData(product);
        System.out.println("Inserted Rows: " + insertData);
        model.addAttribute("data", insertData);
        return "redirect:/display";
    }

    
    //  Show update form for a product by ID
    @RequestMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") int pid, Model model) {
        ProductDao product = repoClass.getById(pid);
        model.addAttribute("product", product);
        return "update";
    }

    
    //  Update product data
    @RequestMapping(path = "/updateProduct", method = RequestMethod.POST)
    public String updateData(@ModelAttribute ProductDao product, Model model) {
        int updated = repoClass.updataData(product);
        System.out.println("Updated Rows: " + updated);
        model.addAttribute("product", product);
        return "redirect:/display";
    }

    
    //   Delete product by ID
    @RequestMapping("/delete/{id}")
    public String deleteId(@PathVariable("id") int pid) {
        int deleteId = repoClass.deleteById(pid);
        System.out.println("Deletion Completed: " + deleteId);
        return "redirect:/display";
    }
}
