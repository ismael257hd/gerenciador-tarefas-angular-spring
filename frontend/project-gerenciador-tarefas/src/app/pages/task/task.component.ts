import { Component } from '@angular/core';
import { TaskListComponent } from '../../components/task-list/task-list.component';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
@Component({
    selector:'app-task',
    standalone:true,
    imports:[CommonModule,MatCardModule,TaskListComponent],
    templateUrl:'./task.component.html',
    styleUrls:['./task.component.css']})
export class TaskComponent{}