package com.example.viikko1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1.domain.Task
import java.time.LocalDate

@Composable
fun HomeScreen(
    taskViewModel: TaskViewModel = viewModel()
) {
    var newTaskTitle by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Task list", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { taskViewModel.filterByDone(false) }) {
                Text("Active tasks")
            }
            Button(onClick = { taskViewModel.filterByDone(true) }) {
                Text("Done tasks")
            }
            Button(onClick = { taskViewModel.sortByDueDate() }) {
                Text("Sort by due date")
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier.weight(1f),
                label = { Text("New task") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    taskViewModel.addTask(
                        Task(
                            id = taskViewModel.tasks.size + 1,
                            title = newTaskTitle,
                            description = "",
                            priority = 1,
                            dueDate = LocalDate.now().plusDays(1),
                            done = false
                        )
                    )
                    newTaskTitle = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(taskViewModel.tasks) { task ->
                TaskRow(
                    task = task,
                    onToggle = { taskViewModel.toggleDone(task.id) },
                    onRemove = { taskViewModel.removeTask(task.id) }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.done,
                    onCheckedChange = { onToggle() }
                )

                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Button(onClick = onRemove) {
                Text("Delete")
            }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text("Description: ${task.description}")
        Text("Priority: ${task.priority}")
        Text("Due: ${task.dueDate}")
    }
}

