package io.pritamproduction.blog.service.impl;

import io.pritamproduction.blog.entites.Post;
import io.pritamproduction.blog.entites.comment;
import io.pritamproduction.blog.exception.ResourceNotFound;
import io.pritamproduction.blog.payloads.commentDto;
import io.pritamproduction.blog.repositoris.PostRepository;
import io.pritamproduction.blog.repositoris.commentRepo;
import io.pritamproduction.blog.service.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class commentServiceImpl implements commentService {
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private commentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public commentDto createComment(commentDto commentDto, Integer postId) {
		Post post=postRepo.findById(postId).orElseThrow(()->new ResourceNotFound("POst","Post id",postId));

		comment comment=modelMapper.map(commentDto,comment.class);
		comment.setPost(post);
		comment savedComment=commentRepo.save(comment);

		return modelMapper.map(savedComment,commentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		comment comment=commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFound("Comment","Comment Id",commentId));
		commentRepo.delete(comment);
	}
}
