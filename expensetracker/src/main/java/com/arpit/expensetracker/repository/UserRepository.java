package com.arpit.expensetracker.repository;

import com.arpit.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "Select * from users where name = :name", nativeQuery = true)
    List<User> findByName(String name);

}
