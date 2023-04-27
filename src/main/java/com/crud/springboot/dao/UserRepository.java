package com.crud.springboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
