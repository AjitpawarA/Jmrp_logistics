package com.alpha.jmrplogistics.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alpha.jmrplogistics.entity.User;
import com.alpha.jmrplogistics.repo.UserRepository;

import jakarta.transaction.Transactional;

@Repository
public class UserDao {

    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User addUser(User user) {
        return userRepository.saveAndFlush(user);
    }
    
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
