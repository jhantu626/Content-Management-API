package io.pritamproduction.blog.repositoris;

import io.pritamproduction.blog.entites.Category;
import io.pritamproduction.blog.entites.Post;
import io.pritamproduction.blog.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	@Query("select p from Post as p where p.title like :key")
	List<Post> findByTitleContaining(@Param("key") String title);

}
