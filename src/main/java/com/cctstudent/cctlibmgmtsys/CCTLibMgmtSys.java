/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.cctstudent.cctlibmgmtsys;

import com.cctstudent.cctlibmgmtsys.view.ViewModel;
import java.util.Scanner;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class CCTLibMgmtSys {

    public static void main(String[] args) {
        
        int menuOption = 0;
        
        Scanner userInput = new Scanner(System.in);
        
        do{
            menuOption = showMenu(userInput);
            
            switch(menuOption)
            {
                case 1:                                         
                    System.out.println("Option 1 ");
                    break;
                case 2:                     
                    System.out.println("Option 2 ");
                    Option2();
                    break;
                case 3:                     
                    System.out.println("Option 3 ");
                    break;
                 case 4:                     
                    System.out.println("Option 4 ");
                     Option4();
                    break;
                 case 5:                     
                    System.out.println("Option 5 ");
                    Option5();
                    break;                    
                 case 6:                     
                    System.out.println("Option 6 ");
                    Option6();
                    break;
                 case 7:                     
                    System.out.println("Option 7 ");
                    Option7();
                    break;
                 case 8:                     
                    System.out.println("Option 8 ");
                    Option8();
                    break;
                 case 9:                     
                    System.out.println("Option 9 ");
                    break;
                 case 10:                     
                    System.out.println("Option 10 ");
                    Option10();
                    break;
                 case 11:                     
                    System.out.println("Option 11 ");
                    Option11();
                    break;                    
                 case 12:                     
                    System.out.println("Option 12 ");
                    System.out.println("Exiting Thank you ");
                    break;
                 default:
                    System.out.println("Invalid Option ");
                    break;
            }
        }while(menuOption!=12);
    }
    
     public static int showMenu(Scanner input)
    {
        
        System.out.println("***************************MENU*************************************");
        System.out.println("********************Options Select one******************************");        
        System.out.println("1) Register a Employee                                              ");
        System.out.println("2) Register a Student                                               ");
        System.out.println("3) Register a Book                                                  ");
        System.out.println("4) Search for a specific book by title and/or author name.          ");
        System.out.println("5) List all books by title and/or author name alphabetical order.   ");
        System.out.println("6) Search for a specific student by name and/or ID.                 ");
        System.out.println("7) List all students by alphabetical name and/or ID order.          ");
        System.out.println("8) Register that a student has borrowed a book                      ");
        System.out.println("9) Adding Book to Waiting List                                      ");
        System.out.println("10) Register that a student has returned a book.                     ");
        System.out.println("11) For a specific student, list the books that they have borrowed.  ");
        System.out.println("12) Exit                                                             ");
        System.out.println("********************************************************************");
        
        // testing if it is a number
        int invalidOption = -1;
        try{
           return input.nextInt();
        }catch(Exception ex){
            input.next();
           return invalidOption;
        }
    }
    public static void Option2()
     {
        Scanner input = new Scanner(System.in);
                
        System.out.println("Enter the student First Name:");
        String firstName = input.next();
        
        System.out.println("Enter the Student Last Name:");
        String lastName = input.next(); 
               
        System.out.println("Enter the Student email:");
        String email = input.next();
        
        System.out.println("Enter the Student address:");
        String address = input.next();
                
        System.out.println("Enter the Student ID:");
        String studentID = input.next();
        
        if(ViewModel.SaveStudent ( firstName,  lastName,  email,  address, studentID))
        {            
            System.out.println("Student successfully registered"); 
        }else{
             System.out.println("Student was not registered"); 
        }
        
     }
     
     public static void Option4()
     {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the book title or * for (all)");
        String userBookTitle = input.next();
        
        System.out.println("Enter the Author(s) Last Name(s) or * for (all)");
        String userAuthorsLastName = input.next();        
        
        if(("*".equals(userBookTitle.trim())) && ("*".equals(userAuthorsLastName.trim())))
        {
            System.out.println("Please enter a book title or authors last name");
            return;
        }

        if(userAuthorsLastName.contains("*"))         
        {
            userAuthorsLastName = userAuthorsLastName.replace("*", "");
        }
        
        if(userBookTitle.contains("*"))         
        {
            userBookTitle = userBookTitle.replace("*", "");
        }        
        
        ViewModel.ListAllBooksByTitleOrAuthors(userBookTitle.trim(),userAuthorsLastName.trim());
        
        
     }
     
     public static void Option5()
     {
         //5) List all books by title and/or author name alphabetical order.
        ViewModel.ListAllBook();
     }
     
     public static void Option6()
     {
         //6) Search for a specific student by name and/or ID
         Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the student first name or * for (all)");
        String firstName = input.next();
        
        System.out.println("Enter the student ID or * for (all) ");
        String studentID = input.next();        
        
        if(("*".equals(firstName.trim())) && ("*".equals(studentID.trim())))
        {
            System.out.println("Please enter student first name or ID");
            return;
        }

        if(firstName.contains("*"))         
        {
            firstName = firstName.replace("*", "");
        }
        
        if(studentID.contains("*"))         
        {
            studentID = studentID.replace("*", "");
        }        
        
        ViewModel.ListAllStudentByFirstNameOrID(firstName.trim(),studentID.trim());
         
     }
     
     public static void Option7()
     {
        //7) List all students by alphabetical name and/or ID order.   
        ViewModel.ListAllStudent();
     }
     
     public static void Option8()
     {
         //8) Register that a student has borrowed a book
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the book ID");
        String bookID = input.next();
        
        System.out.println("Enter Student ID");
        String studentID = input.next(); 
        
        if(bookID.trim().length()<1)
        {
            System.out.println("Book ID is required");
            return;
        }    
        
        if(bookID.trim().length()!=36)
        {
            System.out.println("Book ID is not correct");
            return;
        }        
        
        if(studentID.trim().length()<1)
        {
            System.out.println("Student ID is required");
            return;
        }           
        
        ViewModel.BorrowingRegister(studentID.trim(),bookID.trim());
     }
     
     public static void Option10()
     {
         //10) Register that a student has returned a book.
        Scanner input = new Scanner(System.in);                         
                 
        System.out.println("Enter the borrowing ID");
        String borrowingID = input.next();
        
        System.out.println("Enter Student ID");
        String studentID = input.next(); 
        
        if(borrowingID.trim().length()<1)
        {
            System.out.println("Borrowing ID is required");
            return;
        }    
        
        if(borrowingID.trim().length()!=36)
        {
            System.out.println("Book ID is not correct");
            return;
        }        
        
        if(studentID.trim().length()<1)
        {
            System.out.println("Student ID is required");
            return;
        }           
        
        ViewModel.BookReturnRegister(studentID.trim(),borrowingID.trim());
     
     }
     
     public static void Option11()
     {
         //11) For a specific student, list the books that they have borrowed
        System.out.println("Listing the books borrowed by Student");
        Scanner input = new Scanner(System.in);                         
        
        System.out.println("Enter Student ID");
        String studentID = input.next(); 
        
        if(studentID.trim().length()<1)
        {
            System.out.println("Student ID is required");
            return;
        }           
        
        ViewModel.getBooksBorrowingByStudent(studentID.trim());
        
         
         
     }
     
}
