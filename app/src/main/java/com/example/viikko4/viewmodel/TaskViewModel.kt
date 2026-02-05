package com.example.viikko4.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.viikko4.model.*
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks

    init {
        _tasks.value = mockTasks
    }

    fun addTask(task: Task) {
        _tasks.update { it + task }
    }

    fun toggleDone(id: Int) {
        _tasks.update {
            it.map { task ->
                if (task.id == id) task.copy(done = !task.done) else task
            }
        }
    }

    fun removeTask(id: Int) {
        _tasks.update {
            it.filterNot { task -> task.id == id }
        }
    }

    fun updateTask(updated: Task) {
        _tasks.update {
            it.map { task ->
                if (task.id == updated.id) updated else task
            }
        }
    }

    fun sortByDueDate() {
        _tasks.update {
            it.sortedBy { task -> task.dueDate }
        }
    }

    fun filterByDone() {
        _tasks.update {
            it.filter { task -> !task.done }
        }
    }
}