package io.pritamproduction.blog;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Blog Application Api's",
				description = "All the operation related blog application has done like user,comment,post,category",
				termsOfService = "T&C",
				contact = @Contact(
						name = "Jhantu Bala",
						email = "jhantubala626@gmail.com"
				),
				license = @License(name="0123456789"),
				version = "v1"
		),
		servers = {
				@Server(
				description = "Prod",
				url = "http://localhost:9494/"
				),
				@Server(
				description = "Test",
				url = "http://localhost:9494/"
				),

		}
)
public class BlogAppApisApplication implements CommandLineRunner {


	@Autowired
	private PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class,args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("jhantu@123"));
	}
}
