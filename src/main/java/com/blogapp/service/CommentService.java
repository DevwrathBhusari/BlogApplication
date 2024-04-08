package com.blogapp.service;

import java.util.List;

import com.blogapp.entity.Comment;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.CommentDto;
import com.blogapp.modal.CommentListDto;

public interface CommentService {
	
	//add comment
	public ApiResponse addComment(CommentDto commentDto,Integer userId);
	//update comment
	public ApiResponse updateComment(CommentDto commentDto,Integer userId);
	//get all comment from the post
	public List<CommentListDto> getAllComment(Integer postId);
	//get your comment
	public List<CommentListDto> getYourComment(Integer postId,Integer UserId);
	//delete comment
	public ApiResponse deleteComment( Integer userId, Integer commentId);

}
