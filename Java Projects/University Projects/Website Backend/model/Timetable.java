package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "timetable")
@EntityListeners(AuditingEntityListener.class)
public class Timetable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@NotBlank
	String day;
	
	@NotNull
	@Column(unique = true)
	Long hours;
	
	
	public Timetable() {
		super();
	}

	//Constructor
	public Timetable(String day, Long hours) {
		super();
		this.day = day;
		this.hours = hours;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public Long getHours(){
		return hours;
	}
	
	public void setHours(Long hours) {
		this.hours = hours;
	}
}
