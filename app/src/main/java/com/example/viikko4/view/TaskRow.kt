package com.example.viikko4.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viikko4.model.Task

@Composable
fun TaskRow(
    task: Task,
    onToggle: () -> Unit,
    onEdit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = task.done,
            onCheckedChange = { onToggle() }
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(due ${task.dueDate})",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Button(onClick = onEdit) {
            Text("Edit")
        }
    }
}