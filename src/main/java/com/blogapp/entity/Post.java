package com.blogapp.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer postId;
	 @ManyToOne()
	 private User user;
	 @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	 private List<Comment> comments;
	 private String imageUrl;
	 private String blog;
	
	 
	 
}
