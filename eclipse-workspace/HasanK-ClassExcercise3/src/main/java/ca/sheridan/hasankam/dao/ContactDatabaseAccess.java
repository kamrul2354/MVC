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

    public long addContact(Contact contact) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String insert = "INSERT INTO contact (name, email, phone) VALUES (:name, :email, :phone)";
        namedParameters.addValue("name", contact.getName());
        namedParameters.addValue("email", contact.getEmail());
        namedParameters.addValue("phone", contact.getPhone());
        return jdbc.update(insert, namedParameters);
    }

    public List<Contact> selectContacts() {
        String query = "SELECT * FROM contact";
        return jdbc.query(query, new MapSqlParameterSource(), new BeanPropertyRowMapper<>(Contact.class));
    }

    public Contact selectContactById(int id) {
        String query = "SELECT * FROM contact WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource().addValue("id", id);
        List<Contact> contacts = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<>(Contact.class));
        return contacts.isEmpty() ? null : contacts.get(0);
    }

    public long updateContact(Contact contact) {
        String update = "UPDATE contact SET name = :name, email = :email, phone = :phone WHERE id = :id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("id", contact.getId())
            .addValue("name", contact.getName())
            .addValue("email", contact.getEmail())
            .addValue("phone", contact.getPhone());
        return jdbc.update(update, namedParameters);
    }
}
