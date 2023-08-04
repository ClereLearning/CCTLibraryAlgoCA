/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;
import java.util.*;
/**
 *
 * @author 2022057 Marliclere Santos
 */
public interface Dao<T> 
{
    Optional<T> get(UUID  id); //getting a specific record by ID - optional avoiding pointer null exception
    Set<T> getAll(); // return all from file
    Boolean save(T g);  //g about generics 
}
