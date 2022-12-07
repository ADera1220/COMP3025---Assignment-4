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
 *
 * Version 2.2
 *      - Completed ADD functionality for new tasks
 *      - Corrected issue with Data Class and RealTime Database causing the completion checkbox to not work
 *      - Task list is now handled by exclusively Firebase
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    // Initialize the Adapter class variable
    private lateinit var ToDoTasks: MutableList<ToDoTask>
    lateinit var db: DatabaseReference
    lateinit var taskAdapter: TaskAdapter
    lateinit var addTaskButton: Button
    lateinit var alertAction: AlertAction
    lateinit var currentTask: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Adapter, Database Reference, and the MutableList of Tasks
        db = Firebase.database.reference
        ToDoTasks = mutableListOf<ToDoTask>()
        taskAdapter = TaskAdapter(ToDoTasks)

    }

    fun addTask(toDoTask: ToDoTask) {
        db.child("ToDoTasks").child(toDoTask.id.toString()).setValue(toDoTask)
    }

    fun updateTask(toDoTask: ToDoTask?) {
        db.child("ToDoTasks").child(toDoTask?.id.toString()).setValue(toDoTask)
    }

    fun deleteTask(toDoTask: ToDoTask?) {
        db.child("ToDoTasks").child(toDoTask?.id.toString()).removeValue()
    }

    fun getTaskList(): MutableList<ToDoTask> {
        return ToDoTasks
    }

    // This function allows the RecyclerView to be filled with tasks, this may change in the final version
    fun populateToDoList() {
        val toDoList: RecyclerView? = findViewById(R.id.Todo_Recycler_View)
        toDoList?.layoutManager = LinearLayoutManager(this)
        toDoList?.adapter = taskAdapter
    }

    fun addTaskEventListener(dbReference: DatabaseReference) {
        val taskListListener = object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                ToDoTasks.clear()
                val toDoTaskDB = dataSnapshot.child("ToDoTasks").children

                for(toDoTask in toDoTaskDB) {
                    var newTask = toDoTask.getValue(ToDoTask::class.java)

                    if(newTask != null) {
                        ToDoTasks.add(newTask)
                        taskAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("tvShowError", "LoadTVShow: Cancelled", databaseError.toException())
            }
        }
        dbReference.addValueEventListener(taskListListener)
    }
}