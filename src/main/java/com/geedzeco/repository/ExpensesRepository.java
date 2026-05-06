package com.geedzeco.repository;

import com.geedzeco.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    
    // Wyszukiwanie po dacie
    List<Expenses> findByDate(LocalDate date);
    
    // Wyszukiwanie po kategorii
    List<Expenses> findByCategory(String category);
    
    // Wyszukiwanie po sumie (większej lub równej)
    List<Expenses> findByAmountGreaterThanOrEqual(BigDecimal amount);
    
    // Wyszukiwanie po zakresie dat
    List<Expenses> findByDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Wyszukiwanie po zakresie sumy
    List<Expenses> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);
    
    // Wyszukiwanie po dacie i kategorii
    @Query("SELECT e FROM Expenses e WHERE e.date = :date AND e.category = :category")
    List<Expenses> findByDateAndCategory(@Param("date") LocalDate date, @Param("category") String category);
}
