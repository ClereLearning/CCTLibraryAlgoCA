/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.FileNameBorrowing;
import static com.cctstudent.cctlibmgmtsys.constants.Constants.FilesPath;
import com.cctstudent.cctlibmgmtsys.model.Borrowing;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author 2022057 Marliclere Santos
 */

//Not allowing a same book in the same day 
/*
 class BorrowingComparatorByReturnDate implements Comparator<Borrowing>{

   @Override
    public int compare(Borrowing b1, Borrowing b2) {
       int one =  b1.getBookId().compareTo(b2.getBookId());
       int two =  b1.getStartDate().compareTo(b2.getStartDate());
       //int three = b1.getId().compareTo(b2.getId());
       //int three = b1.getStudentId().compareTo(b2.getStudentId()); 
       //int four = b1.getStartTime().compareTo(b2.getStartTime());
       //System.out.println("Book " + b1.getBookId() + " -> " + one);
       //System.out.println("Date " + b1.getStartDate() + " -> " + two);
       //System.out.println("Id " + b1.getBookId() + " -> " + three);              
       //return ( ((one + two + three) != 0 ) ?1:0);       
       //return ( ((three == -1) || (one == -1) || ( (one + two) != 0 )) ?1:0);
       return (((one + two) != 0 ) ?1:0);
       
    }
 }
*/

public class BorrowingDao {

    /**
     * Get a book by ID
     * @param id
     * @return
     */
    
    public Optional<Borrowing> get(UUID id) {
        ArrayList<Borrowing> borrowings = getAll();
        Optional<Borrowing> booksRet = null;

        for (Borrowing borrowing : borrowings) {	
                if(!id.toString().isEmpty())
                {
                        if((borrowing.getId().toString().toLowerCase().equals(id.toString().toLowerCase())))
                        {		 
                                return Optional.of(borrowing);
                        }
                }
        }         

        return Optional.empty();
    }

    /**
     * Get all borrowing
     * @return
     */
 
    public ArrayList<Borrowing> getAll() {
        //NavigableSet<Borrowing> borrowings = new TreeSet<>(new BorrowingComparatorByReturnDate() );
        
        ArrayList<Borrowing>  borrowings = new ArrayList<Borrowing>();
        
        try{
            
            BufferedReader fileCsv = null;
            String filePath = FilesPath + FileNameBorrowing;
            fileCsv = new BufferedReader(new FileReader(filePath));
            String row = null;
            String header = null;
            try {
                while (( row = fileCsv.readLine()) != null) {
                    String[] borrowingDetail = row.split(",");
                    if(header==null) // avoid the first line 
                    {
                        header = row;
                    }else{                    
                        if(borrowingDetail[0].trim().length()==36)
                        {                                                        
                            borrowings.add(new Borrowing( UUID.fromString(borrowingDetail[0].trim()) ,  UUID.fromString(borrowingDetail[1].trim()), UUID.fromString(borrowingDetail[2].trim()), UUID.fromString(borrowingDetail[3].trim()), LocalDate.parse(borrowingDetail[4]), LocalTime.parse(borrowingDetail[5].trim()), LocalDate.parse(borrowingDetail[6].trim()),Boolean.valueOf(borrowingDetail[7].trim()), LocalDateTime.parse( borrowingDetail[8].trim()) ) );
                        }
                    }                                        
                }
                fileCsv.close();
            } catch (IOException ex) {
                Logger.getLogger(BorrowingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BorrowingDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return borrowings;
    }

    /**
     * Get all borrowing by Internal Student ID
     * @param StudentId
     * @return
     */
    public ArrayList<Borrowing> getAllByStudent(UUID StudentId)
    {
        BorrowingDao instance = new BorrowingDao();        
        ArrayList<Borrowing> borrowings = instance.getAll();
        ArrayList<Borrowing> borrowingRet =  new ArrayList<>();
        
        if(!StudentId.toString().isEmpty())
        {        
            for (Borrowing borrowing : borrowings) {            
                if(borrowing.getStudentId().equals(StudentId))
                {
                    borrowingRet.add(borrowing);
                }            
            }
        }
        
        return borrowingRet;
        
    }
    
    //helping 9

    /**
     * Get all borrowing by Book ID
     * @param bookID
     * @return
     */
     public ArrayList<Borrowing> getAllByBookID(UUID bookID)
    {
        BorrowingDao instance = new BorrowingDao();        
        ArrayList<Borrowing> borrowings = instance.getAll();
        ArrayList<Borrowing> borrowingRet =  new ArrayList<>();
        
        if(!bookID.toString().isEmpty())
        {        
            for (Borrowing borrowing : borrowings) {            
                if(borrowing.getBookId().equals(bookID))
                {
                    borrowingRet.add(borrowing);
                }            
            }
        }
        
        return borrowingRet;
        
    }
    
    /**
     * Save the new info about Borrowing book
     * @param b
     * @return
     */
    
    public Boolean save(Borrowing b) {
        ArrayList<Borrowing> Borrowings = getAll();       
        String filePath = FilesPath + FileNameBorrowing;
        File file = new File(filePath);            

        try(
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bwr = new BufferedWriter(fw)){ // to optimize
            bwr.write(b.toDatabase());
            bwr.newLine(); // avoiding \n dueto other kind of operacional system
            bwr.flush();

        }catch(IOException ex){

            Logger.getLogger(BorrowingDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }
       
        return true;
    }    
    
}
