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
    private ProductsDatabaseAccess pda;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/productDataInput")
    public String productDataInput(Model model) {
        model.addAttribute("product", new Product());
        return "productDataInput";
    }

    @PostMapping("/productDataInput")
    public String addProduct(Model model, @ModelAttribute Product product) {
        String message;
        long numOfRows = pda.addProduct(product);
        if (numOfRows > 0) {
            message = "Success! The product was successfully added to the database.";
        } else {
            message = "Failure! The product was not added to the database.";
        }
        model.addAttribute("message", message);
        return "productAddConfirm";
    }

    @GetMapping("/listOfProducts")
    public String viewListOfProducts(Model model) {
        List<Product> products = pda.selectProducts();
        model.addAttribute("products", products);
        return "listOfProducts";
    }

    @GetMapping("/editableListOfProducts")
    public String editableListOfProducts(Model model) {
        List<Product> products = pda.selectProducts();
        model.addAttribute("products", products);
        return "EditProduct/editableListOfProducts";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        Product product = pda.getProductById(id);
        model.addAttribute("product", product);
        return "EditProduct/editProductData";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Model model, @ModelAttribute Product product) {
        String message;
        int numOfRows = pda.updateProduct(product);
        if (numOfRows > 0) {
            message = "Success! The product was successfully updated.";
        } else {
            message = "Failure! The product was not updated.";
        }
        model.addAttribute("message", message);
        return "EditProduct/productUpdateConfirm";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, Model model) {
        int numOfRows = pda.deleteProduct(id);
        String message;
        if (numOfRows > 0) {
            message = "Success! The product was successfully deleted.";
        } else {
            message = "Failure! The product was not deleted.";
        }
        model.addAttribute("message", message);
        return "EditProduct/productDeleteConfirm";
    }

    // Corrected mappings for Feature 5: List products by brand
    @GetMapping("/productsByBrand")
    public String productsByBrandForm(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productsByBrand";
    }

    @PostMapping("/productsByBrand")
    public String productsByBrand(Model model, @ModelAttribute Product product) {
        List<Product> products = pda.selectProductsByBrand(product.getProductBrand());
        model.addAttribute("products", products);
        return "ListProducts/listOfProductsByBrand";
    }

    // Corrected mappings for Feature 6: List products by quantity threshold
    @GetMapping("/productsByQuantity")
    public String productsByQuantityForm(Model model) {
        model.addAttribute("product", new Product());
        return "AddProduct/productsByQuantity";
    }

    @PostMapping("/productsByQuantity")
    public String productsByQuantity(Model model, @ModelAttribute Product product) {
        List<Product> products = pda.selectProductsByQuantity(product.getQuantityInHand());
        model.addAttribute("products", products);
        return "ListProducts/listOfProductsByQuantity";
    }
}
