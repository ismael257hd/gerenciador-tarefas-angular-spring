package com.gerenciador_tarefas.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;   
    
    @NotBlank(message = "O título é obrigatório.")
    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres.")
    private String title; 
    
    private String description;
    
    private boolean completed;    
    
}
