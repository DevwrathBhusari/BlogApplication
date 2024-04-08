package com.blogapp.service;

import java.util.List;

import com.blogapp.entity.User;
import com.blogapp.modal.UserList;

public interface UserService {
	
	//view all users
	public List<UserList> getAllUsers();
	
	//add user
	
	public String addUser(UserList userList);
	
	//get single User
	
	public User getUser(int id);
	
	//update User
	public String updateUser(UserList userList ,int id);
	
	//delete user
	public String deleteUser(int id);

}
