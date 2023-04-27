package com.crud.springboot.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.crud.springboot.dao.UserInfoRepository;
import com.crud.springboot.entity.UserInfo;


@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional <UserInfo> userInfo = repository.findByName(username);
		
		return userInfo.map( UserInfoUserDetails :: new).orElseThrow(()-> new UsernameNotFoundException("user not found"+username));
		
	}

}
