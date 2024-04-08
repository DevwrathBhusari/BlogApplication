package com.blogapp.serviceImplementation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapp.entity.Comment;
import com.blogapp.entity.Post;
import com.blogapp.entity.User;
import com.blogapp.modal.ApiResponse;
import com.blogapp.modal.CommentDto;
import com.blogapp.modal.CommentListDto;
import com.blogapp.modal.UserList;
import com.blogapp.repository.CommentRepository;
import com.blogapp.repository.PostRepository;
import com.blogapp.repository.UserRepository;
import com.blogapp.service.CommentService;
@Service
public class CommentServiceImplementation implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
   
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ApiResponse addComment(CommentDto commentDto, Integer userId) {
	Post post=	this.postRepository.getById(commentDto.getPostId());
    User user=	this.userRepository.getById(userId);
	Comment comment=new Comment();
	comment.setComment(commentDto.getComment());
	comment.setPost(post);
	comment.setUser(user);
	this.commentRepository.save(comment);
	return new ApiResponse("comment added successfully!!!",true);
	}

	
	@Override
	public ApiResponse updateComment(CommentDto commentDto, Integer userId) {
	      Comment comment = this.commentRepository.findById(commentDto.getCommentId()).get();
	      User user = this.userRepository.findById(userId).get();
	      if (comment.getUser().getName().equalsIgnoreCase(user.getName())) {
			comment.setComment(commentDto.getComment());
			this.commentRepository.save(comment);
			return new ApiResponse("Comment updated sucessfully!!!", true);
		}
		return new ApiResponse("The comment is not yours hence cannot be edited", false);
	}

	@Override
	public List<CommentListDto> getAllComment(Integer postId) {
		  Post post = this.postRepository.findById(postId).get();
		  List<Comment> comments=post.getComments();
		  List<CommentListDto> commentListDtos=new ArrayList<>();
		  for (Comment comment : comments) {
			CommentListDto commentListDto=new CommentListDto();
			commentListDto.setComment(comment.getComment());
			commentListDto.setPostId(comment.getPost().getPostId());
			UserList userList = new UserList();
			userList.setId(comment.getUser().getId());
			userList.setName(comment.getUser().getName());
			commentListDto.setUserList(userList);
		    commentListDtos.add(commentListDto);
	}
	return commentListDtos;	  
	}

	@Override
	public List<CommentListDto> getYourComment(Integer postId, Integer UserId) {
		Post post = this.postRepository.findById(postId).get();
		User user = this.userRepository.findById(UserId).get();
		List<Comment> comments = post.getComments();
		List<CommentListDto> commentListDtos=new ArrayList<>();
		for (int i = 0; i < comments.size(); i++) {
         if (comments.get(i).getUser().getId()==user.getId()) {
			CommentListDto commentListDto= new CommentListDto();
			commentListDto.setComment(comments.get(i).getComment());
			commentListDto.setPostId(comments.get(i).getPost().getPostId());
			UserList userList = new UserList();
			userList.setId(comments.get(i).getUser().getId());
			userList.setName(comments.get(i).getUser().getName());
			commentListDto.setUserList(userList);
			commentListDtos.add(commentListDto);
		}
			
		}
		return commentListDtos;
	}

	@Override
	public ApiResponse deleteComment( Integer userId,  Integer commentId) {
		User user = this.userRepository.findById(userId).get();
		Comment comment = this.commentRepository.findById(commentId).get();
		if (user.getId()==comment.getUser().getId()) {
			this.commentRepository.delete(comment);
			return new ApiResponse("Comment deleted successfully", true);
		}
		return new ApiResponse("Its not the comment posted by you hence cant be deleted!!", false);
	}

}
