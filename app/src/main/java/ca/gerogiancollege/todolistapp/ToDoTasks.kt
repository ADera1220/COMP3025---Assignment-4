package ca.gerogiancollege.todolistapp

/**
 * COMP 3025 | Mobile and Pervasive Computing
 * Assignment 4 | ToDo List application
 * December 1st, 2023
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
 *
 * Version 1.2
 *      - Replaced Date EditText with CalendarView, as per instructions
 *      - Redesigned details page to accommodate the changed layout
 *      - Added toggle switch for Due Date, no functionality yet
 *
 * Version 1.3
 *      - Added a new Frame for "Task Completed" Checkbox in the Details Layout
 *      - Re-designed Details layout to accomodate the changes
 *      - Re-configured colouring of CalendarView to make it easier to read in dark mode
 *
 * Version 2.0
 *      - Re-Assigned git repo to "https://github.com/ADera1220/COMP3025---Assignment-4"
 *      - Added Firebase Implementation
 *      - Created Realtime Database for ToDo List
 *      - Changed ToDoTasks Model to include an "id" attribute
 *
 */

// Data class for ToDoTasks
data class ToDoTasks(
    val id: String,
    // The task description
    val taskName: String,
    // The additional notes
    val taskDetails: String,
    // The due date, currently in String, will be in DATE form in final version
    val dueDate: String,
    // this boolean will track the completion of a task constantly, initialized as false, always
    val taskComplete: Boolean = false
)
