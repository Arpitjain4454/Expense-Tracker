package com.arpit.expensetracker.controller;

import com.arpit.expensetracker.model.Product;
import com.arpit.expensetracker.service.ProductService;
import com.arpit.expensetracker.service.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Product> createProduct(@PathVariable("userId") Long userId, @Valid @RequestBody Product product) {
        Product createdProduct = productService.createProduct(userId, product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) throws ResourceNotFoundException {
        Product product = productService.getProductById(productId);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Product>> getAllProductsByUserId(@PathVariable("userId") Long userId) {
        List<Product> products = productService.getAllProductsByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Product>> getAllProductsByDate(@PathVariable("userId") Long userId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam(value = "time", required = false) @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time) {
        List<Product> products = productService.getAllProductsByDate(userId, date, time);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/total-expenditure")
    public ResponseEntity<BigDecimal> getTotalExpenditureByMonth(@PathVariable("userId") Long userId, @RequestParam("month") int month) {
        BigDecimal totalExpenditure = productService.getTotalExpenditureByMonth(userId, month);
        return new ResponseEntity<>(totalExpenditure, HttpStatus.OK);
    }
}

