package io.pritamproduction.blog.payloads;

import io.pritamproduction.blog.entites.comment;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	private Integer id;
	@NotEmpty(message="Should not be blank")
	private String title;
	@NotEmpty(message = "Should not be Empty")
	private String content;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	private Set<commentDto> comments=new HashSet<>();

}
