package com.example.viikko6.view

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
import com.example.viikko6.viewmodel.TaskViewModel
import com.example.viikko6.data.model.TaskEntity

@Composable
fun HomeScreen(
    viewModel: TaskViewModel,
    onOpenCalendar: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<TaskEntity?>(null) }
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
                    Icon(Icons.Default.Add, contentDescription = "Add task")
                }

                IconButton(onClick = onOpenCalendar) {
                    Icon(Icons.Default.DateRange, contentDescription = "Calendar")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { viewModel.toggleDone(task) },
                    onEdit = { selectedTask = task }
                )
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
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
