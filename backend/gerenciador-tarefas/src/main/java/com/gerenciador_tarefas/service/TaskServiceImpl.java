package com.gerenciador_tarefas.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.gerenciador_tarefas.model.Task;
import com.gerenciador_tarefas.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }
    
    public List<Task> listar() {
        return repository.findAll();
    }

    public Task buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarefa não encontrada: " + id));
    }

    public Task salvar(Task task) {
        return repository.save(task);
    }

    public Task atualizar(Long id, Task taskAtualizado) {
        Task existente = buscarPorId(id);
        existente.setTitle(taskAtualizado.getTitle());
        existente.setDescription(taskAtualizado.getDescription());
        existente.setCompleted(taskAtualizado.isCompleted());
        return repository.save(existente);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Tarefa não encontrada");
        }
        repository.deleteById(id);
    }

}
