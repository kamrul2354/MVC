package ca.sheridan.hasankam.dao;

import ca.sheridan.hasankam.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ContactDatabaseAccess {
    
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;

    // Method to add a new contact
    public int addContact(Contact contact) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String insert = "INSERT INTO contact (firstName, lastName, phoneNumber, email) VALUES (:firstName, :lastName, :phoneNumber, :email)";
        namedParameters.addValue("firstName", contact.getFirstName());
        namedParameters.addValue("lastName", contact.getLastName());
        namedParameters.addValue("phoneNumber", contact.getPhoneNumber());
        namedParameters.addValue("email", contact.getEmail());
        return jdbc.update(insert, namedParameters);
    }

    // Method to get the list of all contacts
    public List<Contact> selectContacts() {
        String query = "SELECT * FROM contact";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Contact.class));
    }

    // Method to get a specific contact by ID
    public Contact selectContactById(int id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "SELECT * FROM contact WHERE id = :id";
        namedParameters.addValue("id", id);
        List<Contact> contacts = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Contact.class));
        return contacts.isEmpty() ? null : contacts.get(0);
    }

    // Method to update a contact by ID
    public long updateContactById(int contactId, Contact contact) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String update = "UPDATE contact SET firstName = :firstName, lastName = :lastName, phoneNumber = :phoneNumber, email = :email WHERE id = :contactId";
        namedParameters.addValue("contactId", contactId);
        namedParameters.addValue("firstName", contact.getFirstName());
        namedParameters.addValue("lastName", contact.getLastName());
        namedParameters.addValue("phoneNumber", contact.getPhoneNumber());
        namedParameters.addValue("email", contact.getEmail());
        return jdbc.update(update, namedParameters);
    }
}
