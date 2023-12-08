package io.pritamproduction.blog.security;

import io.pritamproduction.blog.entites.User;
import io.pritamproduction.blog.exception.ResourceNotFound;
import io.pritamproduction.blog.repositoris.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class customUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		//loading user from database by email
		User user=this.userRepo.findByEmail(s).orElseThrow(()->new ResourceNotFound("user","email: "+s,0));

		return user;
	}
}












