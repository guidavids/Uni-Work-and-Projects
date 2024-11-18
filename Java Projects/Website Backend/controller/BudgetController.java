package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BudgetPostDTO;
import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.service.BudgetService;

@RestController
public class BudgetController {
	
	@Autowired
	BudgetService BudgetService;
	
	@Autowired
	BudgetRepository BudgetRepo;
	
	//Get all Budgets
	@GetMapping("/budget")
	public List<Budget> getBudgets() {
		
		return BudgetService.getBudgets();
		
	}
	
	@PostMapping("/budget")
	public ResponseEntity<Optional<Budget>> addBudget (@RequestBody BudgetPostDTO newBudgetDTO) {
		
		if(newBudgetDTO.getBudgetTitle() == null ||
				newBudgetDTO.getCurrentBudgetAmount() == null ||
				newBudgetDTO.getTotalBudgetAmount() == null) {
			return new ResponseEntity<>(Optional.ofNullable(null), HttpStatus.BAD_REQUEST);
		}
		
		Budget newBudget = new Budget(newBudgetDTO.getBudgetTitle(), 
				newBudgetDTO.getCurrentBudgetAmount(), 
				newBudgetDTO.getTotalBudgetAmount(),
				newBudgetDTO.getUser());
		
		BudgetService.addBudget(newBudget);
		return new ResponseEntity<>(Optional.ofNullable(newBudget), HttpStatus.CREATED);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("budget/delete/{id}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long id) {
        try {
        	BudgetRepo.deleteById(id);
            return ResponseEntity.ok("Budget deleted with success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
