package com.geedzeco.controller;

import com.geedzeco.model.Expenses;
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
    
    // Wyszukiwanie po dacie
    @GetMapping("/search-by-date")
    public ResponseEntity<List<Expenses>> searchByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(expensesService.findByDate(date));
    }
    
    // Wyszukiwanie po kategorii
    @GetMapping("/search-by-category")
    public ResponseEntity<List<Expenses>> searchByCategory(@RequestParam String category) {
        return ResponseEntity.ok(expensesService.findByCategory(category));
    }
    
    // Wyszukiwanie po sumie (równej lub większej niż)
    @GetMapping("/search-by-amount")
    public ResponseEntity<List<Expenses>> searchByAmount(@RequestParam BigDecimal amount) {
        return ResponseEntity.ok(expensesService.findByAmountGreaterThanOrEqual(amount));
    }
    
    // Wyszukiwanie po zakresie dat
    @GetMapping("/search-by-date-range")
    public ResponseEntity<List<Expenses>> searchByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(expensesService.findByDateRange(startDate, endDate));
    }
    
    // Wyszukiwanie po zakresie sumy
    @GetMapping("/search-by-amount-range")
    public ResponseEntity<List<Expenses>> searchByAmountRange(
            @RequestParam BigDecimal minAmount,
            @RequestParam BigDecimal maxAmount) {
        return ResponseEntity.ok(expensesService.findByAmountRange(minAmount, maxAmount));
    }
    
    // Wyszukiwanie po dacie i kategorii
    @GetMapping("/search-by-date-and-category")
    public ResponseEntity<List<Expenses>> searchByDateAndCategory(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam String category) {
        return ResponseEntity.ok(expensesService.findByDateAndCategory(date, category));
    }
}
