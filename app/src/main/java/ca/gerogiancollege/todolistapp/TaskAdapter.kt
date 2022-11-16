package ca.gerogiancollege.todolistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_layout, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskName.text = dataSet[position].taskName
        holder.dueDate.text = dataSet[position].dueDate
        holder.taskComplete.isChecked = dataSet[position].taskComplete
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}