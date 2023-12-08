package io.pritamproduction.blog.service.impl;

import io.pritamproduction.blog.entites.Category;
import io.pritamproduction.blog.entites.Post;
import io.pritamproduction.blog.entites.User;
import io.pritamproduction.blog.exception.ResourceNotFound;
import io.pritamproduction.blog.payloads.CategoryDto;
import io.pritamproduction.blog.payloads.PostDto;
import io.pritamproduction.blog.payloads.PostResponse;
import io.pritamproduction.blog.payloads.UserDto;
import io.pritamproduction.blog.repositoris.CategoryRepository;
import io.pritamproduction.blog.repositoris.PostRepository;
import io.pritamproduction.blog.repositoris.UserRepo;
import io.pritamproduction.blog.service.postService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements postService {

	@Autowired
	private PostRepository repository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepo userRepository;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User","id",userId));
		Category category=categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFound("Category","id",catId));
		Post post=modelMapper.map(postDto,Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);


 		Post savedPostd=repository.save(post);
		return modelMapper.map(savedPostd,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post=repository.findById(id).orElseThrow(()->new ResourceNotFound("Post","id",id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost=repository.save(post);
		return modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post =repository.findById(postId).orElseThrow(()->new ResourceNotFound("Post","Id",postId));
		repository.delete(post);

	}

	@Override
	public List<PostDto> getAllPost() {
		List<Post> posts=repository.findAll();
		List<PostDto> result=posts.stream().map((t)->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());
		return result;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post=repository.findById(id).orElseThrow(()->new ResourceNotFound("User","Id",id));
		PostDto result=modelMapper.map(post,PostDto.class);
		return result;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer catId) {
		Category category=categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFound("Category","Id",catId));
		List<Post> posts=repository.findByCategory(category);
		List<PostDto> postList=posts.stream().map((t)->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());
		return postList;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFound("User","Id",userId));
		List<Post> posts=repository.findByUser(user);
		List<PostDto> postList=posts.stream().map((t)->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());
		return postList;
	}


	public PostResponse getPostsPageble(Integer pageNumber, Integer pageSize){
		Pageable p=PageRequest.of(pageNumber,pageSize);
		Page<Post> pagePost=repository.findAll(p);
		List<Post> allPost=pagePost.getContent();

		List<PostDto> result=allPost.stream().map((t)->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		postResponse.setContent(result);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());


		return postResponse;
	}


	public PostResponse getPostsPagebleBySort(Integer pageNumber, Integer pageSize,String sortBy,String sortDir){
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber,pageSize, sort);
		//for acs and desc
//		Pageable p=PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending());
		Page<Post> pagePost=repository.findAll(p);
		List<Post> allPost=pagePost.getContent();

		List<PostDto> result=allPost.stream().map((t)->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse=new PostResponse();
		postResponse.setContent(result);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());


		return postResponse;
	}


	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post=repository.findByTitleContaining(keyword);
		List<PostDto> results=post.stream().map(t->modelMapper.map(t,PostDto.class)).collect(Collectors.toList());
		return results;
	}



}
