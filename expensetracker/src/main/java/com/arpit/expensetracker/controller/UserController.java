package com.arpit.expensetracker.controller;

import com.arpit.expensetracker.model.User;
import com.arpit.expensetracker.service.ResourceNotFoundException;
import com.arpit.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody String requestData){

        JSONObject requestJson = new JSONObject(requestData);

        JSONObject isValidLogin = validateLogin(requestJson);

        if(isValidLogin.isEmpty()){
            String name = requestJson.getString("name");
            String password = requestJson.getString("password");
            JSONObject responseObj = userService.login(name, password);
            if(responseObj.has("errorMessage")){
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.BAD_REQUEST);
            }
            else{
                return new ResponseEntity<String>(responseObj.toString(), HttpStatus.OK);
            }
        }
        else{
            return new ResponseEntity<>(isValidLogin.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    private JSONObject validateLogin(JSONObject requestJson) {
        JSONObject errorList = new JSONObject();

        if(!requestJson.has("name")){
            errorList.put("name", "missing name");
        }
        if(!requestJson.has("password")){
            errorList.put("password", "missing password");
        }
        return errorList;
    }

    @GetMapping("")
    public ResponseEntity<String> getAllUsers() throws ResourceNotFoundException {
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userList.toString(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long userId) throws ResourceNotFoundException {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(userId, userDetails);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
