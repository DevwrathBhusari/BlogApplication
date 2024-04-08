package com.blogapp.serviceImplementation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.constants.CloudinaryConstants;
import com.blogapp.entity.Post;
import com.blogapp.entity.User;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.PostDto;
import com.blogapp.modal.PostRequest;
import com.blogapp.modal.UserList;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.PostService;
import com.cloudinary.Cloudinary;


@Service
public class PostServiceImplementation implements PostService {
	
	
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	

	@Override
	public ApiResponse createPost(PostRequest postModal,Integer userId) {
	Post post=new Post();
	System.out.println(postModal.getBlog());
	post.setBlog(postModal.getBlog());
	
	User user = this.userRepository.getById(userId);
	post.setUser(user);
	
	 this.postRepository.save(post);
	return new ApiResponse("Post saved succcessfully!!!", true);
	}
	
	
	@Override
	public ApiResponse updatePost(PostRequest postModal,Integer postId) {
	Post post=	this.postRepository.getById(postId);
	post.setBlog(postModal.getBlog());
	this.postRepository.save(post);
	if (post.getBlog().equalsIgnoreCase(postModal.getBlog())) {
		return new ApiResponse("post updated successfully!!!!",true);
	}
	return new ApiResponse("post not updated !!!!",false);
	}

	@Override
	public List<PostDto> getAllPosts() {
	      List<Post> posts = this.postRepository.findAll();
	      List<PostDto> postDtos = new ArrayList<>();
	      for (Post post : posts) {
			PostDto postDto= new PostDto();
			postDto.setPostId(post.getPostId());
			postDto.setBlog(post.getBlog());
		    postDto.setImageUrl(post.getImageUrl());
		    UserList userList=new UserList();
		    userList.setId(post.getUser().getId());
		    userList.setName(post.getUser().getName());
		    postDto.setUserList(userList);
		    postDtos.add(postDto);
	      }
	      return postDtos;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = this.postRepository.findById(id).get();
		PostDto postDto=new PostDto();
		postDto.setPostId(post.getPostId());
		postDto.setBlog(post.getBlog());
		postDto.setImageUrl(post.getImageUrl());
		UserList userList=new UserList();
		userList.setId(post.getUser().getId());
		userList.setName(post.getUser().getName());
		postDto.setUserList(userList);
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUserId(Integer id) {
		 User user = this.userRepository.findById(id).get();
		 List<Post> posts = user.getPosts();
		List<PostDto> postDtos=new ArrayList<>();
		for (Post post : posts) {
			PostDto postDto=new PostDto();
			postDto.setPostId(post.getPostId());
			postDto.setBlog(post.getBlog());
			postDto.setImageUrl(post.getImageUrl());
			UserList userList = new UserList();
			userList.setId(post.getUser().getId());
			userList.setName(post.getUser().getName());
			postDto.setUserList(userList);
			postDtos.add(postDto);
		}
		return postDtos;
	}

	@Override
	public ApiResponse deletePost(Integer id) {
	    Post post= postRepository.findById(id).get();
	    this.postRepository.delete(post);
	    return new ApiResponse("Post deleted sucessfully!!!!", true);
	}

	@Override
	public ApiResponse upload(MultipartFile file,Integer postId) throws IOException {
		Cloudinary cloudinary=new Cloudinary();
		Map data = cloudinary.uploader().upload(file.getBytes(),
				Map.of("cloud_name", CloudinaryConstants.cloud_name,
						"api_key", CloudinaryConstants.api_key, "api_secret", CloudinaryConstants.api_secret,
						"secure", CloudinaryConstants.secure));
		if (data==null) {
			return new ApiResponse("Image Not uploaded", false);
		}
		String urlString=(String)data.get("secure_url");
		Post post= this.postRepository.getById(postId);
		post.setImageUrl(urlString);
		postRepository.save(post);
		return new ApiResponse("Image Uploaded Successfully!!!", true);
	}
	
	


	

}
