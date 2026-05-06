package com.geedzeco.service;

import com.geedzeco.model.Category;
import com.geedzeco.model.Expense;
import com.geedzeco.repository.ExpensesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpensesService {
    
    private final ExpensesRepository expensesRepository;
    
    public List<Expense> findByDate(LocalDate date) {
        return expensesRepository.findByDate(date);
    }
    
    public List<Expense> findByCategory(Category category) {
        return expensesRepository.findByCategory(category);
    }
    
    public List<Expense> findByAmountGreaterThanOrEqual(BigDecimal amount) {
        return expensesRepository.findByAmountGreaterThanOrEqual(amount);
    }
    
    public List<Expense> findByDateRange(LocalDate startDate, LocalDate endDate) {
        return expensesRepository.findByDateBetween(startDate, endDate);
    }
    
    public List<Expense> findByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        return expensesRepository.findByAmountBetween(minAmount, maxAmount);
    }
    
    public List<Expense> findByDateAndCategory(LocalDate date, String category) {
        return expensesRepository.findByDateAndCategory(date, category);
    }
}
