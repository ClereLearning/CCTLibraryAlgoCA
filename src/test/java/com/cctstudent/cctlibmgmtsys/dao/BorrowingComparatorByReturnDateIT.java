/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import com.cctstudent.cctlibmgmtsys.model.Borrowing;
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
public class BorrowingComparatorByReturnDateIT {
    
    public BorrowingComparatorByReturnDateIT() {
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
     * Test of compare method, of class BorrowingComparatorByReturnDate.
     */
    @Test
    public void testCompare() {
        System.out.println("compare");
        Borrowing b1 = null;
        Borrowing b2 = null;
        BorrowingComparatorByReturnDate instance = new BorrowingComparatorByReturnDate();
        int expResult = 0;
        int result = instance.compare(b1, b2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
