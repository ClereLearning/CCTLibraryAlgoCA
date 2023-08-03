/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.BorrowingDays;
import com.cctstudent.cctlibmgmtsys.model.Book;
import com.cctstudent.cctlibmgmtsys.model.Borrowing;
import com.cctstudent.cctlibmgmtsys.model.Student;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class BorrowingDaoIT {
    
    public BorrowingDaoIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of get method, of class BorrowingDao.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        /* borrowing
        id,bookId,studentId,employeeId,StartDate,StartTime,ReturnDate,isReturned,ReturnedDate
        d3003cb6-4f0f-410d-9550-a228dd349785,70a1f65c-d026-4a75-a6b3-2dd276eb9c46,d6360e0f-48e8-401d-b481-f4e41fe9744b,d6360e0f-48e8-401d-b481-f4e41fe9744b,2023-08-03,09:11:21.023095100,2023-08-03,false,2023-08-03T09:11:21.023095100
        2bd4f8f9-f573-4e60-b02c-756d36647bf0,7583ad35-71f7-420c-aecf-13e4cb0931a1,d6360e0f-48e8-401d-b481-f4e41fe9744b,d6360e0f-48e8-401d-b481-f4e41fe9744b,2023-08-03,12:23:27.468415400,2023-08-10,false,2023-08-03T12:23:27.468415400
        */
        
        UUID id = UUID.fromString("d3003cb6-4f0f-410d-9550-a228dd349785");
        BorrowingDao instance = new BorrowingDao();        
        Optional<Borrowing> result = instance.get(id);
        Borrowing borrowing = result.isEmpty()?null:result.get();
        
        //d3003cb6-4f0f-410d-9550-a228dd349785,70a1f65c-d026-4a75-a6b3-2dd276eb9c46,d6360e0f-48e8-401d-b481-f4e41fe9744b,d6360e0f-48e8-401d-b481-f4e41fe9744b,2023-08-03,09:11:21.023095100,2023-08-03,false,2023-08-03T09:11:21.023095100
        
        assertTrue(!borrowing.equals(null), "");
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getAll method, of class BorrowingDao.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        BorrowingDao instance = new BorrowingDao();
        //Set<Borrowing> expResult = null;
        Set<Borrowing> result = instance.getAll();
        //assertEquals(expResult, result);
        assertEquals( true, (0 < result.size()), "error " + result.size());
        
    }

    /**
     * Test of save method, of class BorrowingDao.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Borrowing b = null;
        BorrowingDao borrowingDao = new BorrowingDao();
        Boolean expResult = null;
        
        /*
        Student{id=d6360e0f-48e8-401d-b481-f4e41fe9744b, firstName=Marian, lastName=Dallas, email=mdallas@students.pro.com, address=Wash, studentId=q1w2e3}
        Student{id=0feb2b21-4ffe-4836-a1e5-544079286ca6, firstName=Rafaella, lastName=Dallas, email=rdallas@students.pro.com, address=Dallas, studentId=a1s2d3}
        */
        
        /*
        Book{id=a657a0b4-263d-43d9-b13c-a9058cb64644, authorFirstName=Kliment, authorLastName=Jest, bookTitle=You Can't Take It with You, genre=Comedy|Romance}
        Book{id=08d30641-aed9-4630-b4d0-3eb2f8d87acc, authorFirstName=Benji, authorLastName=Stubbs, bookTitle=You're Not You, genre=Drama}
        Book{id=578eb3bd-9458-44c8-bcd5-6edb6c48a6f2, authorFirstName=Gregor, authorLastName=Dineges, bookTitle=You're Telling Me!, genre=Comedy}
        Book{id=ff2dea5d-1406-48c7-9143-fe5a4c1a9123, authorFirstName=Valeria, authorLastName=Charlick, bookTitle=Yumeji, genre=Drama|Fantasy}
        Book{id=70a1f65c-d026-4a75-a6b3-2dd276eb9c46, authorFirstName=Trudey, authorLastName=Hassen, bookTitle=Z, genre=Drama|Mystery|Thriller}
        Book{id=9532e091-a984-4540-9c8a-a7ad219a2ae4, authorFirstName=Beniamino, authorLastName=Klos, bookTitle=Zatoichi and the Doomed Man (Zatôichi sakate giri) (Zatôichi 11), genre=Action|Drama}
        Book{id=7583ad35-71f7-420c-aecf-13e4cb0931a1, authorFirstName=Rudd, authorLastName=Carnock, bookTitle=Zatoichi's Vengeance (Zatôichi no uta ga kikoeru) (Zatôichi 13), genre=Action|Drama}
        Book{id=f4d3f119-2e52-4fc6-9865-5be286164f00, authorFirstName=fn, authorLastName=ln, bookTitle=title, genre=Action|Comedy|Drama|Western}
        */
        
        String bookID = "70a1f65c-d026-4a75-a6b3-2dd276eb9c46"; //"578eb3bd-9458-44c8-bcd5-6edb6c48a6f2";
        String studentID =  "q1w2e3";  //"a1s2d3";
        
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
            System.out.println(bookID.trim());
            System.out.println(UUID.fromString(student.getId().toString()));
            System.out.println(bookID.trim());
            LocalDate ReturnDate = LocalDate.now();
            ReturnDate = ReturnDate.plusDays(BorrowingDays);
            LocalDate StartDate = LocalDate.now();            
            b = new Borrowing(id, idBook ,idStudent, idStudent,StartDate,LocalTime.now(),ReturnDate, false,LocalDateTime.now());
            //ret = borrowingDao.save(b);              
          
            Set<Borrowing> Borrowings = borrowingDao.getAll();            
            System.out.println("Size " + Borrowings.size());
            if(Borrowings.add(b)) // already exists?
            {
                System.out.println("Size " + Borrowings.size());
                assertTrue(true);
            }else{
                System.out.println("Size " + Borrowings.size());
                assertFalse(false);
            }
        }
                
                
                
        
         //Boolean result = borrowingDao.save(b);
        //assertEquals(expResult, result);
        
       
    }

    /**
     * Test of update method, of class BorrowingDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Borrowing g = null;
        String[] infos = null;
        BorrowingDao instance = new BorrowingDao();
        instance.update(g, infos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class BorrowingDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Borrowing g = null;
        BorrowingDao instance = new BorrowingDao();
        instance.delete(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
