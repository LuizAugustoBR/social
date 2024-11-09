package com.social.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.social.models.Social;

public interface SocialRepository extends JpaRepository<Social, Long> {

}
