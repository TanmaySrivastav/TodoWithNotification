package com.ul.programming.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ul.programming.todoapp.entity.Todo;
import com.ul.programming.todoapp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
}
