/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.dao;

import static com.cctstudent.cctlibmgmtsys.constants.Constants.FileNameStudent;
import static com.cctstudent.cctlibmgmtsys.constants.Constants.FilesPath;
import com.cctstudent.cctlibmgmtsys.model.Student;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 *
 * @author 2022057 Marliclere Santos
 */

 class StudentComparatorByName implements Comparator<Student>{

    @Override
    public int compare(Student s1, Student s2) {  
        return s1.getFirstName().compareTo(s2.getFirstName());
    }    
}
public class StudentDao implements Dao<Student> {

    @Override
    public Optional<Student> get(UUID id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<Student> getAll() {
        NavigableSet<Student> students = new TreeSet<>(new StudentComparatorByName() );
        
        try{
            
            BufferedReader fileCsv = null;
            String filePath = FilesPath + FileNameStudent;
            fileCsv = new BufferedReader(new FileReader(filePath));
            String row = null;
            String header = null;
            try {
                while (( row = fileCsv.readLine()) != null) {
                    String[] studentsDetail = row.split(",");
                    if(header==null) // avoid the first line 
                    {
                        header = row;
                    }else{                    
                        if(studentsDetail[0].trim().length()==36) //UUID length
                        {
                            students.add(new Student(UUID.fromString(studentsDetail[0].trim()),studentsDetail[1],studentsDetail[2],studentsDetail[3],studentsDetail[4],studentsDetail[5]));
                        }
                    }                                        
                }
                fileCsv.close();
            } catch (IOException ex) {
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BookDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return students;
    }

    @Override
    public Boolean save(Student g) {
        
        Set<Student> students = getAll();
        if(students.add(g)) //testing if the book already exists
        {
            String filePath = FilesPath + FileNameStudent;
            File file = new File(filePath);
            try(
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bwr = new BufferedWriter(fw)){ // to optimize
                    bwr.write(g.toDatabase());
                    bwr.newLine(); // avoiding \n dueto other kind of operacional system
                    bwr.flush();
                
            }catch(IOException ex){
                Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
        }else{
            return false;
        }
        
        return true;
    }

    public ArrayList<Student> getAllByTitleOrAuthors(String firstName, String studentID)
    {         
        // 6) Search for a specific student by name and/or ID
        Set<Student> students = getAll();
        ArrayList<Student> studentsRet = new ArrayList<>();
        for (Student student : students) {            
            if((!firstName.isEmpty()) && (!studentID.isEmpty()))
            {
                if((student.getStudentId().toLowerCase().startsWith(studentID.toLowerCase())) && (student.getFirstName().toLowerCase().startsWith(firstName.toLowerCase())))
                {
                    studentsRet.add(student);
                }
            }else{
                
                if((!firstName.isEmpty()) && (studentID.isEmpty()))
                {
                    if(student.getFirstName().toLowerCase().startsWith(firstName.toLowerCase()))
                    {
                        studentsRet.add(student);
                    }
                }else{
                    if((firstName.isEmpty()) && (!studentID.isEmpty()))
                    {
                        if(student.getStudentId().toLowerCase().startsWith(studentID.toLowerCase()))
                        {
                            studentsRet.add(student);
                        }
                    }
                }                            
            }
        }         
         return studentsRet;        
     }
    
    public ArrayList<Student> getAllOrderedByNames() 
    {  
         
         Set<Student> students = getAll();
         Comparator<Student> compareByNames = Comparator
                 .comparing(Student::getFirstName)
                 .thenComparing(Student::getLastName);
         
         ArrayList<Student> sortedStudents = (ArrayList<Student>) students.stream()
                 .sorted(compareByNames)
                 .collect(Collectors.toList());
                 //.collect(collector)
           return sortedStudents;
    }
    
    @Override
    public void update(Student g, String[] infos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Student g) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
}
