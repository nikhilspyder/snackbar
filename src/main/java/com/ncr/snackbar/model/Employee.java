package com.ncr.snackbar.model;

import org.springframework.stereotype.Component;

@Component
public class Employee {

private String quickLookId;
private String name;
//private String userName;
private String email;
private String role;
private String password;
public String getQuickLookId() {
    return quickLookId;
}
public void setQuickLookId(String id) {
    this.quickLookId = id;
}
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}
public void setEmail(String email) {
    this.email = email;
}
public String getEmail() {
    return email;
}
public void setPassword(String password) {
    this.password=password;
}
public String getPassword() {
    return password;
}

public String getRole() {
    return role;
}
public void setRole(String role) {
    this.role = role;
}
public static Employee create(Employee employee) {
    // Person person = new Person();
     /*person.setFirstName(p.firstName);
     person.setLastName(p.lastName);
     person.setAddress(p.address);*/
     return employee;
 }
}