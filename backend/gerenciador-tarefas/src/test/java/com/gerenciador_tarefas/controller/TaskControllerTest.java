package com.gerenciador_tarefas.controller;

import com.gerenciador_tarefas.model.Task;

import com.gerenciador_tarefas.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService service;

    @Test
    void deveRetornarListaDeTarefas() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarefa 1");
        task.setDescription("Descrição");
        task.setCompleted(false);
        
        when(service.listar()).thenReturn(List.of(task));

        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Tarefa 1"));
    }

    @Test
    void deveSalvarTarefaComSucesso() throws Exception {               
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Tarefa 2");
        task.setDescription("Descrição");
        task.setCompleted(false);
        when(service.salvar(any(Task.class))).thenReturn(task);

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {"title": "Tarefa 2", "description": "Descrição", "completed": false}
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Tarefa 2"));
    }

    @Test
    void deveRetornarErro400ParaCamposInvalidos() throws Exception {
    	mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"title": "", "description": "", "completed": false}
                        """))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.errors.title", containsInAnyOrder(
                "O título é obrigatório.",
                "O título deve ter pelo menos 3 caracteres."
        )));
    }
}