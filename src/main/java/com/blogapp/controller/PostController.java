package com.blogapp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.entity.Post;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.PostDto;
import com.blogapp.modal.PostRequest;
import com.blogapp.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	//create post
	@PostMapping("/add/{userId}")
	public ResponseEntity<ApiResponse> createPost(@RequestBody PostRequest postModal,@PathVariable Integer userId) {
		ApiResponse apiResponse = this.postService.createPost(postModal,userId);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	//Update post
	@PutMapping("{postId}/update")
	public ResponseEntity<ApiResponse> updatePost(@RequestBody PostRequest postModal,@PathVariable Integer postId) {
		ApiResponse apiResponse = this.postService.updatePost(postModal, postId);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}
	
	//upload Image
	@PostMapping("/{postId}/upload")
	public ResponseEntity<ApiResponse> uploadImage(@RequestParam( required = false, value ="image") MultipartFile file
			,@PathVariable
			Integer postId) throws IOException {
		ApiResponse apiResponse = this.postService.upload(file,postId);
		return new ResponseEntity<>(apiResponse,HttpStatus.OK);
		}
	
	//get all posts
	@GetMapping("/viewall")
	public ResponseEntity<List<PostDto>> getAll() {
		List<PostDto> posts= this.postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	//get post by postId
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
		}

	//get post by user id
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId) {
		List<PostDto>  posts = this.postService.getPostByUserId(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
    
	//delete post
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		ApiResponse apiResponse = this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		}
	
}
