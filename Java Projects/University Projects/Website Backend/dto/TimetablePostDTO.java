package com.example.demo.dto;

public class TimetablePostDTO {

	String day;
	Long hours;
	
	//Constructor
		public TimetablePostDTO(String day, Long hours) {
			super();
			this.day = day;
			this.hours = hours;
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
