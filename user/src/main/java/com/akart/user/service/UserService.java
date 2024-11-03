package com.akart.user.service;

import com.akart.user.exception.UserServiceResponseException;
import com.akart.user.model.User;
import com.akart.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private void validateUser(User user){
        log.info("Validating User");
        if (isBlank(user.getName())) {
            throw UserServiceResponseException.badRequest().message("name is required");
        }
        if (isBlank(user.getEmail())&& isBlank(user.getContactNo())) {
            throw UserServiceResponseException.badRequest().message("Email Id or Contact number is required");
        }

    }

    public List<User> getAllUsers() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public Optional<User> getUserById(Long id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public User createUser(User user) {
        validateUser(user);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public User updateUser(Long id, User userDetails) {
        validateUser(userDetails);
        try {
            User user = userRepository.findById(id).orElseThrow();
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setContactNo(userDetails.getContactNo());
            user.setAddress(userDetails.getAddress());
            user.setCity(userDetails.getCity());
            user.setCity(userDetails.getCity());
            user.setPinCode(userDetails.getPinCode());
            return userRepository.save(user);
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw UserServiceResponseException.internalServerError().message(e.getMessage());
        }
    }

}
