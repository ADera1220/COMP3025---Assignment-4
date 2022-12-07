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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

// TaskAdapter class for the ToDoTasks
class TaskAdapter(private val dataSet: List<ToDoTask>) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        // We do not need to ass the "notes", "hasDueDate", or "id" here, because they are not shown in the Recycler View
        val name: TextView
        val dueDate: TextView
        val completed: CheckBox

        init {
            name = view.findViewById(R.id.Task_Name_TextView)
            dueDate = view.findViewById(R.id.Due_Date_TextView)
            completed = view.findViewById(R.id.Task_Completed_CheckBox)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /**
         * The onBindViewHolder does not bind the "notes" field in the RecyclerView,
         * I am not currently sure how this will work for the Details page when the "Edit"
         * button is clicked, another adapter, maybe?
         */
        holder.name.text = dataSet[position].name
        holder.completed.isChecked = dataSet[position].completed
        holder.dueDate.text = dataSet[position].dueDate

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}