package com.example.cryptocurrency.repository;

import com.example.cryptocurrency.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
