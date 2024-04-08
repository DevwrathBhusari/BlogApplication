package com.blogapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.entity.Post;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.PostDto;
import com.blogapp.modal.PostRequest;
@Service
public interface PostService {
	
	//create post
	public ApiResponse createPost(PostRequest postModal, Integer UserId);
	//update post
	public ApiResponse updatePost(PostRequest postModal,Integer postId);
	//get all posts
	public List<PostDto> getAllPosts();
	//get Post by postid
	public PostDto getPostById(Integer id);
	//get Post by UserId
	public List<PostDto> getPostByUserId(Integer id);
	//delete post
	public ApiResponse deletePost(Integer id);
    //upload Image
	public ApiResponse upload(MultipartFile file, Integer postId) throws IOException;
}
