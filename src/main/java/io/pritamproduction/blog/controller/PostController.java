package io.pritamproduction.blog.controller;

import io.pritamproduction.blog.config.AppConstraints;
import io.pritamproduction.blog.payloads.ApiResponse;
import io.pritamproduction.blog.payloads.PostDto;
import io.pritamproduction.blog.payloads.PostResponse;
import io.pritamproduction.blog.service.impl.FileServiceImpl;
import io.pritamproduction.blog.service.postService;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private postService service;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private FileServiceImpl fileService;
	@Value("${project.image}")
	private String path;


	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
											  @PathVariable("userId") Integer userId,
											  @PathVariable("categoryId") Integer categoryId){
		PostDto createPost=service.createPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}


	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable("userId") Integer userId){
		List<PostDto> result=service.getAllPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}

	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getAllPOstByCategory(@PathVariable("catId") Integer catId){
		List<PostDto> result=service.getPostByCategory(catId);
		return ResponseEntity.ok(result);
	}


 	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(){
		List<PostDto> result=service.getAllPost();
		return ResponseEntity.ok(result);
	}

	@GetMapping("/post/{id}/posts")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id){
		PostDto post=service.getPostById(id);
		return ResponseEntity.ok(post);
	}

	@PutMapping("/post/{id}/posts")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable("id") Integer id){
		postDto.setAddedDate(new Date());
		PostDto result=service.updatePost(postDto,id);
		return new ResponseEntity<PostDto>(result,HttpStatus.OK);
	}

	@DeleteMapping("/post/{id}")
	public ApiResponse deletePostById(@PathVariable Integer id){
		service.deletePost(id);
		return new ApiResponse("Post is Successfully Deleted ",true);
	}


	@GetMapping("/postsPagination")
	public ResponseEntity<PostResponse> getAllPostPagination(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer pageSize){
		PostResponse result=service.getPostsPageble(pageNumber,pageSize);
		return ResponseEntity.ok(result);
	}
@GetMapping("/postsPaginationSortBy")
	public ResponseEntity<PostResponse> getAllPostPagination(
			@RequestParam(value = "pageNumber",defaultValue = AppConstraints.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstraints.PAGE_SIZE,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstraints.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstraints.SORT_DIR,required = false) String sortDir){
		PostResponse result=service.getPostsPagebleBySort(pageNumber,pageSize,sortBy,sortDir);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		List<PostDto> posts=service.searchPosts("%"+keyword+"%");
		return ResponseEntity.ok(posts);
	}




	//Post Image Upload
	@PostMapping("/post/Image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image")MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {
		PostDto postDto=service.getPostById(postId);
		String fileName=fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostDto updatePost=service.updatePost(postDto,postId);
		return new ResponseEntity<>(updatePost,HttpStatus.CREATED);
	}


	@GetMapping(value = "/post/image/{imageName}",produces =MediaType.ALL_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {

		response.setContentType(MediaType.ALL_VALUE);
		InputStream is= fileService.getResource(path, imageName);

		StreamUtils.copy(is,response.getOutputStream());

	}




}
