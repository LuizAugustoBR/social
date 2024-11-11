package com.social.social.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.social.models.Social;
import com.social.social.models.Voluntarios;

public interface VoluntarioRepository extends JpaRepository<Voluntarios, Long> {
	
	List<Voluntarios> findBySocial(Social social);

}
