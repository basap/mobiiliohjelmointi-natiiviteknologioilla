package com.example.viikko2.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko2.domain.Task
import com.example.viikko2.viewmodel.TaskViewModel

@Composable
fun HomeScreen(
    viewModel: TaskViewModel = viewModel()
) {

    val tasks = viewModel.tasks
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Tasklist",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("New task") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (newTaskTitle.isNotBlank()) {
                        val newTask = Task(
                            id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                            title = newTaskTitle,
                            description = "",
                            priority = 1,
                            dueDate = "2026-02-01",
                            done = false
                        )
                        viewModel.addTask(newTask)
                        newTaskTitle = ""
                    }
                }
            ) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { viewModel.sortByDueDate() }) {
                Text("Sort by due")
            }
            Button(onClick = { viewModel.filterByDone(true) }) {
                Text("Remove done tasks")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { viewModel.toggleDone(task.id) },
                    onRemove = { viewModel.removeTask(task.id) }
                )
            }
        }
    }
}

@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = task.done,
            onCheckedChange = { onToggle() }
        )

        Text(
            text = "${task.title} (due ${task.dueDate})",
            modifier = Modifier.weight(1f)
        )

        IconButton(onClick = onRemove) {
            Text("❌")
        }
    }
}
