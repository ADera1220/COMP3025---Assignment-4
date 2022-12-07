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
 * Version 2.1
 *      - Restructured the ToDoTask Data Class to reflect required data
 *      - Created Enum Class AlertAction to help identify which CRUD action is being done
 *      - Reconfigured Layouts to use Fragments
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ca.gerogiancollege.todolistapp.ToDoListFragment as ToDoListFragment

class MainActivity : AppCompatActivity() {

    // A list of ToDoTasks created for the purpose of DEMO-ing the app
    val currentTasks = mutableListOf<ToDoTask>(
        ToDoTask("Task1X33","Brush teeth", "twice a day", true, "20/11/2022", false),
        ToDoTask("Task1X34", "Pay Bills", "rent:$750\ncar:$457.67", true,  "02/12/2022", false),
        ToDoTask("Task1X35","Reformat Laptop", "Create list of programs to reinstall", false, "", true),
        ToDoTask("Task1X36","laundry", "twice a day", true, "20/11/2023", false)
    )

    // Initialize the Adapter class variable
    private lateinit var db: DatabaseReference
    private lateinit var ToDoTasks: MutableList<ToDoTask>
    lateinit var taskAdapter: TaskAdapter
    lateinit var addTaskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Adapter, Database Reference, and the MutableList of Tasks
        db = Firebase.database.reference
        ToDoTasks = mutableListOf<ToDoTask>()
        taskAdapter = TaskAdapter(currentTasks)

    }
}