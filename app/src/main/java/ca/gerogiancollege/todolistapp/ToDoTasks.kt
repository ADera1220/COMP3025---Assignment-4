package ca.gerogiancollege.todolistapp

import java.util.Date

data class ToDoTasks(
    val taskName: String,
    val taskDetails: String,
    val dueDate: String,
    val taskComplete: Boolean = false
)
