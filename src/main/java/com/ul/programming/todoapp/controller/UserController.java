package com.ul.programming.todoapp.controller;

import org.springframework.web.bind.annotation.*;

import com.ul.programming.todoapp.entity.Todo;
import com.ul.programming.todoapp.entity.User;
import com.ul.programming.todoapp.repository.TodoRepository;
import com.ul.programming.todoapp.repository.UserRepository;
import com.ul.programming.todoapp.request.AddTodoRequest;
import com.ul.programming.todoapp.request.AddUserRequest;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

   private UserRepository userRepository;
   private TodoRepository todoRepository;

    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId){
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public User addUser(@RequestBody AddUserRequest userRequest){
    	System.out.println("User POST called "+userRequest);
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        System.out.println("POST call "+userRequest.getUsername());
        System.out.println("POST call "+userRequest.getPassword());
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/todos")
    public void addTodo(@PathVariable Long userId,@RequestBody AddTodoRequest  todoRequest){
    	System.out.println("Todo User POST called "+userId+"="+todoRequest);
    	User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    	Todo todo=new Todo();
    	todo.setContent(todoRequest.getContent());
    	user.getTodoList().add(todo);
    	System.out.println("POST call "+todoRequest.getContent());
    	//todoRepository.save(todo);
    	userRepository.save(user);
        
    }

    @PostMapping("/todos/{todoId}")
    public void toggleTodoCompleted( @PathVariable Long todoId){
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(!todo.getCompleted());
        todoRepository.save(todo);
    }


    @DeleteMapping("{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
        User user=userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException()); 
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);
    }

}
