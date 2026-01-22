package com.example.viikko1.domain

import java.time.LocalDate

val mockTasks = listOf(
    Task(1, "Task 1", "Desc 1", 1, LocalDate.now().plusDays(3), true),
    Task(2, "Task 2", "Desc 2", 2, LocalDate.now().plusDays(5), false),
    Task(3, "Task 3", "Desc 3", 3, LocalDate.now().plusDays(1), true),
    Task(4, "Task 4", "Desc 4", 4, LocalDate.now(), false),
    Task(5, "Task 5", "Desc 5", 5, LocalDate.now().plusDays(2), false),
    Task(6, "Task 6", "Desc 6", 6, LocalDate.now(), false)
)
