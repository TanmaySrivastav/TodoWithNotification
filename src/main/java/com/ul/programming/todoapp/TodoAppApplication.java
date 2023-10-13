package com.ul.programming.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ul.programming.todoapp.entity.Todo;
import com.ul.programming.todoapp.entity.User;
import com.ul.programming.todoapp.repository.TodoRepository;
import com.ul.programming.todoapp.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner  {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TodoRepository todoRepository;

    public static void main(String[] args) {
        SpringApplication.run(TodoAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setPassword("adam");
        user.setUsername("adam");

        Todo todo  = new Todo();
        todo.setContent("Listen to Podcast");

        user.getTodoList().add(todo);

        userRepository.save(user);
       
    }
}
