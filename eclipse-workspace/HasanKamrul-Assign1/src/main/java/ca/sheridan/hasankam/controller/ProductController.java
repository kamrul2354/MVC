/*
 * Author: Kamrul Hasan
 * Student ID: 991769795
 * Date: [24-09-2024]
 * 
 * Description:
 * This program is created as part of an assignment to develop a Product Catalog 
 * Management System using Spring Boot and Thymeleaf. It allows users to input product 
 * details, confirm the details, and view a list of products. The system uses 
 * a Model-View-Controller (MVC) design pattern, and the product details are stored 
 * in a list, which is displayed in a tabular format.
 */


package ca.sheridan.hasankam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ca.sheridan.hasankam.Product;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
	
	// Store all product objects
    private List<Product> products = new ArrayList<>(); // List to store products

    // Display index page (home page)
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Render the Product data input form
    @GetMapping("/productDataInput")
    public String productDataInput(Model model) {
        model.addAttribute("product", new Product()); // Bind Product object to the form
        return "productDataInput";
    }

    // Show the product data for confirmation
    @GetMapping("/productData")
    public String productData(@ModelAttribute Product product, Model model) {
        model.addAttribute("product", product); // Pass the product data to the view for confirmation
        return "productData"; // Show the confirmation page
    }

    // Handle confirmation and add product to the list
    @GetMapping("/confirmProduct")
    public String confirmProduct(@ModelAttribute Product product, Model model) {
        products.add(product); // Add the product to the list
        model.addAttribute("products", products); // Pass the updated product list to the view
        return "productInformation"; // Redirect to the product list page
    }
}
