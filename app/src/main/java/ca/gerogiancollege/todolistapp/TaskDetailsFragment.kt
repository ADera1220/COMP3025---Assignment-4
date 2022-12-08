package ca.gerogiancollege.todolistapp

/**
 * COMP 3025 | Mobile and Pervasive Computing
 * Assignment 4 | ToDo List application
 * December 7th, 2022
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
 *
 * Version 2.3
 *      - UPDATE functionality is added, CalendarView does not currently behave appropriately
 *      - DELETE functionality is added
 *      - an "are you sure" prompt has been added for DELETE requests
 */

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import java.time.LocalDate
import java.util.*
import kotlin.random.Random


class TaskDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.task_details_fragment, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialized variable to hold the selected date in the CalendarView
        var selectedDate: String = ""
        var currentTask: ToDoTask? = null

        // Initialize the buttons in the Details View
        var cancelButton: View? = getView()?.findViewById(R.id.Cancel_Button)
        var deleteButton: View? = getView()?.findViewById(R.id.Delete_Button)
        var saveButton: View? = getView()?.findViewById(R.id.Save_Button)

        // Initialize the Views for information
        val taskNameEditText: EditText? = getView()?.findViewById(R.id.Task_Name_EditText)
        val taskNotesEditText: EditText? = getView()?.findViewById(R.id.Task_Details_EditText)
        val dueDateSwitch: Switch? = getView()?.findViewById(R.id.Due_Date_Enabled_Switch)
        val dueDateCalendarView: CalendarView? = getView()?.findViewById(R.id.Due_Date_CalendarView)
        val isCompletedCheckBox: CheckBox? = getView()?.findViewById(R.id.Details_Page_CheckBox)

        // The following if statement is only for "UPDATE" statuses
        if((activity as MainActivity).alertAction == AlertAction.UPDATE) {
            // check the current task list for the task that was passed into the details page
            var taskList = (activity as MainActivity).getTaskList()
            for(task in taskList) {
                if (task.id == (activity as MainActivity).currentTask.id) {
                    currentTask = task
                }
            }

            // Initialize the input fields with the data from the task we are updating
            taskNameEditText?.setText(currentTask?.name)
            taskNotesEditText?.setText(currentTask?.notes)
            dueDateSwitch?.isChecked = currentTask?.hasDueDate == true
            isCompletedCheckBox?.isChecked = currentTask?.completed == true

        }

        // This even listener watches the CalendarView for any date selection, allowing the date to be passed in when the changes are saved
        dueDateCalendarView?.setOnDateChangeListener { view, year, month, day ->
            selectedDate = "$year-${month+1}-$day"
        }

        // Cancel event Listener Returns the user to the ToDoList Fragment without performing any other action
        cancelButton?.setOnClickListener {
            (activity as MainActivity).supportFragmentManager
                .commit {
                    replace<ToDoListFragment>(R.id.Fragment_Container)
                }
        }

        // Save event listener performs an addTask action with the current data in the input fields, occurs when there is an AlertAction.ADD flag
        saveButton?.setOnClickListener {
            when((activity as MainActivity).alertAction) {
                // Id we are creating a new task
                AlertAction.ADD -> {
                    var newTask = ToDoTask(
                        generateID(),
                        taskNameEditText?.text.toString(),
                        taskNotesEditText?.text.toString(),
                        getDateSwitchStatus(dueDateSwitch),
                        getCalendarDate(dueDateSwitch, selectedDate),
                        getIsCompletedStatus(isCompletedCheckBox),
                    )
                    (activity as MainActivity).addTask(newTask)
                }
                // If we are updating an existing task
                AlertAction.UPDATE -> {
                    var newTask = ToDoTask(
                        (activity as MainActivity).currentTask.id,
                        taskNameEditText?.text.toString(),
                        taskNotesEditText?.text.toString(),
                        getDateSwitchStatus(dueDateSwitch),
                        getCalendarDate(dueDateSwitch, selectedDate),
                        getIsCompletedStatus(isCompletedCheckBox),
                    )
                    (activity as MainActivity).updateTask(newTask)
                }
            }

            (activity as MainActivity).supportFragmentManager
                .commit {
                    replace<ToDoListFragment>(R.id.Fragment_Container)
                }
        }

        /**
         * We do not need to use a DELETE AlertAction, because the functionality was built in to the
         * UPDATE action. Below, if the user clicks the DELETE button, they will receive a Dialog box
         * asking if they are sure, if they agree, the task is deleted, and the user is returned to the
         * RecyclerView
         */
        deleteButton?.setOnClickListener {
            if((activity as MainActivity).alertAction == AlertAction.UPDATE) {
                var builder = AlertDialog.Builder((activity as MainActivity))

                builder.setTitle("Delete this task?")
                builder.setView((activity as MainActivity).layoutInflater.inflate(R.layout.delete_confirm_layout, null))
                builder.setPositiveButton(getString(R.string.delete_task)) { dialog, _ ->
                    dialog.dismiss()
                    (activity as MainActivity).deleteTask(currentTask)
                    (activity as MainActivity).supportFragmentManager
                        .commit {
                            replace<ToDoListFragment>(R.id.Fragment_Container)
                        }
                }

                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
            }

        }


    }

    // Generates a Random ID that has the time of creation and a random 3 digit Int, to ensure they are unique
    private fun generateID(): String {
        return "T" + System.currentTimeMillis().toString() + "C" + Random.nextInt(100, 999)
    }

    // This function checks the status of the dueDateSwitch and if it is enabled, returns the current date
    private fun getCalendarDate(dueDateSwitch: Switch?, selectedDate: String): String {
        return if(dueDateSwitch?.isChecked == true) {
            selectedDate
        } else {
            ""
        }
    }

    // This function checks the status of the dateSwitch and returns the outcome
    private fun getDateSwitchStatus(dateSwitch: Switch?): Boolean {
        return dateSwitch?.isChecked == true
    }

    // This function checks the status of the isCompleted switch, returning the outcome
    private fun getIsCompletedStatus(isCompleted: CheckBox?): Boolean {
        return isCompleted?.isChecked == true
    }
}































