package com.demo.jwt.backend.repo;

import com.demo.jwt.backend.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByLogin(String login);
}
