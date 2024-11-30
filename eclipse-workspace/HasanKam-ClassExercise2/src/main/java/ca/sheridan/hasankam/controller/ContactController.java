package ca.sheridan.hasankam.controller;

import ca.sheridan.hasankam.dao.ContactDatabaseAccess;
import ca.sheridan.hasankam.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ContactController {
    
    @Autowired
    private ContactDatabaseAccess cda;
    
    // Entry point
    @GetMapping("/index")
    public String home() {
        return "index";
    }

    // Display form for adding a new contact
    @GetMapping("/contactDataInput")
    public String contactDataInput(Model model) {
        model.addAttribute("contact", new Contact());
        return "contactDataInput";
    }

    // Handle the form submission for adding a new contact
    @PostMapping("/contactDataInput")
    public String addContact(Model model, @ModelAttribute Contact contact) {
        String message;
        long numberOfRows = cda.addContact(contact);
        if (numberOfRows > 0) {
            message = "Success! The contact has been successfully added to the address book.";
        } else {
            message = "Failure --- The contact could not be added to the address book.";
        }
        model.addAttribute("message", message);
        return "contactAddConfirm";
    }

    // Display list of all contacts
    @GetMapping("/listOfContacts")
    public String viewListOfContacts(Model model) {
        List<Contact> contacts = cda.selectContacts();
        model.addAttribute("contacts", contacts);
        return "listOfContacts";
    }

    // Display list of contacts with edit options
    @GetMapping("/editableListOfContacts")
    public String editableListOfContacts(Model model) {
        List<Contact> contacts = cda.selectContacts();
        model.addAttribute("contacts", contacts);
        return "editableListOfContacts";
    }

    // Display form for editing a specific contact
    @GetMapping("/editContact/{id}")
    public String editContact(@PathVariable("id") Integer id, Model model) {
        Contact contact = cda.selectContactById(id);
        model.addAttribute("contact", contact);
        return "editContactData";
    }

    // Handle the form submission for updating a contact
    @PostMapping("/updateContact")
    public String updateContact(Model model, @ModelAttribute Contact contact) {
        String message;
        int contactId = contact.getId();
        long rowsUpdated = cda.updateContactById(contactId, contact);
        if (rowsUpdated > 0) {
            message = "Success! The contact has been successfully updated in the address book.";
        } else {
            message = "Failure --- The contact could not be updated in the address book.";
        }
        model.addAttribute("message", message);
        return "editOutcome";
    }
}
