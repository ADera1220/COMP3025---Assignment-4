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
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    // A list of ToDoTasks created for the purpose of DEMO-ing the app
    val currentTasks = mutableListOf<ToDoTasks>(
        ToDoTasks("Brush teeth", "twice a day", "20/11/2022"),
        ToDoTasks("Pay Bills", "rent:$750\ncar:$457.67", "02/12/2022"),
        ToDoTasks("Reformat Laptop", "Create list of programs to reinstall", "30/11/2022"),
        ToDoTasks("laundry", "twice a day", "20/11/2023")
    )

    // Initialize the Adapter class variable
    lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a TaskAdapter with our "curerentTasks" list
        taskAdapter = TaskAdapter(currentTasks)

        // Populate the list items into the RecyclerView
        populateToDoList()
    }

    // This function makes it so the "Add a new Task" Button sends the app to the "activity_details_page", for DEMO only
    fun addTaskClicked(view: View) {
        setContentView(R.layout.activity_details_page)
    }

    // This function makes it so the "Cancel" Button sends the app back to the "activity_main", for DEMO only
    fun addCancelClicked(view: View) {
        setContentView(R.layout.activity_main)
        populateToDoList()
    }

    // This function allows the RecyclerView to be filled with tasks, this may change in the final version
    fun populateToDoList() {
        val toDoList: RecyclerView = findViewById(R.id.Todo_Recycler_View)
        toDoList.layoutManager = LinearLayoutManager(this)
        toDoList.adapter = taskAdapter
    }
}