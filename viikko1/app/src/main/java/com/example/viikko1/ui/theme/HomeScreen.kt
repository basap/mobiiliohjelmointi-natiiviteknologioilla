package com.example.viikko1.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viikko1.domain.mockTasks
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import com.example.viikko1.domain.*
import androidx.compose.material3.Button

@Composable
fun HomeScreen() {

    var tasks by remember {
        mutableStateOf(mockTasks)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tasklist",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = {
                val newTask = Task(
                    id = (tasks.maxOfOrNull { it.id } ?: 0) + 1,
                    title = "New task",
                    description = "Added from UI",
                    priority = 1,
                    dueDate = "2026-01-31",
                    done = false
                )
                tasks = addTask(tasks, newTask)
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text("Add task")
        }

        Button(
            onClick = {
                val doneTasks = filterByDone(tasks, true)
                val notDoneTasks = filterByDone(tasks, false)
                tasks = doneTasks + notDoneTasks
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Text("Done tasks first")
        }

        Button(
            onClick = {
                tasks = sortByDueDate(tasks)
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Sort by due date")
        }

        tasks.forEach { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable {
                        tasks = toggleDone(tasks, task.id)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (task.done)
                        "✔ ${task.title} (due ${task.dueDate})"
                    else
                        "○ ${task.title} (due ${task.dueDate})",
                    fontSize = 18.sp
                )
            }
        }
    }
}