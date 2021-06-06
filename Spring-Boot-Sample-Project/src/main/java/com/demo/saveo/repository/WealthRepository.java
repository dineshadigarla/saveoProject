package com.demo.saveo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface WealthRepository extends JpaRepository<Wealth, Long> {

}
