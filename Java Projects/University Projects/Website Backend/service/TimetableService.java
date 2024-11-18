package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Timetable;
import com.example.demo.repository.TimetableRepository;

@Service
public class TimetableService {

	@Autowired
	TimetableRepository TimeRepo;
	
	
	//Get all timetables
	public List<Timetable> getTimetables() {
		return (List<Timetable>) TimeRepo.findAll();
	}
	
	public void addTimetable(Timetable newTime) {
		TimeRepo.save(newTime);
	}
	
	@Transactional
	public void deleteUser(Long id) {
		Timetable user = TimeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Timetable", "id", id));
		
		TimeRepo.delete(user);
	} 
}
