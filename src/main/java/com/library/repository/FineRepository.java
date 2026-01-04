package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.entity.Fine;
@Repository
public interface FineRepository extends JpaRepository<Fine, Long> {
	
	
}
