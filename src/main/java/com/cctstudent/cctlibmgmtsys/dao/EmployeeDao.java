/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.FileNameEmployee;
import static com.cctstudent.cctlibmgmtsys.constants.Constants.FilesPath;
import com.cctstudent.cctlibmgmtsys.model.Employee;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

// avoiding duplication - email is the key
 class EmployeeComparatorByEmail implements Comparator<Employee>{

    @Override
    public int compare(Employee e1, Employee e2) {  
        return e1.getEmail().compareTo(e2.getEmail());
    }    
}

/**
 *
 * @author Clere
 */
public class EmployeeDao implements Dao<Employee>{

    /**
     * get Employee by Internal ID
     * @param id
     * @return
     */
    @Override
    public Optional<Employee> get(UUID id) {
        
        Set<Employee> employees = getAll();        
        for (Employee employee : employees) {            
                if(!id.toString().isEmpty())
                {
                    if(employee.getId().toString().toLowerCase().equals(id.toString().toLowerCase()))
                    {
                        return Optional.of(employee);                    
                    }            
                }
        } 
        return Optional.empty();
    }
    
    /**
     * get Employee by Email
     * @param email
     * @return
     */
     public Optional<Employee> getByEmail(String email) {
        
        Set<Employee> employees = getAll();        
        for (Employee employee : employees) {            
                if(!email.toString().isEmpty())
                {
                    if(employee.getEmail().toLowerCase().equals(email.toLowerCase()))
                    {
                        return Optional.of(employee);                    
                    }            
                }
        } 
        return Optional.empty();
    }

    /**
     * get / list all Employees
     * @return
     */
    @Override
    public Set<Employee> getAll() {
        NavigableSet<Employee> employees = new TreeSet<>(new EmployeeComparatorByEmail() );
        
	try{
		
		BufferedReader fileCsv = null;
		String filePath = FilesPath + FileNameEmployee;
		fileCsv = new BufferedReader(new FileReader(filePath));
		String row = null;
		String header = null;
		try {
			while (( row = fileCsv.readLine()) != null) {
				String[] employeesDetail = row.split(",");
				if(header==null) // avoid the first line 
				{
					header = row;
				}else{                    
					if(employeesDetail[0].trim().length()==36) //UUID length
					{
						employees.add(new Employee(UUID.fromString(employeesDetail[0].trim()),employeesDetail[1],employeesDetail[2],employeesDetail[3],employeesDetail[4]));
					}
				}                                        
			}
			fileCsv.close();
		} catch (IOException ex) {
			Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
		}
	} catch (FileNotFoundException ex) {
		Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
	}

        return employees;
    }

    /**
     * save a new Employee
     * @param e
     * @return
     */
    @Override
    public Boolean save(Employee e) {
        Set<Employee> employees = getAll();
        if(employees.add(e)) //testing if it already exists
        {
                String filePath = FilesPath + FileNameEmployee;
                File file = new File(filePath);
                try(
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bwr = new BufferedWriter(fw)){ // to optimize
                    bwr.write(e.toDatabase());
                    bwr.newLine(); // avoiding \n dueto other kind of operacional system
                    bwr.flush();

                }catch(IOException ex){
                    Logger.getLogger(EmployeeDao.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
        }else{
                return false;
        }
        return true;
    }
    
}
