package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Budget;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
	
	

}
