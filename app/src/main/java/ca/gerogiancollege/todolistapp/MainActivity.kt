package ca.gerogiancollege.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    val currentTasks = mutableListOf<ToDoTasks>(
        ToDoTasks("Brush teeth", "twice a day", "20/11/2022"),
        ToDoTasks("Pay Bills", "rent:$750\ncar:$457.67", "02/12/2022"),
        ToDoTasks("Reformat Laptop", "Create list of programs to reinstall", "30/11/2022"),
        ToDoTasks("laundry", "twice a day", "20/11/2023")
    )

    lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskAdapter = TaskAdapter(currentTasks)

        val toDoList: RecyclerView = findViewById(R.id.Todo_Recycler_View)
        toDoList.layoutManager = LinearLayoutManager(this)
        toDoList.adapter = taskAdapter
    }
}