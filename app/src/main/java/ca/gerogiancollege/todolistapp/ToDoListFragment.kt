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

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ToDoListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.todo_list_fragment, container, false)
    }

    /**
     * There was an interesting dilemna when I switched to a fragment view, how do I populate the RecyclerView
     * while also still allowing data to pass between the fragments. The answer, to me, was to take advantage
     * of the (activity as MainActivity) to call functions that had to be delcared in the MainActivity, even
     * though they needed to be used inside the ToDoList Fragment
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // These two lines populate the recycler view by calling the functions that talk to Firebase
        (activity as MainActivity).populateToDoList()
        (activity as MainActivity).addTaskEventListener((activity as MainActivity).db)

        // This handles clicks on the task items themselves, passing the item and its position to the details page
        (activity as MainActivity).taskAdapter.onToDoTaskClick = { toDoTask, position ->
            (activity as MainActivity).alertAction = AlertAction.UPDATE
            (activity as MainActivity).currentTask = toDoTask
            (activity as MainActivity).curentTaskPosition = position

            (activity as MainActivity).supportFragmentManager
                .commit {
                    replace<TaskDetailsFragment>(R.id.Fragment_Container)
                }
        }

        // The addTaskButton is declared in the MainActivity, but it's function is here in the fragment
        var addTaskButton: View? = getView()?.findViewById(R.id.Add_Task_Button)
        addTaskButton?.setOnClickListener {
            (activity as MainActivity).alertAction = AlertAction.ADD
            (activity as MainActivity).supportFragmentManager
                .commit {
                    replace<TaskDetailsFragment>(R.id.Fragment_Container)
                }
        }
    }
}