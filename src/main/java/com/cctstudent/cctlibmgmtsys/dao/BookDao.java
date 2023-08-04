/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.FileNameBook;
import static com.cctstudent.cctlibmgmtsys.constants.Constants.FilesPath;
import com.cctstudent.cctlibmgmtsys.model.Book;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.NavigableSet;
import java.util.stream.Collectors;

/**
 *
 * @author 2022057 Marliclere Santos
 */
// avoing dublication
class BookComparatorByAuthorFirstName implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthorFirstName().compareTo(o2.getAuthorFirstName());
    }    
}

// organizing by title
class BookComparatorByBookTitle implements Comparator<Book>{

    @Override
    public int compare(Book o1, Book o2) {  
        return o1.getBookTitle().compareTo(o2.getBookTitle());
    }    
}

/**
 *
 * @author Clere
 */
public class BookDao implements Dao<Book> {
        
    /**
     * Get the book by book id
     * @param id
     * @return
     */
    @Override
    public Optional<Book> get(UUID id) {
        Set<Book> books = getAll();
        Optional<Book> booksRet = null;
        
        for (Book book : books) {
            //if(book.getBookTitle().contains(title))
            if(!id.toString().isEmpty())
            {
                if((book.getId().toString().toLowerCase().equals(id.toString().toLowerCase())))
                {
                   // booksRet = Optional.ofNullable(book);
                   // break;
                    return Optional.of(book);
                }
            }
        }         
        
        //return booksRet;
        return Optional.empty();
    }

    /**
     * get / listing all books
     * @return
     */
    @Override
    public Set<Book> getAll() {        
        NavigableSet<Book> books = new TreeSet<>(new BookComparatorByBookTitle() );
        
        try{
            
            BufferedReader fileCsv = null;
            String filePath = FilesPath + FileNameBook;
            fileCsv = new BufferedReader(new FileReader(filePath));
            String row = null;
            String header = null;
            try {
                while (( row = fileCsv.readLine()) != null) {
                    String[] booksDetail = row.split(",");
                    if(header==null) // avoid the first line 
                    {
                        header = row;
                    }else{                    
                        if(booksDetail[0].trim().length()==36)
                        {
                            books.add(new Book(UUID.fromString(booksDetail[0].trim()),booksDetail[1],booksDetail[2],booksDetail[3],booksDetail[4]));
                        }
                    }                                        
                }
                fileCsv.close();
            } catch (IOException ex) {
                Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return books;
    }
    
    /**
     * get / listing all books organized by title and author last name
     * @return
     */
    public ArrayList<Book> getAllByTitleAndAuthor() 
     {  
         
         Set<Book> books = getAll();
         Comparator<Book> compareByTitleAndAuthor = Comparator
                 .comparing(Book::getBookTitle)
                 .thenComparing(Book::getAuthorLastName);
         
         ArrayList<Book> sortedBooks = (ArrayList<Book>) books.stream()
                 .sorted(compareByTitleAndAuthor)
                 .collect(Collectors.toList());
                 //.collect(collector)
           return sortedBooks;
     }
     
    /**
     * get all book by title or author 's last name
     * @param title
     * @param authorsLastName
     * @return
     */
    public ArrayList<Book> getAllByTitleOrAuthors(String title, String authorsLastName)
     {
         
         //4) Search for a specific book by title and/or author name
         Set<Book> books = getAll();
         ArrayList<Book> booksRet = new ArrayList<>();
        for (Book book : books) {
            //if(book.getBookTitle().contains(title))
            if((!authorsLastName.isEmpty()) && (!title.isEmpty()))
            {
                if((book.getBookTitle().toLowerCase().startsWith(title.toLowerCase())) && (book.getAuthorLastName().toLowerCase().startsWith(authorsLastName.toLowerCase())))
                {
                    booksRet.add(book);
                }
            }else{
                
                if((!authorsLastName.isEmpty()) && (title.isEmpty()))
                {
                    if(book.getAuthorLastName().toLowerCase().startsWith(authorsLastName.toLowerCase()))
                    {
                        booksRet.add(book);
                    }
                }else{
                    if((authorsLastName.isEmpty()) && (!title.isEmpty()))
                    {
                        if(book.getBookTitle().toLowerCase().startsWith(title.toLowerCase()))
                        {
                            booksRet.add(book);
                        }
                    }
                }                            
            }
        }         
         return booksRet;
         
     }
         
    /**
     * saving the new book
     * @param g
     * @return
     */
    @Override
    public Boolean save(Book g) {
        
        Set<Book> books = getAll();
        
        if(books.add(g)) //testing if the book already exists
        {        
            String filePath = FilesPath + FileNameBook;
            File file = new File(filePath);
            try(
                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bwr = new BufferedWriter(fw)){ // to optimize            
                bwr.write(g.toDatabase());
                bwr.newLine(); // avoiding \n dueto other kind of operacional system            
                bwr.flush();
                
            }catch(IOException ex){
                Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }        
        }else{
            return false;
        }
        
        return true;
    }
}
