package com.example.viikko2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko2.domain.*

class TaskViewModel : ViewModel() {

    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        tasks = mockTasks
    }

    fun addTask(task: Task) {
        tasks = addTask(tasks, task)
    }

    fun toggleDone(id: Int) {
        tasks = toggleDone(tasks, id)
    }

    fun removeTask(id: Int) {
        tasks = tasks.filterNot { it.id == id }
    }

    fun filterByDone(done: Boolean) {
        tasks = if (done) {
            tasks.filter { !it.done }
        } else {
            tasks
        }
    }

    fun sortByDueDate() {
        tasks = sortByDueDate(tasks)
    }
}