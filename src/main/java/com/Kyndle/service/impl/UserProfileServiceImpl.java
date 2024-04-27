package com.Kyndle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Kyndle.config.repository.UserProfileRepository;
import com.Kyndle.entity.UserProfile;
import com.Kyndle.exception.InvalidUserProfileException;
import com.Kyndle.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository repository;

    private final String EXTERNAL_API_URL = "dummyuservalidation.com/validateUser";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserProfile addUserProfile(UserProfile userProfile) {
        return repository.save(userProfile);
    }

    @Override
    public boolean validateUserProfile(UserProfile userProfile) {
        if (userProfile.getName() == null || userProfile.getEmailid() == null) {
            throw new InvalidUserProfileException("Name and Email ID are mandatory fields.");
        }

        ResponseEntity<String> response = restTemplate.postForEntity(EXTERNAL_API_URL, userProfile, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            throw new InvalidUserProfileException("External API validation failed with status: " + response.getStatusCodeValue());
        }
    }
}
