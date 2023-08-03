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
public class Borrowing {
    private UUID id;
    private UUID bookId;
    private UUID studentId;
    private UUID employeeId;
    private LocalDate StartDate;
    private LocalTime StartTime;
    private LocalDate ReturnDate;    
    private Boolean isReturned;
    private LocalDateTime ReturnedDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDate getStartDate() {
        return StartDate;
    }

    public void setStartDate(LocalDate StartDate) {
        this.StartDate = StartDate;
    }
    
    public LocalTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalTime StartTime) {
        this.StartTime = StartTime;
    }

    public LocalDate getReturnDate() {
        return ReturnDate;
    }

    public void setReturnDate(LocalDate ReturnDate) {
        this.ReturnDate = ReturnDate;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Boolean isReturned) {
        this.isReturned = isReturned;
    }

    public LocalDateTime getReturnedDate() {
        return ReturnedDate;
    }

    public void setReturnedDate(LocalDateTime ReturnedDate) {
        this.ReturnedDate = ReturnedDate;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.bookId);
        hash = 31 * hash + Objects.hashCode(this.studentId);
        hash = 31 * hash + Objects.hashCode(this.StartDate);
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
        final Borrowing other = (Borrowing) obj;
        if (!Objects.equals(this.bookId, other.bookId)) {
            return false;
        }
        if (!Objects.equals(this.studentId, other.studentId)) {
            return false;
        }
        return Objects.equals(this.StartDate, other.StartDate);
    }

    @Override
    public String toString() {
        return "Borrowing{" + "id=" + id + ", bookId=" + bookId + ", studentId=" + studentId + ", employeeId=" + employeeId + ", StartDate=" + StartDate + ", StartTime=" + StartTime + ", ReturnDate=" + ReturnDate + ", isReturned=" + isReturned + ", ReturnedDate=" + ReturnedDate + '}';
    }
    
    
    public String toDatabase() {
        return  id.toString() + "," + bookId.toString() + "," + studentId.toString() + "," + employeeId.toString() + "," + StartDate.toString() + "," + StartTime.toString() + "," + ReturnDate.toString() + "," + isReturned.toString() + "," + ReturnedDate.toString();
    }

    public Borrowing(UUID id, UUID bookId, UUID studentId, UUID employeeId, LocalDate StartDate, LocalTime StartTime, LocalDate ReturnDate, Boolean isReturned, LocalDateTime ReturnedDate) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
        this.employeeId = employeeId;
        this.StartDate = StartDate;
        this.StartTime = StartTime;
        this.ReturnDate = ReturnDate;
        this.isReturned = isReturned;
        this.ReturnedDate = ReturnedDate;
    }
    
    
    
}
