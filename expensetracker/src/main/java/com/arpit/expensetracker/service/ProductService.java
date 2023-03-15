package com.arpit.expensetracker.service;

import com.arpit.expensetracker.model.Product;
import com.arpit.expensetracker.model.User;
import com.arpit.expensetracker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Long userId, Product product) {
        User user = new User();
        user.setId(userId);
        product.setUser(user);
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) throws ResourceNotFoundException {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    public List<Product> getAllProductsByUserId(Long userId) {
        return productRepository.findByUserId(userId);
    }

    public List<Product> getAllProductsByDate(Long userId, LocalDate date, LocalTime time) {
        if (time != null) {
            return productRepository.findByUserIdAndDateAndTime(userId, date, time);
        } else {
            return productRepository.findByUserIdAndDate(userId, date);
        }
    }

    public BigDecimal getTotalExpenditureByMonth(Long userId, int month) {
        LocalDate startDate = LocalDate.of(LocalDate.now().getYear(), month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return productRepository.getTotalExpenditureByUserIdAndDateBetween(userId, startDate, endDate);
    }
}
