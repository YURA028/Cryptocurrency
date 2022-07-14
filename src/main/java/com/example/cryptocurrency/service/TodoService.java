package com.example.cryptocurrency.service;

import com.example.cryptocurrency.entity.TodoEntity;
import com.example.cryptocurrency.entity.UserEntity;
import com.example.cryptocurrency.model.Todo;
import com.example.cryptocurrency.repository.TodoRepository;
import com.example.cryptocurrency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private UserRepository userRepository;

    public Todo createTodo(TodoEntity todoEntity, Long userId){
        UserEntity user = userRepository.findById(userId).get();
        todoEntity.setUser(user);
        return Todo.toModel(todoRepository.save(todoEntity));
    }

    public Todo completeTodo(Long id){
        TodoEntity todoEntity = todoRepository.findById(id).get();
        todoEntity.setCompleted(!todoEntity.getCompleted());
        return Todo.toModel(todoRepository.save(todoEntity));
    }
}
