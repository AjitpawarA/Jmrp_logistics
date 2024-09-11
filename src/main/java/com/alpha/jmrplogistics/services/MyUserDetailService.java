package com.alpha.jmrplogistics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alpha.jmrplogistics.entity.User;
import com.alpha.jmrplogistics.entity.Userprincipal;
import com.alpha.jmrplogistics.exception.UserNotFoundException;
import com.alpha.jmrplogistics.repo.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userRepository.findByUsername(username);
			if(user==null) {
				throw new UserNotFoundException("User not found");
			}
		return new Userprincipal(user);
	}

}
