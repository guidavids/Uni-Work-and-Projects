package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	BudgetRepository BudgetRepo;
	
	public BudgetService() {
		
		super();
	}
	
	public List<Budget> getBudgets() {
		return (List<Budget>) BudgetRepo.findAll();
	}
	
	public void addBudget(Budget newBudget) {
		BudgetRepo.save(newBudget);
	}
	
	@Transactional
	public void deleteBudget(Long id) {
		Budget budget = BudgetRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Budget", "id", id));
	
		BudgetRepo.delete(budget);
	}
}
