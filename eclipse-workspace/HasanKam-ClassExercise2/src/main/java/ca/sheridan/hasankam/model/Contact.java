package ca.sheridan.hasankam.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private int id; // Changed from int to Integer
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    // No-arg constructor
    public Contact() {}

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
