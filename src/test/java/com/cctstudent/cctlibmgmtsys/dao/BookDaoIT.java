/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import com.cctstudent.cctlibmgmtsys.model.Book;
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
public class BookDaoIT {
    
    public BookDaoIT() {
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
     * Test of get method, of class BookDao.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        UUID id = null;
        BookDao instance = new BookDao();
        Optional<Book> expResult = null;
        Optional<Book> result = instance.get(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class BookDao.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        BookDao instance = new BookDao();
        //Set<Book> expResult = null;
        Set<Book> result = instance.getAll();
                
        assertTrue(result.size() > 0);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class BookDao.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Book g = null;
        BookDao instance = new BookDao();
        instance.save(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class BookDao.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Book g = null;
        String[] infos = null;
        BookDao instance = new BookDao();
        instance.update(g, infos);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class BookDao.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Book g = null;
        BookDao instance = new BookDao();
        instance.delete(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
