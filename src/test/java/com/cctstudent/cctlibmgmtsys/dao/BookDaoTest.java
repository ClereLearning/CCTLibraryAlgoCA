/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import com.cctstudent.cctlibmgmtsys.model.Book;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class BookDaoTest {
   
     
    /**
     * Test of getAll method, of class BookDao.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        BookDao instance = new BookDao();
        //Set<Book> expResult = null;
        Set<Book> result = instance.getAll();
        assertFalse(result.isEmpty());        
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype. ");
    }
}
