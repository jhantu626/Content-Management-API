package io.pritamproduction.blog.repositoris;

import io.pritamproduction.blog.entites.comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface commentRepo extends JpaRepository<comment,Integer> {

}
