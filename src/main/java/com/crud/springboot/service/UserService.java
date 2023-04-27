package com.crud.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.springboot.dao.UserInfoRepository;
import com.crud.springboot.dao.UserRepository;
import com.crud.springboot.entity.User;
import com.crud.springboot.entity.UserInfo;

@Service
public class UserService {
	
@Autowired	
private UserRepository userRepository;

@Autowired
private UserInfoRepository repository;

@Autowired
private PasswordEncoder passwordEncoder;

public User createUser(User user) {
	return userRepository.save(user);
}
public List<User> createUsers(List<User> users) {
	return userRepository.saveAll(users);
}
public User getUserById(int id) {
	
	return userRepository.findById(id).orElse(null);
}
public List<User> getUsers(int pageNo, int recordCount) {
	Pageable pageable = PageRequest.of(pageNo, recordCount,Sort.by("name"));
	return userRepository.findAll(pageable).toList();
}
public User updateUser(User user) {
	User oldUser = null;
	Optional<User> optionaluser = userRepository.findById(user.getId());
	if(optionaluser.isPresent()) {
	    oldUser = optionaluser.get();
		oldUser.setName(user.getName());
		oldUser.setAddress(user.getAddress() );
		userRepository.save(oldUser);
		}else {
			return new User();
		}
	return oldUser;
}
public String deleteUserById(int id) {
	userRepository.deleteById(id);
	return "User got deleted";
}

public  String newUser(UserInfo userInfo){
	userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
	repository.save(userInfo);
	return "user added to system" ;
	
	}
}



























