package com.geedzeco.repository;

import com.geedzeco.model.Category;
import com.geedzeco.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense, Long> {
    
    List<Expense> findByDate(LocalDate date);
    
    List<Expense> findByCategory(Category category);
    
    List<Expense> findByAmountGreaterThanOrEqual(BigDecimal amount);
    
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Expense> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
    
    @Query("SELECT e FROM Expense e WHERE e.date = :date AND e.category = :category")
    List<Expense> findByDateAndCategory(@Param("date") LocalDate date, @Param("category") String category);
}
