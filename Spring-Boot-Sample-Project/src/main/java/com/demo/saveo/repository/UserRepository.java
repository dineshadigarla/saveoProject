package com.demo.saveo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
	User findByTcno(String tcno);

}
