package com.geedzeco.service;

import com.geedzeco.model.Expenses;
import com.geedzeco.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpensesService {
    
    @Autowired
    private ExpensesRepository expensesRepository;
    
    public List<Expenses> findByDate(LocalDate date) {
        return expensesRepository.findByDate(date);
    }
    
    public List<Expenses> findByCategory(String category) {
        return expensesRepository.findByCategory(category);
    }
    
    public List<Expenses> findByAmountGreaterThanOrEqual(BigDecimal amount) {
        return expensesRepository.findByAmountGreaterThanOrEqual(amount);
    }
    
    public List<Expenses> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return expensesRepository.findByDateBetween(startDate, endDate);
    }
    
    public List<Expenses> findByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        return expensesRepository.findByAmountBetween(minAmount, maxAmount);
    }
    
    public List<Expenses> findByDateAndCategory(LocalDate date, String category) {
        return expensesRepository.findByDateAndCategory(date, category);
    }
}
