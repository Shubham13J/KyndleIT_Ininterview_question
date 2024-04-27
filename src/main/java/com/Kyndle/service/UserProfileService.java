package com.Kyndle.service;

import com.Kyndle.entity.UserProfile;

public interface UserProfileService {
	UserProfile addUserProfile(UserProfile userProfile);
    boolean validateUserProfile(UserProfile userProfile);
}
