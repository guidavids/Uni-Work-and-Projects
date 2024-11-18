package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Timetable;

public interface TimetableRepository extends JpaRepository<Timetable, Long>{
	

}
