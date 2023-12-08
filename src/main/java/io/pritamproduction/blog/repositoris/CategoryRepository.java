package io.pritamproduction.blog.repositoris;

import io.pritamproduction.blog.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
