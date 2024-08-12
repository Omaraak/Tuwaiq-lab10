package com.example.lab10.Service;

import com.example.lab10.Model.User;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(int id, User user) {
        User oldUser = userRepository.getById(id);
        if (oldUser != null) {
            oldUser.setName(user.getName());
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            oldUser.setRole(user.getRole());
            oldUser.setAge(user.getAge());
            userRepository.save(oldUser);
            return true;
        }
        return false;
    }

    public boolean deleteUser(int id) {
        User oldUser = userRepository.getById(id);
        if (oldUser != null) {
            userRepository.delete(oldUser);
            return true;
        }
        return false;
    }
}
