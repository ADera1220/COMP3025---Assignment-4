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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// TaskAdapter class for the ToDoTasks
class TaskAdapter(private val dataSet: List<ToDoTasks>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val taskName: TextView
        val dueDate: TextView
        val taskComplete: CheckBox

        init {
            taskName = view.findViewById(R.id.Task_Name_TextView)
            dueDate = view.findViewById(R.id.Due_Date_TextView)
            taskComplete = view.findViewById(R.id.Task_Completed_CheckBox)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /**
         * The onBindViewHolder does not bind the details field in the RecyclerView,
         * I am not currently sure how this will work for the Details page when the "Edit"
         * button is clicked, another adapter, maybe?
         */
        holder.taskName.text = dataSet[position].taskName
        holder.dueDate.text = dataSet[position].dueDate
        holder.taskComplete.isChecked = dataSet[position].taskComplete
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}