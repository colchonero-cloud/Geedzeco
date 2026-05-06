package com.geedzeco.controller;

import com.geedzeco.model.Category;
import com.geedzeco.model.Expense;
import com.geedzeco.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpensesController {
    
    @Autowired
    private ExpensesService expensesService;
    
    @GetMapping("/search-by-date")
    public ResponseEntity<List<Expense>> searchByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(expensesService.findByDate(date));
    }
    
    @GetMapping("/search-by-category")
    public ResponseEntity<List<Expense>> searchByCategory(@RequestParam Category category) {
        return ResponseEntity.ok(expensesService.findByCategory(category));
    }
    
    @GetMapping("/search-by-amount")
    public ResponseEntity<List<Expense>> searchByAmount(@RequestParam BigDecimal amount) {
        return ResponseEntity.ok(expensesService.findByAmountGreaterThanOrEqual(amount));
    }
    
    @GetMapping("/search-by-date-range")
    public ResponseEntity<List<Expense>> searchByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(expensesService.findByDateRange(startDate, endDate));
    }
    
    @GetMapping("/search-by-amount-range")
    public ResponseEntity<List<Expense>> searchByAmountRange(
            @RequestParam BigDecimal minAmount,
            @RequestParam BigDecimal maxAmount) {
        return ResponseEntity.ok(expensesService.findByAmountRange(minAmount, maxAmount));
    }
    
    @GetMapping("/search-by-date-and-category")
    public ResponseEntity<List<Expense>> searchByDateAndCategory(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String category) {
        return ResponseEntity.ok(expensesService.findByDateAndCategory(date, category));
    }
}
