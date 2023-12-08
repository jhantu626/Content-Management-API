package io.pritamproduction.blog.controller;

import io.pritamproduction.blog.payloads.ApiResponse;
import io.pritamproduction.blog.payloads.commentDto;
import io.pritamproduction.blog.service.impl.commentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private commentServiceImpl commentService;

	@PostMapping("/post/{postId}/comment")
	public ResponseEntity<commentDto> createComment(@RequestBody commentDto commentDto,
													@PathVariable("postId") Integer postId){
		commentDto comment=commentService.createComment(commentDto,postId);

		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}


	@DeleteMapping("/post/comment/delete/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(new ApiResponse("Comment",true),HttpStatus.OK);
	}




}
