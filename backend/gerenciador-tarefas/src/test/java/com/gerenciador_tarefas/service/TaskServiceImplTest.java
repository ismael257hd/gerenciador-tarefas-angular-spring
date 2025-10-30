package com.gerenciador_tarefas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.gerenciador_tarefas.model.Task;
import com.gerenciador_tarefas.repository.TaskRepository;

class TaskServiceImplTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskServiceImpl service; 

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Task();
        task.setId(1L);
        task.setTitle("Tarefa 1");
        task.setDescription("Descrição");
        task.setCompleted(false);
    }

    @Test
    void deveListarTodasTarefas() {
        when(repository.findAll()).thenReturn(List.of(task));

        List<Task> result = service.listar();

        assertEquals(1, result.size());
        assertEquals("Tarefa 1", result.get(0).getTitle());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarTarefaPorId() {
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        Task result = service.buscarPorId(1L);

        assertEquals("Tarefa 1", result.getTitle());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoNaoEncontrarTarefa() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void deveSalvarTarefa() {
        when(repository.save(task)).thenReturn(task);

        Task result = service.salvar(task);

        assertNotNull(result);
        assertEquals("Tarefa 1", result.getTitle());
        verify(repository, times(1)).save(task);
    }

    @Test
    void deveAtualizarTarefa() {
        Task nova = new Task();
        nova.setTitle("Tarefa 1");
        nova.setDescription("Descrição nova");
        nova.setCompleted(true);

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenReturn(nova);

        Task result = service.atualizar(1L, nova);

        assertEquals("Descrição nova", result.getDescription());
        assertTrue(result.isCompleted());
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    void deveExcluirTarefa() {
        when(repository.existsById(1L)).thenReturn(true);

        service.excluir(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoExcluirInexistente() {
        when(repository.existsById(2L)).thenReturn(false);

        assertThrows(NoSuchElementException.class, () -> service.excluir(2L));
    }
}
