package io.pritamproduction.blog.repositoris;

import io.pritamproduction.blog.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
	Optional<User> findByEmail(String email);
}
