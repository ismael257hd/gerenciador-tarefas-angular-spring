
import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task.model';
import { TaskFormComponent } from '../task-form/task-form.component';
import { MatIconModule } from '@angular/material/icon';
import { FormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';



@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatDialogModule, MatIconModule, FormsModule, MatCheckboxModule],
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css'],
})
export class TaskListComponent implements OnInit {
  private taskService = inject(TaskService);
  private dialog = inject(MatDialog);
  private cdr = inject(ChangeDetectorRef);

  displayedColumns = ['id', 'title', 'description', 'completed', 'acoes'];
  tasks: Task[] = [];

  ngOnInit() {
    this.carregarTarefas();
  }

  carregarTarefas() {
    this.taskService.listar().subscribe({
      next: (data) => {
        this.tasks = data;
        this.cdr.detectChanges(); 
      },
      error: (err) => console.error('Erro ao carregar as tarefas', err),
    });
  }

  abrirModal(task?: Task) {
  const dialogRef = this.dialog.open(TaskFormComponent, {
    width: '550px',          
    maxWidth: '90vw',        
    disableClose: true,
    data: task ? { task } : null,
    panelClass: 'custom-dialog-container' 
  });

  dialogRef.afterClosed().subscribe((result) => {
    if (result === 'atualizar') {
      this.carregarTarefas();
    }
  });
}

  excluir(id: number) {
    if (confirm('Tem certeza que deseja excluir está tarefa?')) {      
      this.taskService.excluir(id).subscribe({
        next: () => {
          alert('Tarefa excluída com sucesso!');
          this.carregarTarefas();
        },
        error: (err) => console.error('Erro ao excluir a tarefa', err),
      });
    }
  }

  onToggleCompleted(task: Task): void {
  task.completed = !task.completed;

  this.taskService.atualizar(task).subscribe({
    next: () => console.log(`Tarefa ${task.id} atualizada com sucesso`),
    error: err => console.error('Erro ao atualizar tarefa', err)
  });
}

}