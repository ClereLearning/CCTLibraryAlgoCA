/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import com.cctstudent.cctlibmgmtsys.model.Book;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class BookDao implements Dao<Book> {
        
    private Set<Book> books = new LinkedHashSet<>();   

    @Override
    public Optional<Book> get(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<Book> getAll() {        
        
        try{
            BufferedReader fileCsv = null;
            String filePath = "D:\\Classes\\AlgorithmsContructs\\CA2\\Developing\\CCTLibMgmtSys\\Database\\Books.csv";
            fileCsv = new BufferedReader(new FileReader(filePath));
            String row = null;
            String header = null;
            try {
                while (( row = fileCsv.readLine()) != null) {
                    String[] booksDetail = row.split(",");
                    if(header==null)
                    {
                        header = row;
                    }else{                    
                        books.add(new Book(UUID.fromString(booksDetail[0]),booksDetail[1],booksDetail[2],booksDetail[3],booksDetail[4]));
                    }                    
                    // Player player = new Player(Integer.valueOf(booksDetail[0]),booksDetail[1],booksDetail[2],booksDetail[3]);
                    // teamOne.Players.put(player.getId(), player);
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

    @Override
    public void save(Book g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Book g, String[] infos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Book g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
