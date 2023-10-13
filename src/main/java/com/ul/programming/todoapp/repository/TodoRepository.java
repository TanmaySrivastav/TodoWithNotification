package com.ul.programming.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ul.programming.todoapp.entity.Todo;
import com.ul.programming.todoapp.entity.User;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
