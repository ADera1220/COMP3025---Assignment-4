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
 *
 * Version 2.4
 *      - Added dialog for "Cancel" button to ask if unsaved changes should be dropped
 *      - Removed ability to uncheck boxes from ToDoList
 *      - Made Checkboxes Theme appropriate
 *      - Added grey style to list items that are "completed"
 *      - Increased Task Name text size
 */

enum class AlertAction {
    ADD, UPDATE
}