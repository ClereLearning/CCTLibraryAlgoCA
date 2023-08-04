/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.model;

import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class Employee {
    private UUID id;
    private String FirstName;
    private String LastName;
    private String email;
    private String password;

    public Employee(UUID id, String FirstName, String LastName, String email, String password) {
        this.id = id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.email = email;
        this.password = password;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.FirstName);
        hash = 43 * hash + Objects.hashCode(this.LastName);
        hash = 43 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.FirstName, other.FirstName)) {
            return false;
        }
        if (!Objects.equals(this.LastName, other.LastName)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", FirstName=" + FirstName + ", LastName=" + LastName + ", email=" + email + ", password=" + password + '}';
    }
    
    public String toDatabase() {
        return  id + "," + FirstName + "," + LastName + "," + email + "," + password;
    }
}
