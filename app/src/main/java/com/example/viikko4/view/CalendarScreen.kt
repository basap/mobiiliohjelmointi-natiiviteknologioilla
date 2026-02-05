package com.example.viikko4.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko4.model.Task
import com.example.viikko4.viewmodel.TaskViewModel

@Composable
fun CalendarScreen(
    viewModel: TaskViewModel,
    onBack: () -> Unit
) {
    val tasks by viewModel.tasks.collectAsState()
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    val groupedTasks = tasks.groupBy { it.dueDate }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calendar", style = MaterialTheme.typography.headlineMedium)
            TextButton(onClick = onBack) {
                Text("Back")
            }
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            groupedTasks.forEach { (date, tasksForDate) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(tasksForDate) { task ->
                    TaskRow(
                        task = task,
                        onToggle = { viewModel.toggleDone(task.id) },
                        onEdit = { selectedTask = task }
                    )
                }
            }
        }
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
