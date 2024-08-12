package com.example.lab10.Controller;

import com.example.lab10.ApiResponse;
import com.example.lab10.Model.User;
import com.example.lab10.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jss/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable int id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if (userService.updateUser(id, user))
            return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("user update failed"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@RequestParam int id) {
        if (userService.deleteUser(id))
            return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
        return ResponseEntity.status(400).body(new ApiResponse("user delete failed"));
    }
}
