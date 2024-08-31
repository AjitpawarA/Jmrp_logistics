package com.alpha.jmrplogistics.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alpha.jmrplogistics.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
