package com.blogapp.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentListDto {
	
	private String comment;
	private UserList userList;
	private Integer postId;

}
