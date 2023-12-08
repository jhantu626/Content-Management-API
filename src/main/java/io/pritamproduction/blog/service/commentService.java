package io.pritamproduction.blog.service;

import io.pritamproduction.blog.payloads.commentDto;

public interface commentService {
	commentDto createComment(commentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);

}
