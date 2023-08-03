/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cctstudent.cctlibmgmtsys.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author 2022057 Marliclere Santos
 */
public class BookReturned {
    private UUID id;
    private UUID BorrowingId;
    private UUID bookId;
    private UUID studentId;
    private UUID employeeId;
    private LocalDate ReturnedDate;
    private LocalTime StartTime;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBorrowingId() {
        return BorrowingId;
    }

    public void setBorrowingId(UUID BorrowingId) {
        this.BorrowingId = BorrowingId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getReturnedDate() {
        return ReturnedDate;
    }

    public void setReturnedDate(LocalDate ReturnedDate) {
        this.ReturnedDate = ReturnedDate;
    }

    public LocalTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalTime StartTime) {
        this.StartTime = StartTime;
    }

    public BookReturned(UUID BorrowingId, UUID bookId, UUID studentId, UUID employeeId, LocalDate ReturnedDate, LocalTime StartTime) {
        this.BorrowingId = BorrowingId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.ReturnedDate = ReturnedDate;
        this.StartTime = StartTime;
    }

    public BookReturned(UUID id, UUID BorrowingId, UUID bookId, UUID studentId, UUID employeeId, LocalDate ReturnedDate, LocalTime StartTime) {
        this.id = id;
        this.BorrowingId = BorrowingId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.ReturnedDate = ReturnedDate;
        this.StartTime = StartTime;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.BorrowingId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookReturned other = (BookReturned) obj;
        return Objects.equals(this.BorrowingId, other.BorrowingId);
    }

    @Override
    public String toString() {
        return "BookReturned{" + "id=" + id + ", BorrowingId=" + BorrowingId + ", bookId=" + bookId + ", studentId=" + studentId + ", employeeId=" + employeeId + ", ReturnedDate=" + ReturnedDate + ", StartTime=" + StartTime + '}';
    }
    
     public String toDatabase() {
        return id.toString() + "," + BorrowingId.toString() + "," + bookId.toString() + "," + studentId.toString() + "," + employeeId.toString() + "," + ReturnedDate.toString() + "," + StartTime.toString();
    }
    
    
}
