package com.arpit.expensetracker.service;

import com.arpit.expensetracker.model.User;
import com.arpit.expensetracker.repository.UserRepository;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    @SneakyThrows
    public User updateUser(Long userId, User userDetails) {
        User user = getUserById(userId);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public JSONObject login(String name, String password) {

        JSONObject response = new JSONObject();
        List<User> user = userRepository.findByName(name);
        if(user.isEmpty()){
            response.put("error Message", "Username doesn't exists");
            return response;
        }
        else{
            User userObj = user.get(0);
            if(password.equals(userObj.getPassword())){
                response = createResponse(userObj);
            }
            else{
                response.put("error message", "password is not valid");
            }
        }
        return response;
    }

    private JSONObject createResponse(User user) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("userId", user.getId());
        jsonObject.put("name", user.getName());
        jsonObject.put("email", user.getEmail());

        return jsonObject;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
