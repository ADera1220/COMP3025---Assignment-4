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

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import kotlin.random.Random


class TaskDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.task_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var taskList = (activity as MainActivity).getTaskList()
        var selectedDate: String = ""

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

        if((activity as MainActivity).alertAction == AlertAction.UPDATE) {
            taskNameEditText?.setText("")
            taskNotesEditText?.setText("")
            dueDateSwitch?.isChecked = true
            isCompletedCheckBox?.isChecked = true
        }

        // This even listener watches the CalendarView for any date selection, allowing the date to be passed in when the changes are saved
        dueDateCalendarView?.setOnDateChangeListener { view, year, month, day ->
            selectedDate = "$day-${month+1}-$year"
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
            var newTask = ToDoTask(
                generateID(),
                taskNameEditText?.text.toString(),
                taskNotesEditText?.text.toString(),
                getDateSwitchStatus(dueDateSwitch),
                getCalendarDate(dueDateSwitch, selectedDate),
                getIsCompletedStatus(isCompletedCheckBox),
                )
            (activity as MainActivity).addTask(newTask)
            (activity as MainActivity).supportFragmentManager
                .commit {
                    replace<ToDoListFragment>(R.id.Fragment_Container)
                }
        }

        deleteButton?.setOnClickListener {
        }
    }

    // Generates a Random ID that has the time of creation and a random 3 digit Int, to ensure they are unique
    private fun generateID(): String {
        return "T" + System.currentTimeMillis().toString() + "C" + Random.nextInt(100, 999)
    }

    private fun getCalendarDate(dueDateSwitch: Switch?, selectedDate: String): String {
        return if(dueDateSwitch?.isChecked == true) {
            selectedDate
        } else {
            ""
        }
    }

    // This function is necessary because the possibility of a null Switch or CheckBox view was making it impossible to pass the switch status directly
    private fun getDateSwitchStatus(dateSwitch: Switch?): Boolean {
        return dateSwitch?.isChecked == true
    }

    private fun getIsCompletedStatus(isCompleted: CheckBox?): Boolean {
        return isCompleted?.isChecked == true
    }
}































