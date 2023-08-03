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

 class BookReturnedComparatorByDate implements Comparator<BookReturned>{

   @Override
    public int compare(BookReturned b1, BookReturned b2) {
       int one =  b1.getBorrowingId().compareTo(b2.getBorrowingId());
       int two =  b1.getReturnedDate().compareTo(b2.getReturnedDate());     
       return ( ((one + two) != 0 ) ?1:0);
    }
 }
public class BookReturnedDao implements Dao<BookReturned> {

    private Object System;

    @Override
    public Optional<BookReturned> get(UUID id) {
        Set<BookReturned> booksReturned = getAll();
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

    @Override
    public Set<BookReturned> getAll() {
         NavigableSet<BookReturned> booksReturned = new TreeSet<>(new BookReturnedComparatorByDate() );
        
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

    @Override
    public Boolean save(BookReturned b) {
        
        
        Set<BookReturned> booksReturned = getAll();
        if(booksReturned.add(b)) // already exists?
        {
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
        }else{
            //System.out.println("x");
           return false;
        }

        return true;
    }

    @Override
    public void update(BookReturned g, String[] infos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(BookReturned g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
