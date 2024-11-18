package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

//Create a Budget class

@Entity
@Table(name = "Budget")
@EntityListeners(AuditingEntityListener.class)
public class Budget implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank
	String budgetTitle;
	
	@NotNull
	@Min(value = 0)
	Long currentBudgetAmount;

	@NotNull
	@Min(value = 0)
	Long totalBudgetAmount;
	
	@ManyToOne
	@JoinColumn(name = "UserId")
	User user;
	
	public Budget() {
		
		super();
		
	}
	
	public Budget(String budgetTitle, Long currentBudgetAmount, Long totalBudgetAmount, User user) {
		
		super();
		this.budgetTitle = budgetTitle;
		this.currentBudgetAmount = currentBudgetAmount;
		this.totalBudgetAmount = totalBudgetAmount;
		this.user = user;
	}
	
	//Created getters and setters
	public Long getId() {
		
		return id;
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public void setTotalBudgetAmount(Long totalBudgetAmount) {
		
		this.totalBudgetAmount = totalBudgetAmount;
		
	}
	
	public User getUser() {
		
		return user;
		
	}
	
	public void setUser(User user) {
		
		this.user = user;
		
	}
}
