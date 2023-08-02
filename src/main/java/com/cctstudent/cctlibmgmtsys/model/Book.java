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
public class Book  {
    private UUID id;
    private String authorFirstName;
    private String authorLastName;
    private String bookTitle;
    private String genre;   

    public Book(UUID id, String authorFirstName, String authorLastName, String bookTitle, String genre) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.bookTitle = bookTitle;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.authorFirstName);
        hash = 73 * hash + Objects.hashCode(this.authorLastName);
        hash = 73 * hash + Objects.hashCode(this.bookTitle);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.authorFirstName, other.authorFirstName)) {
            return false;
        }
        if (!Objects.equals(this.authorLastName, other.authorLastName)) {
            return false;
        }
        return Objects.equals(this.bookTitle, other.bookTitle);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", authorFirstName=" + authorFirstName + ", authorLastName=" + authorLastName + ", bookTitle=" + bookTitle + ", genre=" + genre + '}';
    }
    
    
    public String toDatabase()
    {
        return  id.toString() + "," + authorFirstName + "," + authorLastName + "," + bookTitle + "," + genre ;
    }
 
}
