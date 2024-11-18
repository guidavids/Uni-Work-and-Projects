package com.example.demo.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserPostDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController  
public class UserController {
	
	@Autowired
	UserService userService;

	// Get All Users
    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }
    
    // Add new user
    @PostMapping("/user")
    public ResponseEntity<Optional<User>> addUser(@RequestBody UserPostDTO newUserDTO) {
    	
    	if (newUserDTO.getName()==null || 
    		newUserDTO.getEmail()==null ||
    		newUserDTO.getPassword()==null) {
            return new ResponseEntity<>(Optional.ofNullable(null), HttpStatus.BAD_REQUEST);
        }
    	
    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); //Create encoder
    	User newUser = new User(newUserDTO.getName(), newUserDTO.getEmail(),
    			encoder.encode(newUserDTO.getPassword())); //Generate a new user with encoded password
    	userService.addUser(newUser); //Add new user
    	return new ResponseEntity<>(Optional.ofNullable(newUser),HttpStatus.CREATED); //On success return this response

    }
	 
    
    // Get User by ID
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") long Id) {
    	System.out.println("Here!");
        return new ResponseEntity<User>(userService.findById(Id).get(), HttpStatus.OK);
    }
    
    
    //Delete a User by ID
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable(value = "id") long Id) {
        userService.deleteUser(Id);
        return "User Deleted"; 
    }
    
    //Get User by Email
    @GetMapping("/user/findByEmail")
    public Optional<User> getUserByEmail(@RequestParam String email) {
    	return Optional.ofNullable(userService.findByEmail(email));
    }

    @PutMapping("/user/updateEmailAndName") // create a PutMapping to allow user name and email to be updated via user settings
    public ResponseEntity<String> updateUserEmailAndName(@RequestParam String email, @RequestParam String newEmail, @RequestParam String newName) {
        
    	User user = userService.findByEmail(email); // set and object to find user by their email

        if (user != null) { //if findByEmail is not null
            user.setEmail(newEmail); //set new email
            user.setName(newName); // set new name
            userService.saveUser(user); // save new details
            return ResponseEntity.ok("User email and name updated successfully"); // display message in the browser's console if successful
        } else {
            return ResponseEntity.notFound().build(); //or else display response not found in console. Error 400
        }
    }

}
