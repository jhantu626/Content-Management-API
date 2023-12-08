package io.pritamproduction.blog.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private Integer id;
	@NotEmpty(message = "Name should not be empty")
	private String name;
	@Email(message = "your mail shoud be in format")
	@NotEmpty(message = "Email should not be empty")
	private String email;
	@NotEmpty(message = "Password should not be empty")
	@Size(min = 4,max = 12,message = "password should be between 4 to 12 charecter")
	private String password;
	@NotEmpty(message = "About should not be empty")
	private String about;
}
