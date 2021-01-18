package com.stackroute.search.controller;

import com.stackroute.search.model.UserProfile;
import com.stackroute.search.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserProfileController {

    private UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> saveUser(@RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userProfileService.saveUser(userProfile), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userProfileService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/users/{role}")
    public ResponseEntity<?> getAllUsersByRole(@PathVariable("role") String role) {
        return new ResponseEntity<>(userProfileService.getAllUsersByRole(role), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchResponse(@RequestParam("value") String keyword) {
        return new ResponseEntity<>(userProfileService.search(keyword), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam("email") String email) {
        return new ResponseEntity<>(userProfileService.getUserByEmail(email), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody UserProfile userProfile) {
        return new ResponseEntity<>(userProfileService.updateUser(userProfile), HttpStatus.OK);
    }

}
