package com.codegym.controllers;

import com.codegym.models.Product;
import com.codegym.services.ProductService;
import com.codegym.services.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService = new ProductServiceImpl();

    @GetMapping("/product")
    public ModelAndView home() {
        return new ModelAndView("index", "products", this.productService.findAll());
    }

    @GetMapping("product/create")
    public ModelAndView create() {
        return new ModelAndView("create", "product", new Product());
    }

    @PostMapping("product/save")
    public ModelAndView save(Product product) {
        product.setId((int) (Math.random() * 99999));
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("index", "products", this.productService.findAll());
        modelAndView.addObject("success", "Created successfully");
        return modelAndView;
    }

    @GetMapping("product/{id}/edit")
    public ModelAndView edit(@PathVariable int id) {
        return new ModelAndView("edit", "product", this.productService.findById(id));
    }

    @PostMapping("product/update")
    public ModelAndView update(Product product) {
        productService.update(product.getId(), product);
        ModelAndView modelAndView = new ModelAndView("index", "products", this.productService.findAll());
        modelAndView.addObject("success", "Updated successfully");
        return modelAndView;
    }

    @GetMapping("product/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        return new ModelAndView("delete", "product", productService.findById(id));
    }

    @PostMapping("/product/delete")
    public ModelAndView delete(Product product) {
        productService.remove(product.getId());
        ModelAndView modelAndView = new ModelAndView("index", "products", productService.findAll());
        modelAndView.addObject("success", "Deleted successfully");
        return modelAndView;
    }

    @GetMapping("/product/{id}/view")
    public ModelAndView view(Product product) {
        return new ModelAndView("view", "product", productService.findById(product.getId()));
    }

    @GetMapping("/product/search")
    public ModelAndView search() {
        return new ModelAndView("search");
    }

    @PostMapping("/product/result")
    public ModelAndView result(@RequestParam String nameSearch) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productService.findAll().size(); i++) {
            if (nameSearch.equalsIgnoreCase(productService.findAll().get(i).getName())) {
                products.add(productService.findAll().get(i));
            }
        }
        ModelAndView modelAndView = new ModelAndView("index", "products", products);
        modelAndView.addObject("success", "?????");
        return modelAndView;
    }

}
