package io.pritamproduction.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer id;
	@NotEmpty(message = "category title should not be null")
	private String categoryTitle;
	private String categoryDescription;
}
