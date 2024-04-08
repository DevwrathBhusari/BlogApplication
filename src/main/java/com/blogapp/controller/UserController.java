package com.blogapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.blogapp.entity.User;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.UserList;
import com.blogapp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
      @Autowired     
      private UserService userService;
	
	//create
      @PostMapping("/addUser")
	public ResponseEntity<ApiResponse> createUser(@RequestBody UserList userList){
		String addUser = this.userService.addUser(userList);
		if (addUser.equalsIgnoreCase("user added successfully!!!")) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("user created successfully!!!",true),HttpStatus.OK); 
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("user not created ",false),HttpStatus.BAD_REQUEST);
	}
	//update
      @PutMapping("/updateUser/{id}")
      public ResponseEntity<ApiResponse> updateUser(@RequestBody UserList userList, @PathVariable Integer id){
    	  String updateUser = this.userService.updateUser(userList, id);
    	  if (updateUser.equalsIgnoreCase("User updated successfully!!!")) {
  			return new ResponseEntity<ApiResponse>(new ApiResponse("user updated successfully!!!",true),HttpStatus.OK); 
  		}
  		return new ResponseEntity<ApiResponse>(new ApiResponse("user not updated ",false),HttpStatus.BAD_REQUEST);
  	}  
    	  
      
	//get all users
      @GetMapping("/")
      public ResponseEntity<List<UserList>> getAllUsers(){
    	  return ResponseEntity.ok(this.userService.getAllUsers());
      }
      
	//get user by id
      @GetMapping("/{id}")
      public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		User user = this.userService.getUser(id);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	//delete user
      @DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUSer(@PathVariable Integer id) {
		String deleteUser = this.userService.deleteUser(id);
		if (deleteUser.equalsIgnoreCase("User Deleted Successfully!!!")) {
  			return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully!!!",true),HttpStatus.OK); 
  		}
  		return new ResponseEntity<ApiResponse>(new ApiResponse("user not deleted ",false),HttpStatus.BAD_REQUEST);
  	}  
	
	
	
	
	
}
