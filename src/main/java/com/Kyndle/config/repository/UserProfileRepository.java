package com.Kyndle.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Kyndle.entity.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
