package ca.sheridan.hasankam.controller;

import ca.sheridan.hasankam.dao.ProductsDatabaseAccess;
import ca.sheridan.hasankam.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductsDatabaseAccess productsDatabaseAccess;

    // Entry point - Accessible by all roles
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Login Page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Access Denied Page
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    // Add Product - Accessible only by ADMIN
    @GetMapping("/AddProduct/productDataInput")
    public String productDataInput(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productDataInput";
    }

    @PostMapping("/AddProduct/productDataInput")
    public String addProduct(Model model, @ModelAttribute Product product) {
        String message;
        long rowsInserted = productsDatabaseAccess.addProduct(product);
        if (rowsInserted > 0) {
            message = "Success! Product successfully added to the database.";
        } else {
            message = "Failure! Product could not be added to the database.";
        }
        model.addAttribute("message", message);
        return "AddProduct/productAddConfirm";
    }

    // List Products - Accessible only by SALES
    @GetMapping("/ListProducts/listProducts")
    public String viewListOfProducts(Model model) {
        List<Product> products = productsDatabaseAccess.selectAllProducts();
        model.addAttribute("products", products);
        return "ListProducts/listProducts";
    }

    // Edit Products - Accessible only by ADMIN
    @GetMapping("/EditProduct/editableListOfProducts")
    public String editableListOfProducts(Model model) {
        List<Product> products = productsDatabaseAccess.selectAllProducts();
        model.addAttribute("products", products);
        return "EditProduct/editableListOfProducts";
    }

    @GetMapping("/EditProduct/editProduct/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        Product product = productsDatabaseAccess.selectProductById(id);
        if (product == null) {
            model.addAttribute("error", "Product with ID " + id + " not found.");
            return "error";
        }
        model.addAttribute("product", product);
        return "EditProduct/editProductData";
    }

    @PostMapping("/EditProduct/updateProduct")
    public String updateProduct(Model model, @ModelAttribute Product product) {
        String message;
        long rowsUpdated = productsDatabaseAccess.updateProductById(product);
        if (rowsUpdated > 0) {
            message = "Success! Product successfully updated in the database.";
        } else {
            message = "Failure! Product could not be updated in the database.";
        }
        model.addAttribute("message", message);
        return "EditProduct/editOutcome";
    }

    // Delete Product - Accessible only by ADMIN
    @GetMapping("/DeleteProduct/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id, Model model) {
        long rowsDeleted = productsDatabaseAccess.deleteProductById(id);
        String message;
        if (rowsDeleted > 0) {
            message = "Success! Product successfully deleted.";
        } else {
            message = "Failure! Product could not be deleted.";
        }
        model.addAttribute("message", message);
        return "DeleteProduct/deleteOutcome";
    }
}
