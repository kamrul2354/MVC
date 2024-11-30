package ca.sheridan.hasankam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ca.sheridan.hasankam.LoanClass;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoanClassController {

    // Store all loan objects
    private List<LoanClass> loans = new ArrayList<>();

    // Display the index page (home page)
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Render the loan data input form
    @GetMapping("/loanClassDataInput")
    public String loanDataInput(Model model) {
        model.addAttribute("loan", new LoanClass()); // Bind Loan object to the form
        return "loanClassDataInput";
    }

    // Process loan data for confirmation
    @GetMapping("/loanClassData")
    public String loanData(@ModelAttribute LoanClass loan, Model model) {
        model.addAttribute("loan", loan); // Pass loan data to the view for confirmation
        return "loanClassData";
    }

    // Handle confirmation, add loan to the list, and calculate payments
    @GetMapping("/confirmLoan")
    public String confirmLoan(@ModelAttribute LoanClass loan, Model model) {
        loans.add(loan); // Add the new loan to the list

        // Pass the updated list to the view
        model.addAttribute("loans", loans);
        return "loanClassInformation"; // Show the loan details page
    }
}
