package com.example.viikko4.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko4.model.Task

@Composable
fun AddTaskDialog(
    nextId: Int,
    onDismiss: () -> Unit,
    onAdd: (Task) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add task") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due date (YYYY-MM-DD)") }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        onAdd(
                            Task(
                                id = nextId,
                                title = title,
                                description = description,
                                priority = 1,
                                dueDate = if (dueDate.isBlank()) "2026-02-01" else dueDate,
                                done = false
                            )
                        )
                    }
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
