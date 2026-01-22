package com.example.viikko1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko1.domain.Task
import com.example.viikko1.domain.mockTasks

class TaskViewModel : ViewModel() {

    private val allTasks = mutableStateOf(listOf<Task>())
    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        allTasks.value = mockTasks
        tasks = mockTasks
    }

    fun addTask(task: Task) {
        allTasks.value += task
        tasks = allTasks.value
    }

    fun toggleDone(id: Int) {
        allTasks.value = allTasks.value.map {
            if(it.id == id) it.copy(done = !it.done) else it
        }
        tasks = allTasks.value
    }

    fun removeTask(id: Int) {
        allTasks.value = allTasks.value.filterNot { it.id == id }
        tasks = allTasks.value
    }

    fun filterByDone(done: Boolean) {
        tasks = allTasks.value.filter { it.done == done }
    }

    fun sortByDueDate() {
        tasks = tasks.sortedBy { it.dueDate }
    }
}