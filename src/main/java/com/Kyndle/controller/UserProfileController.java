package com.Kyndle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Kyndle.entity.UserProfile;
import com.Kyndle.exception.InvalidUserProfileException;
import com.Kyndle.service.UserProfileService;

@RestController
public class UserProfileController {
    @Autowired
    private UserProfileService service;

    @PostMapping("/addUserProfile")
    public ResponseEntity<String> addUserProfile(@RequestBody UserProfile userProfile) {
        try {
            if (!service.validateUserProfile(userProfile)) {
                return ResponseEntity.badRequest().body("Invalid user profile");
            }
            UserProfile savedProfile = service.addUserProfile(userProfile);
            return ResponseEntity.status(HttpStatus.CREATED).body("User profile added successfully with ID: " + savedProfile.getId());
        } catch (InvalidUserProfileException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
