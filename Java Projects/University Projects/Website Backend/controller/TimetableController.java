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
import com.example.demo.dto.TimetablePostDTO;
import com.example.demo.model.Budget;
import com.example.demo.model.Timetable;
import com.example.demo.repository.TimetableRepository;
import com.example.demo.service.TimetableService;

@RestController
public class TimetableController {

	@Autowired
	TimetableService TimeService;

	@Autowired
	TimetableRepository TimeRepo;


	//Get all timetables
	@GetMapping("/timetable")
	public List<Timetable> getTables(){

		return TimeService.getTimetables();

	}

	@PostMapping("/timetable")
	public ResponseEntity<Optional<Timetable>> addTable (@RequestBody TimetablePostDTO newTableDTO) {

		if(newTableDTO.getDay() == null ||
				newTableDTO.getHours() == null) {
			return new ResponseEntity<>(Optional.ofNullable(null), HttpStatus.BAD_REQUEST);
		}

		Timetable newTable = new Timetable(newTableDTO.getDay(), 
				newTableDTO.getHours());

		TimeService.addTimetable(newTable);
		return new ResponseEntity<>(Optional.ofNullable(newTable), HttpStatus.CREATED);
	}

	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("timetable/delete/{id}")
	public ResponseEntity<?> deleteTimetableable(@PathVariable Long id) {
		try {
			TimeRepo.deleteById(id);
			return ResponseEntity.ok("Table deleted with success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
