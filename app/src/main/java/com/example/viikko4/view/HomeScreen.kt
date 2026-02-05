package com.example.viikko4.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikko4.viewmodel.TaskViewModel
import com.example.viikko4.model.Task

@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onOpenCalendar: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }
    var showAddDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Tasklist", fontSize = 24.sp)

            Row {
                IconButton(onClick = { showAddDialog = true }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add task"
                    )
                }

                IconButton(onClick = onOpenCalendar) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Calendar"
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = { viewModel.sortByDueDate() }) {
                Text("Sort by due")
            }
            Button(onClick = { viewModel.filterByDone() }) {
                Text("Remove done")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { viewModel.toggleDone(task.id) },
                    onEdit = { selectedTask = task }
                )
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
            nextId = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
            onDismiss = { showAddDialog = false },
            onAdd = { task ->
                viewModel.addTask(task)
                showAddDialog = false
            }
        )
    }

    selectedTask?.let {
        DetailDialog(
            task = it,
            onDismiss = { selectedTask = null },
            onSave = { updated ->
                viewModel.updateTask(updated)
                selectedTask = null
            },
            onDelete = { task ->
                viewModel.removeTask(task.id)
                selectedTask = null
            }
        )
    }
}
