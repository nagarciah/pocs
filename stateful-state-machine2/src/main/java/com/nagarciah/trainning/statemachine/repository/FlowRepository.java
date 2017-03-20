package com.nagarciah.trainning.statemachine.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarciah.trainning.statemachine.model.Flow;

public interface FlowRepository extends JpaRepository<Flow, Long>{
	public Optional<Flow> findById(Long id);
}
