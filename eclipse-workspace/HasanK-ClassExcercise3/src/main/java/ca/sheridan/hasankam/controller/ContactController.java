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

    @GetMapping("/index")
    public String index() {
        return "index";
    }
    // Mapping for Access Denied
    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }

    @GetMapping("/AddContact/contactDataInput")
    public String contactDataInput(Model model) {
        model.addAttribute("contact", new Contact());
        return "AddContact/contactDataInput";
    }

    @PostMapping("/AddContact/contactDataInput")
    public String addContact(Model model, @ModelAttribute Contact contact) {
        long rows = cda.addContact(contact);
        model.addAttribute("message", rows > 0 ? "Contact added successfully!" : "Failed to add contact.");
        return "AddContact/contactAddConfirm";
    }

    @GetMapping("/ListContacts/listContacts")
    public String listContacts(Model model) {
        List<Contact> contacts = cda.selectContacts();
        model.addAttribute("contacts", contacts);
        return "ListContacts/listContacts";
    }

    @GetMapping("/EditContact/editableListOfContacts")
    public String editableListOfContacts(Model model) {
        List<Contact> contacts = cda.selectContacts();
        model.addAttribute("contacts", contacts);
        return "EditContact/editableListOfContacts";
    }

    @GetMapping("/EditContact/editContact/{id}")
    public String editContact(@PathVariable("id") Integer id, Model model) {
        Contact contact = cda.selectContactById(id);
        if (contact == null) {
            model.addAttribute("error", "Contact not found.");
            return "error";
        }
        model.addAttribute("contact", contact);
        return "EditContact/editContactData";
    }

    @PostMapping("/EditContact/updateContact")
    public String updateContact(Model model, @ModelAttribute Contact contact) {
        long rows = cda.updateContact(contact);
        model.addAttribute("message", rows > 0 ? "Contact updated successfully!" : "Failed to update contact.");
        return "EditContact/editOutcome";
    }
}
