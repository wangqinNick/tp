# Developer Guide for raVI
{:toc}
## Setting up

### Software Prerequisites

1. **JDK** 11
2. **IntelliJ** IDEA

### Setting up the work environment

1. **Fork** this repo onto your GitHub account. **Clone** it onto your computer.
2. Open IntelliJ. Close any existing projects if you are not at the welcome page.
3. Ensure it is set to the correct JDK version.
    a. `Configure` > `Project Structure for New Projects` > Select Java 11 under Project SDK.
4. Click `Open or Import` to open the cloned repo.
5. If necessary, locate the `build.gradle` file and select it. Click OK.

### Verifying the setup

1. Run the program by selecting the `run` Gradle task at the top right and running it.
2. Test the program by trying to run commands.
3. Additionally, select the `test` Gradle task and run it. Check that all the tests pass.

### Configure coding style

raVI's code is strictly styled using the Gradle Checkstyle plugin.
It is advised to change [IntelliJ's built-in code style
options](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to match the requirements.

The checkstyle configurations is in `<ROOT>/config/checkstyle/` by default. Here is some information on [how to use the
Checkstyle plugin with IntelliJ IDEA](https://se-education.org/guides/tutorials/checkstyle.html).

## Design & implementation

This section describes some noteworthy details on how certain features are implemented.

### Top level classes

This is a class diagram of the top level of raVi.
The classes depicted here are those which are direct dependencies of the main class Duke.
The various dependencies of the classes depicted here are not shown to avoid cluttering, and are described in later sections.

The main class is called Duke (carried over from legacy codebase). The main loop is held within the main class.
Most classes used by the main class are static in nature and do not need to be instantiated.

The Command and CommandResult objects are dependencies of Executor in addition to Duke. Executor can be
viewed as a simple layer of abstraction on top of Command and CommandResult to facilitate the execution of user
commands. Command is a dependency of Parser as Parser creates Command objects to return to the main loop.

![UML class diagram for Main Class](/docs/diagrams/MainClassDiagram.png?raw=true)

### Command Family

The Command family of classes are nearly all derived from the abstract Command class, except for
CommandResult and PromptType. All Command classes belong to the command package.

The Command classes carry information about the user's command. There is one class for each exact user command.
The `execute()` function of the Command class generates a CommandResult, which holds the reply to the user.

PromptType indicates the functionality of the Command object. The most useful type is EDIT, which indicates to
StateManager that there has been a change in state.

![UML class diagram for Command Family Classes](/docs/diagrams/CommandClassDiagram.png?raw=true)

### Data Family

The Data family of classes consists of all the abstracted data types required for our features, such as
Tasks, Modules, and their respective Managers. All Data classes exist in the data package, and the classes
in charge of saving and loading like InputOutputManager are in the storage subpackage.

Lesson, Task, and Module are the base level abstractions, with their respective Managers containing the logic to store
and manipulate instances of these objects in a meaningful way. InputOutputManager reads and writes information from the
various Managers in order to save and load. State and StateManager are specifically for the undo and redo functionality.
They do not interact directly with the rest of the Data family.

LessonFilter is the only interface in the data package. It allows for flexible creation of filters for powerful user
filtering of lessons via lambda functions. For example, the user can choose to filter only lectures on Mondays before 2PM.

Since there is no command to save or load, InputOutputManager is not a dependency of Command. All the other Managers,
however, are dependencies of Command as there are commands for using/manipulating each one of them. InputOutputManager
and Command are then dependencies of the main class Duke.

![UML class diagram for Data Family Classes](/docs/diagrams/DataClassDiagram.png?raw=true)

### Parser Family

The Parser family of classes consists of the main Parser class and the xCommandParser subclasses. The main Parser class
first determines the main command in the user command string. If it is one of the 5 commands with a xCommandParser
subclass, then Parser delegates the remaining work to the subclass due to the complicated logic involved. Otherwise, it
handles the logic itself.

It will create a Command object, no matter whether the user command is valid or not (if it is not, then an
IncorrectCommand object is created). This Command object is then passed back to the main class Duke for execution.

![UML class diagram for Parser Family Classes](/docs/diagrams/ParserClassDiagram.png?raw=true)

## Product scope
### Target user profile

The target user profile for raVI is described by the following:
* A student of NUS (a freshman in particular)
* Has a need to manage their school related tasks, classes and notes
* Prefers desktop apps over other types
* Types fast
* Prefers typing to mouse interactions
* Reasonably comfortable using CLI apps

### Value proposition

A common problem amongst freshmen is the inability to organise all the incoming information.
NUS places a focus on taking responsibility for your own learning, so it might be a tough transition from tertiary education.
A lot of students miss lessons, assignments, and even exams, just because they're struggling to adapt to the new
environment.

raVI helps students to manage their school-related information in a compact, stripped-down interface that does not bombard them with too much information.
When you receive your modules and lessons, simply enter them into raVI as they arrive. raVI will keep track of all of it
for you.
You can create tasks, give them deadlines, and tag them to certain modules. You can see all of your tasks and deadlines at a glance.
You can even write and save your notes in raVI, uncluttering your work environment even further.

raVI is even integrated with NUSMods, bringing its comprehensive library of information to your fingertips.
All of the above features are wrapped in a compact, no-frills command-line interface. No confusing menus and dropdowns
to distract you; only simple commands to give you what you want.

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Add/Delete Feature
This feature is facilitated by the TaskManager, ModuleManager classes.
Extending from the abstract Command class are the AddModule, AddTask Command classes. This feature implements the following operations:
* AddTask - Add a task to the task list through `TaskManager.add()`
* AddModule - Add a module to the module list through `ModuleManager.add()`
* DeleteTask - Deletes a task from the task list through `TaskManager.delete()`
* DeleteModule - Deletes a module from the module list through `ModuleManager.delete()`

![Sequence diagram for AddCommand Family Classes](/docs/diagrams/AddCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of an add command.\
AddCommand is an abstract class, inheriting from it are AddTaskCommand and AddModuleComand.\
The AddCommandParser decides to create either AddModuleCommand, AddTaskCommand or IncorrectCommand objects based on the user input.\
Each of these have an execute() function that creates a CommandResult object that shows the user the result of the command through TextUi, using `showOutputToUser()`\

Given below is an example usage scenario and how the add feature behaves at each step.

Step 1. The user launches the application for the first time.

Step 2. The user inputs `add -m CS2101` into Ravi, as the user wants to note down a module named ‘CS2101’ and add it to their module list.\
This input is received by the Ui as a string. This string is parsed by the Parser, and thereafter the AddCommandParser, before creating an AddModuleCommand. 

Step 3. The AddModuleCommand is executed, returning a `CommandResult` containing a success message if the module has been successfully added.\
Otherwise, an exception message will be shown explaining the exception to the user.\
Common reasons for failure include:

* Wrong command format\
e.g. `add --t task`\
e.g. `add -t task --by 2-10-2020 1400`\
e.g. `add -m Fake Mod`\
e.g. `add -t task -by 2nd Jan`
* Module already exists in module list\
e.g. `add -m CS1010` but the module list already contains `CS1010`

### Cap Feature 
This feature is faciliatated by ModuleManager and Module classes.
It extends `Command` and runs through the `ModuleManager`, checking every `grade` and `moduleCredit`.
* `CapCommand#gradeConvert` - Takes the grade of a module and assigns it a value according to the NUS grading schematic
* `CapCommand#calculateCap` - Uses a formula to calculate the user's current cap, with the user's total Module Credit taken and current cap.

![Sequence diagram for Cap Feature in Command class](/docs/diagrams/CapCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of a Cap command.
The CapCommandParser parses the user's input and assigns the relevant attributes in the Cap Command constructor, such as `totalMcTaken` and `currentCap`.
When Cap Command is being executed , a CommandResult object is created that calculates the user's current cap after taking into account the current `ModuleManager` modules and the past semester's total mc taken and current cap.

Given below is an example usage scenario and how the cap feature behaves at each step. 

Step 1. The user launches the application for the first time. 

Step 2. The user inputs `add -m CS2101` into Ravi, as the user adds a module he's taking into the `ModuleManager`. The user keys in as many modules into Ravi as they are taking. 

Step 3. Once the user attains a grade for the modules keyed in, he inputs `grade CS2101 4 A-` and grades all the other modules he has taken.

Step 4. Once every module in the `ModuleManager` has been graded, he inputs `cap 46 4.24` to calculate his accumulative cap after attaining his new grades.

Step 5. The `CommandResult` returns the success message to show the user his current cap after attaining his grades.

### Grade Feature 
This feature is facilitated by ModuleManager and Module classes. 
It extends `Command` and is stored internally inside `Module` as an `grade` and `moduleCredit`.
* `GradeCommand#testgrade(stringGrade)` - checks if the input grade is valid according to NUS grading schematic 
* `GradeCommand#grade(moduleModule)` - assigns the specific module present in the module list, the grade and moduleCredit attributes.

![Sequence diagram for Grade Feature in Command class](/docs/diagrams/GradeCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of a Grade command.
The GradeCommandParser parses the user's input, and assigns the relevant attributes in the Grade Command constructor, such as `moduleCredit` and `grade`
When Grade Command is being executed,a CommandResult object is created that shows the user the result of the command through TextUi, using `showOutputToUser()`

Given below is an example usage scenario and how the grade feature behaves at each step.

Step 1. The user launches the application. The user inputs `add -m CS2101` into ra.VI, as the user wants to note down a module named ‘CS2101’ and add it to their module list. This input is received by the Ui ,which processes it into a string. The string is parsed by the parser and allocates it to the AddCommand where it is added to the list of modules. 

Step 2. The user inputs `grade CS2101 4 A+`. Where the user input is parsed and allocated to by the parser to GradeCommand. `GradeCommand#execute()` is called and moduleManager checks if such a module exists in the user’s module list, then checks if the input grade is valid according to the NUS grading schematic and finally assigns the specific module , the grade and module credits.

Step 3. The `CommandResult` returns the success message to show the user that their module has successfully been graded. Otherwise, an exception message will be shown regarding the exception caught.

### Timetable Feature 
This feature is faciliatated by TimeTableManager and TimeTableCommand classes.
Extending from the abstract TimeTableCommand class are the TimeTableAddCommand, TimeTableDeleteCommand and TimeTableViewCommand classes. This feature implements the following operations:
* AddLesson - Add a Lesson to the timetable through `TimeTableManager.addLesson()`
* DeleteLesson - Delete all associated Lessons from the timetable through `TaskManager.deleteLesson()`
* ViewTimeTable - List all Lessons in the timetable through `TaskManager.getSpecificDayLessons()` or `TaskManager.getSpecifiedWeekLessons()`

![Class diagram for TimeTable Family Classes](/docs/diagrams/TimeTableClassDiagram.png?raw=true)

As seen from the class diagram above, these are the classes that are required for this feature.\
Upon the first start up of ra.VI, `TimeTableManager.initialise()` will be run. This will no longer run again as long as the user does not tamper with / delete the files in the created data folder.\
The TimeTable is created based on the user's initial input, with an appropriate number of LessonManagers.\
The point of entry for this feature will be at TimeTableCommandParser, which will decide which of the commands to return through `parseTimeTableAddCommand()`. 
Thereafter, if the TimeTableCommand is returned, the TimeTableManager will carry out the associated commands, adding, deleting or viewing the Lessons in the timetable.

#### Add lesson/s to timetable
Given below is an example scenario to add a lesson to the timetable and how the timetable feature behaves at each step.

Step 1. The user launches the application for the first time. ra.VI asks for the current NUS week. This input is parsed and initialises the TimeTableManager. 

Step 2. The user inputs `add -m CS2101`, as the user wants to note down a module named `CS2101` and add it to their module list.

Step 3. The user inputs `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`. This means the user wants to add a `CS2101 LECTURE` that occurs once a week on `TUESDAY 0800 1000`. This command will be parsed and eventually returns a TimeTableAddCommand.

Step 4. The TimeTableAddCommand is executed, returning a `CommandResult` containing a success message if the Lessons have been successfully added.\
Otherwise, an exception message will be shown explaining the exception to the user.\
Common reasons for failure include:

* Wrong command format\
e.g. `timetable -add CS2101 TUE 0800 1000 LECTURE 1`\
e.g. `timetable -add CS2101 TUESDAY 8am 10am LECTURE 1`\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 NONSENSE 1`\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 5`\
* Module does not exist in module list\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1` but the module list does not contain `CS2101`. Available modules can be found by entering `list -m`
e.g. `timetable -add BAD TUESDAY 0800 1000 LECTURE 1` but the module list does not contain `BAD` and `BAD` is not a valid NUS module.

#### Delete lesson/s from timetable
Given below is an example scenario to delete a lesson from the timetable and how the timetable feature behaves at each step.

Step 1. The user launches the application for the first time. ra.VI asks for the current NUS week. This input is parsed and initialises the TimeTableManager. 

Step 2. The user inputs `add -m CS2101`, as the user wants to note down a module named `CS2101` and add it to their module list.

Step 3. The user inputs `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`. This means the user wants to add a `CS2101 LECTURE` that occurs once a week on `TUESDAY 0800 1000`. This command will be parsed and eventually returns a TimeTableAddCommand.

Step 4. The user inputs `timetable -del TUESDAY 1`. This means the user wants to delete the `CS2101 TUESDAY 0800 1000 LECTURE` lessons. The `1` at the end reflects lessons on index `1` on `TUESDAY`. This command will be parsed and eventually returns a TimeTableDeleteCommand.

Step 4. The TimeTableDeleteCommand is executed, returning a `CommandResult` containing a success message if the Lessons have been successfully deleted.\
Otherwise, an exception message will be shown explaining the exception to the user.\
Common reasons for failure include:

* Wrong command format\
e.g. `timetable -del TUE 1`\
* Lesson does not exist in the timetable\
e.g. `timetable -del TUESDAY 5` but the timetable does not contain a lesson/s on `TUESDAY` at index `5`. Current lessons can be found by entering `timetable -day` or `timetable -week`

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|user|save and load my information|store all my school related data in the application|
|v1.0|user|add tasks to the application|keep track of all my tasks|
|v1.0|user|add deadlines for tasks to the application|keep track of when I need to complete the tasks|
|v1.0|user|add modules to the application|keep track of them during the school term|
|v1.0|user|delete tasks from the application|keep track of new tasks|
|v1.0|user|delete modules from the application|keep track of new modules|
|v2.0|user|view my timetable quickly|be aware of my classes and prepare for them quickly|
|v2.0|user|view a summary of my tasks|be aware of my tasks and work on them as needed|
|v2.0|user|grade my modules|keep track of my grades for respective modules|
|v2.0|user|calculate my accumulative cap|keep track of my progress in university|

## Non-Functional Requirements

{Give non-functional requirements}
* Should work on any mainstream OS as long as it has Java 11 or above installed.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *glossary item* - Definition
* Mainstream OS: Windows, Linux, Unix, OS-X

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
Given below are instructions to test the app manually.

### Launch and shutdown

### Adding a task w/ deadline
1. Adding a task without deadline
    1. Test case: `add -t read a book`
    Expected: Task `read a book` will be added to the task list. Details of the success of the added task will be shown.
    2. Test case: `add -t `
    Expected: As there is no task to add, details of the associated error message will be shown.
2. Adding a task with deadline
    1. Test case: `add -t read a book -by 20-10-2020 1800`
    Expected: Task `read a book` will be added to the task list. Additionally, the deadline 
    will be added to the task.
    Details of the added task is shown in the status message.
    2. Test case: `add -t read a book -by 20/20/2020`
    Expected: Invalid deadline `20/20/2020` will not allow the task to be added to the task list. Details of the associated error message will be shown.

### Adding a module
1. Adding a module
    1. Test case: `add -m CS2113T`
    Expected: Module `CS2113T` will be checked against the NUS module list. 
    Since `CS2113T` is a valid module, it will be added to the module list. Details of the success of the added module will be shown.
    2. Test case: `add -m Fake Mod`
    Expected: As there is no such module `Fake Mod` in the NUS module list, it will not be added to the module list. Details of the associated error message will be shown.

### Deleting a task
1. Deleting a task
    1. Test case: `del -t 1` where `task` is index `0` in the task list
    Expected: The DeleteCommandParser parses `1` and converts it to index `0` in the task list. As task `task` is the index `0` in the task list, `task` will be deleted from the task list.
    2. Test case: `del -t 10` where there is no task of index `9` in the task list
    Expected: The DeleteCommandParser parses `10` and converts it to index `9` in the task list. As there is no task of index `9` in the task list, the deletion will give an error. Details of the associated error message will be shown.

### Deleting a module
1. Deleting a module
    1. Test case: `del -m CS2113T` where `CS2113T` has been previously added to the module list
    Expected: As module `CS2113T` is in the module list, `CS2113T` will be deleted from the module list. Details of the success of the deleted module will be shown.
    2. Test case: `del -m 0` where there is no module `0` in the module list
    Expected: As there is no such module in the module list, the deletion will give an error. Details of the associated error message will be shown.
    
### Adding a lesson to the timetable

### Marking a task as done / undone

### Editing a task

### Editing a module

### Viewing the timetable

### Calculating the Accumulated Cap after the current semester
1. Calculate the Cap:
    1. Test case: `cap 46 4.24`, where the modules in Module List has already been graded individually.
    Excepted: Each module in `ModuleManager` will be checked for its `moduleCredit` and `grade`, it is accumulated in variables.
    Using the accumulative cap formula and the user's most updated cap will be calculated and shown to user.

### Grading an existing module in module list
1. Grade the module:
    1. Test case: `grade CS2101 4 A+` , where the module `CS2101` has been previously added to the module list
    Expected: Module `CS2101` will be checked if its inside ModuleManager.
    Since `CS2101` exists then the attributes of `4` and `A+` which are module credit and grade,
    will be added to the Module.  

### Undo the previous command

### Redo the previous command

### Saving data
1. Add tasks and modules, then exit
    1. Test case: `add -t task 1`, `add -m CS1010`, `bye`
    Expected: `user_task_data.json` and `user_mod_data.json` should be created in `<ROOT>/data/` with a JSON
    representation of the task and module.
2. Loading tasks and modules
    1. Test case: Run raVI again after the first test case, then run `list -t` and `list -m`
    Expected: `task 1` should be shown in the task list, and `CS1010` should be shown in the module list.
