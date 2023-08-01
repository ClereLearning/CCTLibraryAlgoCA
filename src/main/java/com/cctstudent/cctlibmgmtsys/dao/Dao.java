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
    Optional<T> get(UUID  id);
    Set<T> getAll();
    void save(T g);
    void update(T g, String[] infos);
    void delete(T g);    
}
