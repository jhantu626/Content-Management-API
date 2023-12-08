package io.pritamproduction.blog.service;

import io.pritamproduction.blog.payloads.PostDto;
import io.pritamproduction.blog.payloads.PostResponse;
import java.util.List;

public interface postService {
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer id);
	void deletePost(Integer postId);

	List<PostDto> getAllPost();
	PostDto getPostById(Integer id);
	List<PostDto> getPostByCategory(Integer catId);
	List<PostDto> getAllPostByUser(Integer userId);

	List<PostDto> searchPosts(String keyword);

	PostResponse getPostsPageble(Integer pageNumber, Integer pageSize);

	PostResponse getPostsPagebleBySort(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);






}