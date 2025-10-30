import { Component, Inject, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskService } from '../../services/task.service';
import { Task } from '../../models/task.model';
import { MatIconModule } from '@angular/material/icon';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [CommonModule, FormsModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css'],
})
export class TaskFormComponent implements OnInit {
  private cdr = inject(ChangeDetectorRef);
  private taskService = inject(TaskService);
  private dialogRef = inject(MatDialogRef<TaskFormComponent>);
  task: Task = { title: '', description: '', completed: false };
  isEdit = false;

  constructor(@Inject(MAT_DIALOG_DATA) public data: { task?: Task }) {}

  ngOnInit() {
    if (this.data?.task) {
      this.task = { ...this.data.task };
      this.isEdit = true;
    }
  }

  serverErrors: any = {};

  salvar() {
    this.serverErrors = {}; 

    const observer = {
      next: () => {
        alert(this.isEdit ? 'Atualizado com sucesso!' : 'Cadastrado com sucesso!');
        this.dialogRef.close('atualizar');
      },
      error: (err: any) => {
        console.log('Erro recebido do backend:', err);

        if (err.status === 400 && err.error) {
          if (typeof err.error === 'object') {
            this.serverErrors = err.error.errors || {};          
          } else {
            try {
              const parsed = JSON.parse(err.error);
              this.serverErrors = parsed.errors || {};
            } catch {
              this.serverErrors = { general: 'Erro de validação sem detalhes.' };
            }
          }
        } else {
          this.serverErrors = { general: 'Erro inesperado. Contate o suporte.' };
        }
        this.cdr.detectChanges();
      }
    };

    if (this.isEdit) {
      this.taskService.atualizar(this.task).subscribe(observer);
    } else {
      this.taskService.inserir(this.task).subscribe(observer);
    }
  }

  cancelar() {
    this.dialogRef.close();
  }
}