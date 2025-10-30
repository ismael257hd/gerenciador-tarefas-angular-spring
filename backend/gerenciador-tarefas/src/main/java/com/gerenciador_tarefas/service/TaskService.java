package com.gerenciador_tarefas.service;

import java.util.List;

import com.gerenciador_tarefas.model.Task;


public interface TaskService {
	
    public List<Task> listar();

    public Task buscarPorId(Long id);

    public Task salvar(Task task);

    public Task atualizar(Long id, Task taskAtualizado);

    public void excluir(Long id);

}
