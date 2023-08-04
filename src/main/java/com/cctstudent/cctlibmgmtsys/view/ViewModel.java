/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.view;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.BorrowingDays;
import com.cctstudent.cctlibmgmtsys.dao.BookDao;
import com.cctstudent.cctlibmgmtsys.dao.BookReturnedDao;
import com.cctstudent.cctlibmgmtsys.dao.BorrowingDao;
import com.cctstudent.cctlibmgmtsys.dao.EmployeeDao;
import com.cctstudent.cctlibmgmtsys.dao.StudentDao;
import com.cctstudent.cctlibmgmtsys.model.Book;
import com.cctstudent.cctlibmgmtsys.model.BookReturned;
import com.cctstudent.cctlibmgmtsys.model.Borrowing;
import com.cctstudent.cctlibmgmtsys.model.Employee;
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
    
    /**
     * List all books by title and/or author name alphabetical order
     */
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
    
    /**
     * Search for a specific book by title and/or author name
     * @param title
     * @param authorsLastName
     */
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
    
    /**
     * Register a Student 
     * @param firstName
     * @param lastName
     * @param email
     * @param address
     * @param studentID
     * @return
     */
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
       
        StudentDao studentDao = new StudentDao();  
       
       Optional<Student> studentOpt = studentDao.getByEmail(email);
        Student student = studentOpt.isEmpty()?null:studentOpt.get();  
        
        if (student == null){            
            UUID id = UUID.randomUUID();
            return studentDao.save(new Student(id, firstName, lastName, email, address,studentID));        
        }else{
            System.out.println("Student already exists");
            return false;
        }                 
        
    }
    
    /**
     * Search for a specific student by name and/or ID
     * @param firstName
     * @param studentID
     */
    public static void ListAllStudentByFirstNameOrID(String firstName, String studentID)
    {
        //6) Search for a specific student by name and/or ID
        
        System.out.println("Search for a specific student by name and/or ID");
        StudentDao instance = new StudentDao();        
        ArrayList<Student> result = instance.getAllStudentByFirstNameOrID(firstName,studentID );
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
    
    /**
     * List all students by alphabetical name and/or ID order. 
     */
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
    
    /**
     * Returning Student by ID
     * @param studentID
     * @return
     */
    private static Student GetStudentByStudentID (String studentID )
    {
        StudentDao studentDao = new StudentDao();        
        Optional<Student> studentOpt = studentDao.getStudentId(studentID.trim());
        Student student = studentOpt.isEmpty()?null:studentOpt.get();        
        return student;   
    }
    
    /**
     * Verify if the student exists by ID
     * @param studentID
     * @return
     */
    private static Boolean ExistStudentByStudentID (String studentID )
    {
        Student student = GetStudentByStudentID(studentID);
         if(student==null)
        {    
            return false;
        }else{
            return true;
         }    
    }
    
    /**
     * Register that a student has borrowed a book
     * @param studentID
     * @param bookID
     */
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
        Student student = GetStudentByStudentID(studentID);
        
        
         if(student==null)
        {
            System.out.println("Student ID does not exists. Please verify.");
            return;
        }
         
         
        //Checking if the book is available
        UUID idBook = UUID.fromString(bookID);
        ArrayList<Borrowing> resultB = borrowingDao.getAllByBookID(idBook);
        
        long borrowings = 0;
        if(resultB!=null)
        {        
            borrowings = resultB.size();            
        }        
        
        BookReturnedDao bookReturnedDao = new BookReturnedDao();
        ArrayList<BookReturned> resultR = bookReturnedDao.getAllByBookID(idBook);
        
       
        
        long returneds = 0;
        if(resultR!=null)
        {        
            returneds = resultR.size();            
        }
        
        // System.out.println("b :" + borrowings);
        //System.out.println("r :" + returneds);
              
        if(borrowings > returneds )
        {
            System.out.println("Book not available. Utlize a waiting List menu 9 ");
            return;            
        }
        
        
        
        
        Boolean ret = false;        
        if((book!=null) && (student!=null))
        {
            //System.out.println("Ready to save the Borrowing");
            UUID id = UUID.randomUUID();                        
            UUID idStudent = UUID.fromString(student.getId().toString());
            
            LocalDate ReturnDate = LocalDate.now();
            ReturnDate = ReturnDate.plusDays(BorrowingDays);
            LocalDate StartDate = LocalDate.now();
            
            Borrowing b = new Borrowing(id, idBook ,idStudent, idStudent,StartDate,LocalTime.now(),ReturnDate, false,LocalDateTime.now());
           
            ret = borrowingDao.save(b);           
        }
        
        if(ret)
        {
            System.out.println("Borrowing Book Registered");
        }else{
            System.out.println("Borrowing Book failure, it was not registered");
        }
    }         
    
    /**
     * Register that a student has returned a book
     * @param StudentID
     * @param borrowingID
     */
    public static void BookReturnRegister(String StudentID, String borrowingID)
    {
         //10) Register that a student has returned a book.
        //String studentID =  "q1w2e3";  //"a1s2d3";
        //String borrowingID = "d3003cb6-4f0f-410d-9550-a228dd349785";    
        
        Student student = GetStudentByStudentID(StudentID);
       
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
            ArrayList<BookReturned> booksReturned = BookReturnedDao.getAll();
           
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
    
    /**
     * For a specific student, list the books that they have borrowed
     * @param StudentId
     */
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
            if(result.size()==0)
            {
                System.out.println("Does not exists result for this Student.");
                return;
            }
            
            for( Borrowing b: result)
            {
                Optional<Book> BookOpt = bookDao.get(b.getBookId());
                book = BookOpt.isEmpty()?null:BookOpt.get();                
                if(book!=null)
                {
                     System.out.println( " Date: "+ b.getStartDate() + " - Borrowing ID: " + b.getId() + " - " +  book);
                }               
            }
        }else{
            System.out.println("Not found");        
        }        
        
    }
    
    /**
     * Adding Book to Waiting List
     * @param studentID
     * @param bookID
     */
    public static void WaitingListRegister(String studentID, String bookID)
    {
        //9) Adding Book to Waiting List   
        
        
        StudentDao studentDao = new StudentDao();        
        Optional<Student> studentOpt = studentDao.getStudentId(studentID.trim());
        Student student = studentOpt.isEmpty()?null:studentOpt.get();
       
         if(student==null)
        {
            System.out.println("Student ID does not exists. Please verify.");
            return;
        }     
        UUID idStudent = student.getId();
        
        
        BorrowingDao borrowingDao = new BorrowingDao();        
        
        UUID idBook = UUID.fromString(bookID);
        ArrayList<Borrowing> resultB = borrowingDao.getAllByBookID(idBook);
        
        long borrowings = 0;
        if(resultB!=null)
        {        
            borrowings = resultB.size();            
        }        
        
        BookReturnedDao bookReturnedDao = new BookReturnedDao();
        ArrayList<BookReturned> resultR = bookReturnedDao.getAllByBookID(idBook);
        
        long returneds = 0;
        if(resultR!=null)
        {        
            returneds = resultR.size();            
        }
              
        if(borrowings > returneds )
        {
            System.out.println("Book not available. Lets enter in a waiting List");
            System.out.println("Waiting List - it is only a simulation - sorry for now");
            //go for waiting list
        }else{
            System.out.println("Book is available. Make a regular borrowing menu 8 ");
        }       
        
    }
    
    /**
     * For a specific student, list the books that they have borrowed
     * @param StudentId
     */
    public static void getBooksReturnedByStudent( String StudentId)
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
        BookReturnedDao bookReturnedDAO = new BookReturnedDao();
        
        
         
        ArrayList<BookReturned> result = bookReturnedDAO.getAllByStudent(idStudent);        
        Book book = null;
        BookDao bookDao = new BookDao();
        
        
        if(result!=null)
        {   
            
            if(result.size()==0)
            {
                System.out.println("Does not exists result for this Student.");
                return;
            }
            
            for( BookReturned b: result)
            {
                Optional<Book> BookOpt = bookDao.get(b.getBookId());
                book = BookOpt.isEmpty()?null:BookOpt.get();                
                if(book!=null)
                {
                     System.out.println( " Date: "+ b.getReturnedDate() + " - Retuned ID: " + b.getId() + " - Borrowing ID: " + b.getBorrowingId() + " - " +  book);
                }               
            }
            
            
            
        }else{
            System.out.println("Not found");        
        }        
        
    }
    
    /**
     * Register a Employee
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     * @return
     */
    public static Boolean SaveEmployee (String firstName, String lastName, String email, String password) {
        
       // 2) Register a Employee 
       System.out.println("Registering a Employee"); 
       
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
       
       if(password.trim().length()<1){
           System.out.println("Password is required");
           return false;
       }       
      
        EmployeeDao employeeDao = new EmployeeDao();     
        
        
        Optional<Employee> employeeOpt = employeeDao.getByEmail(email);
        Employee employee = employeeOpt.isEmpty()?null:employeeOpt.get();  
        
        if (employee == null){            
            UUID id = UUID.randomUUID();
            return employeeDao.save(new Employee(id, firstName, lastName, email, password));
        }else{
           System.out.println("Employee already exists ");
           return false;
        }
        
    }
    
}
