package com.gerenciador_tarefas.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciador_tarefas.model.Task;
import com.gerenciador_tarefas.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	private final TaskService service;
    
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> excluir(@PathVariable Long id) {        
        service.excluir(id);
        return ResponseEntity.ok(Map.of("mensagem", "Tarefa removida com sucesso!"));
    }
    
    
    @PostMapping
    public ResponseEntity<?> inserir(@Valid @RequestBody Task task, BindingResult result) {
    	if (result.hasErrors()) {
    		Map<String, List<String>> errors = result.getFieldErrors()
    			    .stream()
    			    .collect(Collectors.groupingBy(
    			        FieldError::getField,
    			        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
    			    ));

    			Map<String, Object> response = new HashMap<>();
    			response.put("message", "Erro de validação nos campos");
    			response.put("errors", errors);

    	    return ResponseEntity
    	            .badRequest()
    	            .contentType(MediaType.APPLICATION_JSON)
    	            .body(response);
    	}

        Task saved = service.salvar(task);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public Task buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Task taskAtualizado, BindingResult result) {
    	if (result.hasErrors()) {
    		Map<String, List<String>> errors = result.getFieldErrors()
    			    .stream()
    			    .collect(Collectors.groupingBy(
    			        FieldError::getField,
    			        Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
    			    ));

    			Map<String, Object> response = new HashMap<>();
    			response.put("message", "Erro de validação nos campos");
    			response.put("errors", errors);

    	    return ResponseEntity
    	            .badRequest()
    	            .contentType(MediaType.APPLICATION_JSON)
    	            .body(response);
    	}
        
        Task atualizado = service.atualizar(id, taskAtualizado);
        return ResponseEntity.ok(atualizado);
    }
}
