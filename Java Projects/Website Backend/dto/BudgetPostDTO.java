package com.example.demo.dto;

import com.example.demo.model.User;

public class BudgetPostDTO {
	
	String budgetTitle;
	Long currentBudgetAmount;
	Long totalBudgetAmount;
	User user;
	
	public BudgetPostDTO(String budgetTitle, Long currentBudgetAmount, Long totalBudgetAmount, User user) { //Constructor
		
		super();
		this.budgetTitle = budgetTitle;
		this.currentBudgetAmount = currentBudgetAmount;
		this.totalBudgetAmount = totalBudgetAmount;
		this.user = user;
	}
	
	//Created getters and setters
	
	public String getBudgetTitle() {
		
		return budgetTitle;
		
	}
	
	public void setBidgetTitle(String budgetTitle) {
		
		this.budgetTitle = budgetTitle;
		
	}
	
	public Long getCurrentBudgetAmount() {
		
		return currentBudgetAmount;
		
	}
	
	public void setCurrentBudgetAmount(Long currentBudgetAmount) {
		
		this.currentBudgetAmount = currentBudgetAmount;
		
	}
	
	public Long getTotalBudgetAmount() {
		
		return totalBudgetAmount;
		
	}
	
	public void setBudgetAmount(Long totalBudgetAmount) {
		
		this.totalBudgetAmount = totalBudgetAmount;
		
	}
	
	public User getUser() {
		
		return user;
		
	}
	
	public void setUser(User user) {
		
		this.user = user;
		
	}
}
