package com.crud.springboot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo , Integer> {

	Optional<UserInfo> findByName(String username);

}
