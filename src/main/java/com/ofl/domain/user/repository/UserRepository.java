package com.ofl.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofl.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
