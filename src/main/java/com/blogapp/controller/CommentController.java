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

import com.blogapp.entity.Comment;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.CommentDto;
import com.blogapp.modal.CommentListDto;
import com.blogapp.service.CommentService;

@RestController
@RequestMapping("/Comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	
	//add comment
	@PostMapping("/{userId}/addComment")
	public ResponseEntity<ApiResponse> addComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId) {
		ApiResponse apiResponse = this.commentService.addComment(commentDto, userId);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	//update Comment
	@PutMapping("/{userId}/editComment")
	public ResponseEntity<ApiResponse> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer userId) {
		ApiResponse apiResponse = this.commentService.updateComment(commentDto, userId);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	//get all comments in a post
	@GetMapping("/post/{postId}")
	public ResponseEntity<List<CommentListDto>> getAllComments(@PathVariable Integer postId) {
		List<CommentListDto> allComment = this.commentService.getAllComment(postId);
		return new ResponseEntity<List<CommentListDto>>(allComment,HttpStatus.OK);
		}
	
	//get your Comment
	@GetMapping("/post/{postId}/User/{userId}")
	public ResponseEntity<List<CommentListDto>> getYourComment(@PathVariable Integer postId,@PathVariable Integer userId) {
		List<CommentListDto> yourComment = this.commentService.getYourComment(postId, userId);
		return new ResponseEntity<List<CommentListDto>>(yourComment,HttpStatus.OK);
		}
	
	//delete your comment
	@DeleteMapping("/{commentId}/User/{userId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer userId,@PathVariable Integer commentId) {
		ApiResponse deleteComment = this.commentService.deleteComment(userId, commentId);
		return new ResponseEntity<ApiResponse>(deleteComment,HttpStatus.OK);
		}
	
}
