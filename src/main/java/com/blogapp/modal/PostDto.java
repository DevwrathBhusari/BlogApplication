package com.blogapp.modal;

import java.util.List;

import com.blogapp.entity.Comment;
import com.blogapp.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer postId;
	  private UserList userList;
	 private List<Comment> comments;
	 private String imageUrl;
	 private String blog;
	
	
	
	

}
