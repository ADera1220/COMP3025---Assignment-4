package ca.gerogiancollege.todolistapp

/**
 * COMP 3025 | Mobile and Pervasive Computing
 * Assignment 3 | ToDo List application
 * November 15, 2023
 * Adam Dera | Student #: 200422676
 *
 * Version 1.0
 *      - Created main activity
 *      - Created to-do task layout for the recycler view
 *      - Implemented colour theming and borders
 *
 * Version 1.1
 *      - Created the Details page activity
 *      - Implemented theming on Details page
 *      - Added static data to DEMO layout
 *      - Added code to allow switching between layouts, FOR DEMO ONLY
 *      - Created Adapter Class "TaskAdapter"
 *      - Created Data Class "ToDoTasks"
 */

// Data class for ToDoTasks
data class ToDoTasks(
    // The task description
    val taskName: String,
    // The additional notes
    val taskDetails: String,
    // The due date, currently in String, will be in DATE form in final version
    val dueDate: String,
    // this boolean will track the completion of a task constantly, initialized as false, always
    val taskComplete: Boolean = false
)
