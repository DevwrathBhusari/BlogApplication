package com.blogapp.serviceImplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entity.User;
import com.blogapp.modal.UserList;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.UserService;
@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserList> getAllUsers() {
		List<User> users= userRepository.findAll();
		List<UserList> userLists=new ArrayList<>();
		for(User user:users)
		{
			UserList list=new UserList();
			list.setId(user.getId());
			list.setName(user.getName());
			userLists.add(list);
		}
		return userLists;
	}

	@Override
	public String addUser(UserList userList) {
		List<User> users= userRepository.findAll();
		for(int i=0;i<=users.size()-1;i++) {
			if (users.get(i).getName().equalsIgnoreCase(userList.getName())) {
				return "this name already exists!! Try another name...";
			}	
		}
		User user = new User();
		user.setName(userList.getName());
		userRepository.save(user);
		return "user added successfully!!!";
	}

	@Override
	public User getUser(int id) {
      User user = this.userRepository.findById(id).get();
		return user;
	}

	@Override
	public String updateUser(UserList userList ,int id) {
		User user = userRepository.findById(id).get();
		user.setName(userList.getName());
		userRepository.save(user);
		return "User updated successfully!!!";
	}

	@Override
	public String deleteUser(int id) {
		User user = this.userRepository.findById(id).get();
		userRepository.delete(user);
		return "User Deleted Successfully!!!";
	}

}
