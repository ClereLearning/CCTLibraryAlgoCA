/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.FileNameBooksReturned;
import static com.cctstudent.cctlibmgmtsys.constants.Constants.FilesPath;
import com.cctstudent.cctlibmgmtsys.model.BookReturned;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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

/**
 *
 * @author 2022057 Marliclere Santos
 */


// not allowing the same Borrowing Id
/*
 class BookReturnedComparatorById implements Comparator<BookReturned>{

   @Override
    public int compare(BookReturned b1, BookReturned b2) {
       int one =  b1.getBorrowingId().compareTo(b2.getBorrowingId());
       int two =  b1.getReturnedDate().compareTo(b2.getReturnedDate());     
       //return ( ((one + two) != 0 ) ?1:0);
       return one;
    }
 }
*/
/**
 *
 * @author Clere
 */
public class BookReturnedDao {

    private Object System;

    /**
     * Get details about the Book returned by BookReturned 
     * @param id
     * @return
     */
    
    public Optional<BookReturned> get(UUID id) {
        ArrayList<BookReturned> booksReturned = getAll();
        Optional<BookReturned> booksRet = null;

        for (BookReturned bookReturned : booksReturned) 
        {	
            if(!id.toString().isEmpty())
            {
                if((bookReturned.getId().toString().toLowerCase().equals(id.toString().toLowerCase())))
                {		 
                    return Optional.of(bookReturned);
                }
            }
        }  
        return Optional.empty();
    }

    /**
     *  Get all Book returneds
     * @return
     */
    
    public ArrayList<BookReturned> getAll() {
        // NavigableSet<BookReturned> booksReturned = new TreeSet<>(new BookReturnedComparatorById() );
        ArrayList<BookReturned> booksReturned = new ArrayList<>();
        
        try{
            
            BufferedReader fileCsv = null;
            String filePath = FilesPath + FileNameBooksReturned;
            fileCsv = new BufferedReader(new FileReader(filePath));
            String row = null;
            String header = null;
            try {
                while (( row = fileCsv.readLine()) != null) {
                    String[] ReturnsDetails = row.split(",");
                    if(header==null) // avoid the first line 
                    {
                        header = row;
                    }else{                    
                        if(ReturnsDetails[0].trim().length()==36)
                        {             
                            BookReturned b = new BookReturned(
                                    UUID.fromString(ReturnsDetails[0].trim()),
                                    UUID.fromString(ReturnsDetails[1].trim()),
                                    UUID.fromString(ReturnsDetails[2].trim()),
                                    UUID.fromString(ReturnsDetails[3].trim()),
                                    UUID.fromString(ReturnsDetails[4].trim()),
                                    LocalDate.parse(ReturnsDetails[5].trim()), 
                                    LocalTime.parse(ReturnsDetails[6].trim())
                                    );
                            booksReturned.add(b );
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
        
        return booksReturned;
    }

    /**
     *  Get all Books returned by BookID
     * @param bookID
     * @return
     */
    public ArrayList<BookReturned> getAllByBookID(UUID bookID)
    {
            //helping 9
            ArrayList<BookReturned> booksReturneds = getAll();
            ArrayList<BookReturned> ListofBooksRet = new ArrayList<>();
            
            if(!bookID.toString().isEmpty())
            {        
                    for (BookReturned bookReturned : booksReturneds) 
                    {            
                            if(bookReturned.getBookId().equals(bookID))
                            {
                                    ListofBooksRet.add(bookReturned);
                            }            
                    }
            }	
            return ListofBooksRet;	
    }
    
    /**
     * Get all Books returned by Internal Student ID
     * @param StudentId
     * @return
     */
    public ArrayList<BookReturned> getAllByStudent(UUID StudentId)
    {                
        ArrayList<BookReturned> bookReturneds = getAll();
        ArrayList<BookReturned> bookReturnedRet =  new ArrayList<>();
        
        if(!StudentId.toString().isEmpty())
        {        
            for (BookReturned bookReturned : bookReturneds) {            
                if(bookReturned.getStudentId().equals(StudentId))
                {
                    bookReturnedRet.add(bookReturned);
                }            
            }
        }
        
        return bookReturnedRet;
        
    }
    
    /**
     * Get save the new returned book
     * @param b
     * @return
     */

    public Boolean save(BookReturned b) {
        
        String filePath = FilesPath + FileNameBooksReturned;
        File file = new File(filePath);

        try(                
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bwr = new BufferedWriter(fw)){ // to optimize
            bwr.write(b.toDatabase());
            bwr.newLine(); // avoiding \n dueto other kind of operacional system
            bwr.flush();

        }catch(IOException ex){
            //System.out.println(ex.getMessage());
            Logger.getLogger(BookReturnedDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
        }

        return true;
    }
}
