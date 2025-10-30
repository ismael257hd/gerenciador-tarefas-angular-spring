package com.gerenciador_tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gerenciador_tarefas.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}