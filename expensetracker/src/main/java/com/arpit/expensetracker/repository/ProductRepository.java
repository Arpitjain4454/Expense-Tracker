package com.arpit.expensetracker.repository;

import com.arpit.expensetracker.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByUserId(Long userId);

    List<Product> findByUserIdAndDate(Long userId, LocalDate date);

    List<Product> findByUserIdAndDateAndTime(Long userId, LocalDate date, LocalTime time);

    @Query(value = "SELECT COALESCE(SUM(p.price), 0) FROM Product p WHERE p.user.id = :userId AND p.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    BigDecimal getTotalExpenditureByUserIdAndDateBetween(@Param("userId") Long userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

