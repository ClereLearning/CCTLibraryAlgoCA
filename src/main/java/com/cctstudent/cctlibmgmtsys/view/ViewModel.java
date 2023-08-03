/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.view;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.BorrowingDays;
import com.cctstudent.cctlibmgmtsys.dao.BookDao;
import com.cctstudent.cctlibmgmtsys.dao.BookReturnedDao;
import com.cctstudent.cctlibmgmtsys.dao.BorrowingDao;
import com.cctstudent.cctlibmgmtsys.dao.StudentDao;
import com.cctstudent.cctlibmgmtsys.model.Book;
import com.cctstudent.cctlibmgmtsys.model.BookReturned;
import com.cctstudent.cctlibmgmtsys.model.Borrowing;
import com.cctstudent.cctlibmgmtsys.model.Student;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
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
    
    public Optional<Student> GetStudentByStudentID (String studentID )
    {
        StudentDao studentDao = new StudentDao();
        Optional<Student> student = studentDao.getStudentId(studentID);
        return student;   
    }
    
    public static Boolean ExistStudentByStudentID (String studentID )
    {
        StudentDao studentDao = new StudentDao();
        Optional<Student> student = studentDao.getStudentId(studentID);
        if(student!=null)
        {
            return true;
        }else{
            return false;
        }        
    }
    
    public static void BorrowingRegister(String studentID , String bookID )
    {
        //8) Register that a student has borrowed a book 
        System.out.println("Borrowing Book Register");
        BorrowingDao borrowingDao = new BorrowingDao();
        
        BookDao bookDao = new BookDao();
        Optional<Book> bookOpt = bookDao.get(UUID.fromString(bookID.trim()));
        Book book = bookOpt.isEmpty()? null: bookOpt.get();       
        if(book==null)
        {
            System.out.println("Book ID does not exists. Please verify.");
            return;
        }
        StudentDao studentDao = new StudentDao();
        //Optional<Student> student = studentDao.get(UUID.fromString(studentID.trim()));
        Optional<Student> studentOpt = studentDao.getStudentId(studentID.trim());
        Student student = studentOpt.isEmpty()?null:studentOpt.get();
       
         if(student==null)
        {
            System.out.println("Student ID does not exists. Please verify.");
            return;
        }
        
        Boolean ret = false;        
        if((book!=null) && (student!=null))
        {
            System.out.println("Ready to save the Borrowing");
            UUID id = UUID.randomUUID();            
            UUID idBook = UUID.fromString(bookID.trim());
            UUID idStudent = UUID.fromString(student.getId().toString());
            
            //System.out.println(bookID.trim());
            //System.out.println(UUID.fromString(student.getId().toString()));
            //System.out.println(bookID.trim());
            
            LocalDate ReturnDate = LocalDate.now();
            ReturnDate = ReturnDate.plusDays(BorrowingDays);
            LocalDate StartDate = LocalDate.now();
            //StartDate = ReturnDate;
            Borrowing b = new Borrowing(id, idBook ,idStudent, idStudent,StartDate,LocalTime.now(),ReturnDate, false,LocalDateTime.now());
            
            
            
            //Borrowing b = new Borrowing(id,UUID.fromString(bookID.trim()), UUID.fromString(student.getId().toString()),UUID.fromString(student.getId().toString()),LocalDate.now(),LocalTime.now(),LocalDate.now(), false,LocalDateTime.now());
            ret = borrowingDao.save(b);           
        }
        
        if(ret)
        {
            System.out.println("Borrowing Book Registered");
        }else{
            System.out.println("Borrowing Book failure, it was not registered");
        }
    }         
    
    public static void BookReturnRegister(String StudentID, String borrowingID)
    {
         //10) Register that a student has returned a book.
        //String studentID =  "q1w2e3";  //"a1s2d3";
        //String borrowingID = "d3003cb6-4f0f-410d-9550-a228dd349785";        
        
        StudentDao studentDao = new StudentDao();
        //Optional<Student> student = studentDao.get(UUID.fromString(studentID.trim()));
        Optional<Student> studentOpt = studentDao.getStudentId(StudentID.trim());
        Student student = studentOpt.isEmpty()?null:studentOpt.get();
       
         if(student==null)
        {
            System.out.println("Student ID does not exists. Please verify.");
            return;
        }
         
       
        BorrowingDao borrowingDao = new BorrowingDao();
        Optional<Borrowing> boorrowingOpt = borrowingDao.get(UUID.fromString(borrowingID.trim()));
        Borrowing borrowing = boorrowingOpt.isEmpty()? null: boorrowingOpt.get();       
        if(borrowing==null)
        {
            System.out.println("Borrowing ID does not exists. Please verify.");
            return;
        }
        
        
        
        Boolean ret = false;        
        if((student!=null) && (borrowing!=null))
        {
            
            if (!borrowing.getStudentId().equals(student.getId()))
            {
                System.out.println("Student ID does not match with the Student Borrowing ID. Please verify.");
                return;
            }
            
            
            //System.out.println("Ready to save the Returning");
            UUID id = UUID.randomUUID();  
            UUID idBook =  borrowing.getBookId();
            UUID idStudent = UUID.fromString(student.getId().toString());
            UUID idBorrowing = UUID.fromString(borrowingID.trim());
           
            /*
            System.out.println(idBook.toString());
            System.out.println(idStudent.toString());
            System.out.println(idBorrowing.toString());
              */               
            LocalDate StartDate = LocalDate.now();
            
            BookReturned b = new BookReturned(
                                    id,
                                    idBorrowing,
                                    idBook,
                                    idStudent,
                                    idStudent,
                                    StartDate, 
                                    LocalTime.now()
                                    );
            
            
            
            BookReturnedDao BookReturnedDao = new BookReturnedDao();  
            Set<BookReturned> booksReturned = BookReturnedDao.getAll();
           
            if(booksReturned.add(b)) // already exists?
            {       
                BookReturnedDao instance = new BookReturnedDao(); 
                instance.save(b );
                System.out.println("Book Returned. Thank you. ");
            }else{
               System.out.println("The book was already returned ");
            }
        }
    }
    
    public static void getBooksBorrowingByStudent( String StudentId)
    {
        //11) For a specific student, list the books that they have borrowed
        StudentDao studentDao = new StudentDao();        
        Optional<Student> studentOpt = studentDao.getStudentId(StudentId.trim());
        Student student = studentOpt.isEmpty()?null:studentOpt.get();
       
         if(student==null)
        {
            System.out.println("Student ID does not exists. Please verify.");
            return;
        }     

        UUID idStudent = student.getId();
        BorrowingDao borrowingDao = new BorrowingDao();
        
         
        ArrayList<Borrowing> result = borrowingDao.getAllByStudent(idStudent);        
        Book book = null;
        BookDao bookDao = new BookDao();
        
        
        if(result!=null)
        {        
            for( Borrowing b: result)
            {
                Optional<Book> BookOpt = bookDao.get(b.getBookId());
                book = BookOpt.isEmpty()?null:BookOpt.get();                
                if(book!=null)
                {
                     System.out.println(b.getStartDate() + " - " + book);
                }               
            }
        }else{
            System.out.println("Not found");        
        }        
        
    }
    
}
