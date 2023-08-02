/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.view;

import com.cctstudent.cctlibmgmtsys.dao.BookDao;
import com.cctstudent.cctlibmgmtsys.dao.StudentDao;
import com.cctstudent.cctlibmgmtsys.model.Book;
import com.cctstudent.cctlibmgmtsys.model.Student;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class ViewModel {
    
    
    public static void ListAllBook()
    {
        //5) List all books by title and/or author name alphabetical order.
        
        System.out.println("Listing all books by title and/or author name alphabetical order");
        BookDao instance = new BookDao();                
        ArrayList<Book> result = instance.getAllByTitleAndAuthor();
        
        if(result!=null)
        {
            for( Book b: result)
            {
                System.out.println(b.toString());
            }                
        }else{
            System.out.println("Not found");
        }
    }
    
    
    public static void ListAllBooksByTitleOrAuthors(String title, String authorsLastName)
    {
        //4) Search for a specific book by title and/or author name
        
        System.out.println("Listing all books for a specific book by title and/or author name");
        BookDao instance = new BookDao();                
        ArrayList<Book> result = instance.getAllByTitleOrAuthors(title,authorsLastName );
        if(result!=null)
        {            
            for( Book b: result)
            {
                System.out.println(b.toString());
            }             
        }else{
            System.out.println("Not found");
        }
            
    }
    
    public static Boolean SaveStudent (String firstName, String lastName, String email, String address, String studentID) {
        
       // 2) Register a Student 
       System.out.println("Register a Student"); 
       if(firstName.trim().length()<1){
           System.out.println("First Name is required");
           return false;
       }
       
        if(lastName.trim().length()<1){
           System.out.println("Last Name is required");
           return false;
       }
        
         if(email.trim().length()<1){
           System.out.println("Email is required");
           return false;
       }
       
       if(address.trim().length()<1){
           System.out.println("Address is required");
           return false;
       }
       
       if(studentID.trim().length()<1){
           System.out.println("Student ID is required");
           return false;
       }
        StudentDao instance = new StudentDao();       
        UUID id = UUID.randomUUID();
        return instance.save(new Student(id, firstName, lastName, email, address,studentID));
    }
    
    public static void ListAllStudentByFirstNameOrID(String firstName, String studentID)
    {
        //6) Search for a specific student by name and/or ID
        
        System.out.println("Search for a specific student by name and/or ID");
        StudentDao instance = new StudentDao();        
        ArrayList<Student> result = instance.getAllByTitleOrAuthors(firstName,studentID );
        if(result!=null)
        {            
            for( Student s: result)
            {
                System.out.println(s.toString());
            }             
        }else{
            System.out.println("Not found");
        }            
    }
    
    public static void ListAllStudent()
    {
        //7) List all students by alphabetical name and/or ID order.        
        System.out.println("Listing all students by alphabetical name and/or ID order");
        StudentDao instance = new StudentDao();        
        ArrayList<Student> result = instance.getAllOrderedByNames();
        if(result!=null)
        {
            for( Student s: result)
            {
                System.out.println(s.toString());
            }
        }else{
            System.out.println("Not found");
        }
    }
    
}
