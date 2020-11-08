# Developer Guide for ra.VI

## Table of Contents

## Setting up
The following section describes how to set up ra.VI on your own computer.

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

ra.VI's code uses the Gradle Checkstyle plugin.
It is advised to change [IntelliJ's built-in code style
options](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to match the requirements.

The checkstyle configurations is in `<ROOT>/config/checkstyle/` by default. Here is some information on [how to use the
Checkstyle plugin with IntelliJ IDEA](https://se-education.org/guides/tutorials/checkstyle.html).

## Product scope
### Target user profile

The target user profile for ra.VI is described by the following:
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

ra.VI helps students to manage their school-related information in a compact, stripped-down interface that does not bombard them with too much information.  
When you receive your modules and lessons, simply enter them into ra.VI as they arrive. ra.VI will keep track of all of it
for you.  
You can create tasks, give them deadlines, and tag them to certain modules. You can see all of your tasks and deadlines at a glance.  
You can even write and save your notes in ra.VI, uncluttering your work environment even further.

ra.VI is even integrated with NUSMods, bringing its comprehensive library of information to your fingertips.  
All the above features are wrapped in a compact, no-frills command-line interface. No confusing menus and dropdowns
to distract you; only simple commands to give you what you want.

## Implementation
This section describes some noteworthy details on how certain features are implemented.

## Top level classes

This is a class diagram of the top-level of ra.Vi.  
The classes depicted here are those which are direct dependencies of the main class Ravi.  
The various dependencies of the classes depicted here are not shown to avoid cluttering, and are described in later sections.  

The main class holds the main loop. 
Most classes used by the main class are static in nature and do not need to be instantiated. 

The Command and CommandResult objects are dependencies of Executor in addition to Ravi. Executor can be
viewed as a simple layer of abstraction on top of Command and CommandResult to facilitate the execution of user
commands. Command is a dependency of Parser as Parser creates Command objects to return to the main loop.

![UML class diagram for Main Class](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/MainClassDiagram.png?raw=true)

### Command Family

The Command family of classes are nearly all derived from the abstract Command class, except for
CommandResult and PromptType. All Command classes belong to the command package. This is shown in the diagram below.

![UML class diagram for Command Family Classes](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/CommandClassDiagram.png?raw=true)

PromptType indicates the functionality of the Command object. The most useful type is EDIT, which indicates to
StateManager that there has been a change in state.

The Command classes carry information about the user's command. There is one class for each exact user command. The
`execute()` function of the Command class generates a CommandResult, which holds the reply to the user. This is shown in the diagram below.

![UML sequence diagram for Command Classes](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/CommandSequenceDiagram.png?raw=true)

### Data Family

The Data family of classes consists of all the abstracted data types required for our features, such as
Tasks, Modules, and their respective Managers. All Data classes exist in the data package, and the classes
in charge of saving and loading like InputOutputManager are in the storage subpackage.

Lesson, Task, and Module are the base level abstractions, with their respective Managers containing the logic to store
and manipulate instances of these objects in a meaningful way. InputOutputManager reads and writes information from the
various Managers in order to save and load. State and StateManager are specifically for undo and redo functionality.
They do not interact directly with the rest of the Data family.

LessonFilter is the only interface in the data package. It allows for flexible creation of filters for powerful user
filtering of lessons via lambda functions. For example, the user can choose to filter only lectures on Mondays before 2PM.

Since there is no command to save or load, InputOutputManager is not a dependency of Command. All the other Managers,
however, are dependencies of Command as there are commands for using/manipulating each one of them. InputOutputManager
and Command are then dependencies of the main class Ravi.

![UML class diagram for Data Family Classes](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/DataClassDiagram.png?raw=true)

### Parser Family

The Parser family of classes consists of the main Parser class and the xCommandParser subclasses. The main Parser class
first determines the main command in the user command string. If it is one of the 5 commands with a xCommandParser
subclass, then Parser delegates the remaining work to the subclass due to the complicated logic involved. Otherwise, it
handles the logic itself.

It will create a Command object, no matter whether the user command is valid or not (if it is not, then an
IncorrectCommand object is created). This Command object passes back to the main class Ravi for execution.

![UML class diagram for Parser Family Classes](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/ParserClassDiagram.png?raw=true)


### Timetable Family 

The Timetable Family of classes is a _cross-family_ family of classes from the Data and Command families, 
and consists of the timetable Command and CommandParser classes, as well as TimeTableManager and TimeTable themselves. 
Extending from the abstract TimeTableCommand class are the TimeTableAddCommand, TimeTableDeleteCommand, TimeTableViewCommand, and TimeTableResetCommand classes.

![Class diagram for TimeTable Family Classes](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/TimeTableClassDiagram.png?raw=true)

**This is a good representation of how the other data classes (Task, Module) work as well.** The Command objects
call the methods held in the Manager classes to perform work on the stored user data.

Upon the first start up of ra.VI, `TimeTableManager.initialise()` will be run. This will no longer run again as long as the user 
does not tamper with or delete the files in the created data folder.

The TimeTable is created based on the user's initial input, with an appropriate number of LessonManagers.
The point of entry for this feature will be at TimeTableCommandParser, which will decide which of the commands 
to return through `parseTimeTableCommand()`. If the TimeTableCommand is returned and executed, the 
TimeTableManager will carry out the associated commands, adding, deleting or viewing the Lessons in the timetable.

## Feature explanation with sequence diagrams

### Main loop sequence
When ra.VI runs, there are 3 phases to its lifecycle.

1. Initialisation
2. Main command loop
3. Saving and exiting

The main sequence diagram can be broken into three parts representing each of these phases.

![Sequence diagram 1 for Main loop](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/MainSequenceDiagram1.png?raw=true)
ra.VI's `start()` method initialises all the classes used by the main class, like TextUi, InputOutputManager, and more.
TimeTableManager may need user input to be initialised, and so it is placed in a validation loop.
A welcome message is shown at the end of the initialisation phase.

![Sequence diagram 2 for Main loop](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/MainSequenceDiagram2.png?raw=true)
The main loop is contained in the 'runCommandLoopUntilExitCommand()' method.
The main loop follows the following steps:
1. Get the user command as a string.
2. Parse the command using Parser. Parser will return a Command object.
3. Execute the Command object with the `execute()` method. This will do the necessary work and return a CommandResult object.
4. If any data was changed, StateManager will run `saveState()` to facilitate undo commands.
5. Finally, use the CommandResult object to show the result of the command to the user using TextUi.

Note that the Command and CommandResult objects are destroyed at the end of the method. Additionally, the Parser object
is destroyed after each use.

![Sequence diagram 3 for Main loop](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/MainSequenceDiagram3.png?raw=true)
After exiting the main loop, the main class calls InputOutputManager's saving methods to save all user data.
After that, the program terminates.

### Add/Delete Feature
This feature is facilitated by the TaskManager, ModuleManager classes.
Extending from the abstract Command class are the AddModule, AddTask Command classes. This feature implements the following operations:
* AddTask - Add a task to the task list through `TaskManager.add()`
* AddModule - Add a module to the module list through `ModuleManager.add()`
* DeleteTask - Deletes a task from the task list through `TaskManager.delete()`
* DeleteModule - Deletes a module from the module list through `ModuleManager.delete()`

![Sequence diagram for AddCommand](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/AddCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of an add command.\
AddCommand is an abstract class, inheriting from it are AddTaskCommand and AddModuleComand.\
The AddCommandParser decides to create either AddModuleCommand, AddTaskCommand or IncorrectCommand objects based on the user input.\
Each of these have an execute() function that creates a CommandResult object that shows the user the result of the command through TextUi, using `showOutputToUser()`

Given below is an example usage scenario and how the add feature behaves at each step.

1. The user launches the application for the first time.

2. The user inputs `add -m CS2101` into ra.VI, as the user wants to note down a module named ‘CS2101’ and add it to their module list.\
The Ui receives the input as a string. Parser parses the string, and thereafter the AddCommandParser, before creating an AddModuleCommand. 

3. The AddModuleCommand is executed, returning a `CommandResult` containing a success message if the module has been successfully added.\
Otherwise, an exception message will be shown explaining the exception to the user.\
Common reasons for failure include:

* Wrong command format\
e.g. `add --t task`\
e.g. `add -t task --by 2-10-2020 1400`\
e.g. `add -m Fake Mod`\
e.g. `add -t task -by 2nd Jan`
* Module already exists in module list\
e.g. `add -m CS1010` but the module list already contains `CS1010`

### List Feature
This feature is facilitated by the TaskManager and ModuleManager classes.  
It extends from the abstract `Command` class.  
This feature implements the following operations:
* List tasks - List all tasks in the task list through `TaskManager.list()`
* List modules - List all modules in module map through `ModuleManager.list()`

![Sequence diagram for List Feature in Command class](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/ListCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of `ListCommand`.  
`ListCommandParser` class calls ListCommand(1), ListCommand(0), or InvalidParameterException() methods based on the user input.

`ListCommand` has an execute method. Depending on the type of entry, `ListCommand` calls list() method of `TaskManager` if it is TASK, or list() method of `ModuleManager` if it is MODULE. Both objects return output.

If output is null, `ListCommand` calls CommandResult(MESSAGE_LIST_EMPTY), creating a `CommandResult` object. Else, `ListCommand` calls CommandResult(MESSAGE_LIST_PRINTED + output), creating a `CommandResult` object.

`ListCommand` returns `CommandResult`. 

Given below is an example usage scenario and how the cap feature behaves at each step.  
1. The user launches the application for the first time.  
2. The user inputs `add -t Read book` into ra.VI, adding the task to the task list in TaskManager. The user keys in multiple other tasks of the following:
* `add -t Return book -by 2-10-2020 1400`
* `add -t Meeting`  

3. The user inputs `add -m CS2113T` into ra.VI, adding the module to the module map in ModuleManager. The user keys in multiple other modules of the following:
* `add -m CS2101`
* `add -m CG2271`

4. The user inputs `list -t`. The CommandResult returns  
```
1. Read book [x]
2. Return book [x], by 02:00PM, Friday, 02 Oct 20 
3. Meeting [x]
```
Step 5. The user inputs `list -m`. The CommandResult returns  
```
1. CS2113T: Software Engineering & Object-Oriented Programming: No grade yet
2. CG2271: Real-Time Operating Systems: No grade yet
3. CS2101: Effective Communication for Computing Professionals: No grade yet
```

### CAP Feature 
This feature is faciliatated by ModuleManager and Module classes.
It extends `Command` and runs through the `ModuleManager`, checking every `grade` and `moduleCredit`.
* `CapCommand.gradeConvert` - Takes the grade of a module and assigns it a value according to the NUS grading schematic
* `CapCommand.calculateCap` - Uses a formula to calculate the user's current cap, with the user's total Module Credit taken and current CAP.

![Sequence diagram for Cap Feature in Command class](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/CapCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of a CAP command.
The CapCommandParser parses the user's input and assigns the relevant attributes in the CAP Command constructor, 
such as `totalMcTaken` and `currentCap`.
When Cap Command executes, a CommandResult object is created that calculates the user's current cap after taking into 
account the current `ModuleManager` modules and the past semester's total MC taken and current CAP.

Given below is an example usage scenario and how the CAP feature behaves at each step. 

1. The user launches the application for the first time. 

2. The user inputs `add -m CS2101` into ra.VI, as the user adds a module he's taking into the `ModuleManager`.
The user keys in as many modules into ra.VI as they are taking. 

3. Once the user attains a grade for the modules keyed in, he inputs `grade CS2101 4 A-` and grades all the other
modules he has taken.

4. Once every module in the `ModuleManager` has been graded, he inputs `cap 46 4.24` to calculate his accumulative 
CAP after attaining his new grades.

5. The `CommandResult` returns the success message to show the user his current CAP after attaining his grades.

### Grade Feature 
This feature is facilitated by ModuleManager and Module classes. 
It extends `Command` and is stored internally inside `Module` as an `grade` and `moduleCredit`.
* `GradeCommand.testgrade(stringGrade)` - checks if the input grade is valid according to NUS grading schematic 
* `GradeCommand.grade(moduleModule)` - assigns the specific module present in the module list, the grade and moduleCredit attributes.

![Sequence diagram for Grade Feature in Command class](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/diagrams/GradeCommandSequenceDiagram.png?raw=true)

As seen from the sequence diagram above, this is the flow of a Grade command.
The GradeCommandParser parses the user's input, and assigns the relevant attributes in the Grade Command constructor, such as `moduleCredit` and `grade`
When Grade Command executes,a CommandResult object is created that shows the user the result of the command through TextUi, using `showOutputToUser()`

Given below is an example usage scenario and how the grade feature behaves at each step.

1. The user launches the application. The user inputs `add -m CS2101` into ra.VI, as the user wants to note down 
a module named ‘CS2101’ and add it to their module list. This input is received by the Ui ,which processes it into 
a string. The parser parses the string and allocates it to the AddCommand where it is added to the list of modules. 

2. The user inputs `grade CS2101 4 A+`. The parser parses and allocates the user input to GradeCommand. 
`GradeCommand.execute()` is called and moduleManager checks if such a module exists in the user’s module list, 
then checks if the input grade is valid according to the NUS grading schematic and finally assigns the specific module, 
the grade and module credits.

3. The `CommandResult` returns the success message to show the user that their module has successfully been graded. 
Otherwise, an exception message will be shown regarding the exception caught.

### Timetable Feature
This feature is facilitated by the TimeTableManager class and TimeTableCommand class.
Extending from the abstract TimeTableCommand class are the TimeTableAddCommand, TimeTableDeleteCommand, TimeTableViewCommand and TimeTableResetCommand classes.
* AddLesson - Add a Lesson to the timetable through `TimeTableManager.addLesson()`
* DeleteLesson - Delete all associated Lessons from the timetable through `TimeTableManager.deleteLesson()`
* ViewTimeTable - List all Lessons in the timetable through `TimeTableManager.getSpecificDayLessons()` or `TimeTableManager.getSpecifiedWeekLessons()`
* ResetTimeTable - Reset timetable through `TimeTableManager.initialiseTimetable()` 

#### Add lesson/s to timetable
Given below is an example scenario to add a lesson to the timetable and how the timetable feature behaves at each step.

1. The user launches the application for the first time. ra.VI asks for the current NUS week. This input is parsed and 
initialises the TimeTableManager. 

2. The user inputs `add -m CS2101`, as the user wants to note down a module named `CS2101` and add it to their module list.

3. The user inputs `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`. This means the user wants to add a `CS2101 LECTURE` 
that occurs once a week on `TUESDAY 0800 1000`. This command will be parsed and eventually returns a TimeTableAddCommand.

Step 4. The TimeTableAddCommand is executed, returning a `CommandResult` containing a success message if the Lessons have 
been successfully added. Otherwise, an exception message will be shown explaining the exception to the user.\
Common reasons for failure include:

* Wrong command format\
e.g. `timetable -add CS2101 TUE 0800 1000 LECTURE 1`\
e.g. `timetable -add CS2101 TUESDAY 8am 10am LECTURE 1`\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 NONSENSE 1`\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 5`
* Module does not exist in module list\
e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1` but the module list does not contain `CS2101`. Available modules can be found by entering `list -m`
e.g. `timetable -add BAD TUESDAY 0800 1000 LECTURE 1` but the module list does not contain `BAD` and `BAD` is not a valid NUS module.

#### Delete lesson/s from timetable
Given below is an example scenario to delete a lesson from the timetable and how the timetable feature behaves at each step.

1. The user launches the application for the first time. ra.VI asks for the current NUS week. This input is parsed 
and initialises the TimeTableManager. 

2. The user inputs `add -m CS2101`, as the user wants to note down a module named `CS2101` and add it to their module list.

3. The user inputs `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`. This means the user wants to add a 
`CS2101 LECTURE` that occurs once a week on `TUESDAY 0800 1000`. This command will be parsed and eventually returns a 
TimeTableAddCommand.

4. The user inputs `timetable -del TUESDAY 1`. This means the user wants to delete the `CS2101 TUESDAY 0800 1000 LECTURE` 
lessons. The `1` at the end reflects lessons on index `1` on `TUESDAY` as reflected by `timetable -day` or `timetable -week`. 
This command will be parsed and eventually returns a TimeTableDeleteCommand.

5. The TimeTableDeleteCommand is executed, returning a `CommandResult` containing a success message if the Lessons 
have been successfully deleted. Otherwise, an exception message will be shown explaining the exception to the user.
Common reasons for failure include:

* Wrong command format\
e.g. `timetable -del TUE 1`.
* Lesson does not exist in the timetable\
e.g. `timetable -del TUESDAY 5` but the timetable does not contain a lesson/s on `TUESDAY` at index `5`. Current 
lessons can be found by entering `timetable -day` or `timetable -week`.

#### View the timetable
Given below is an example scenario to view the timetable for the day.

1. The user adds a lesson to the timetable for today, for e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`.

2. The user inputs `timetable -day`.

3. The timetable for the day is shown to the user. The user is able to see the Tuesday CS2101 lecture that was 
previously added in step 1.

#### Filter the timetable
Given below is an example scenario to filter the timetable for CS2101 LECTURE.

1. The user adds a lesson to the timetable for today, for e.g. `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 1`.

2. The user inputs `timetable -filter CS2101 - - - LECTURE`

3. All CS2101 lectures in the timetable are shown to the user. The user is able to see the CS2101 weekly lectures that were previously added in step 1.

* Wrong command format\
e.g. `timetable -filter`

#### Reset the timetable
Given below is an example scenario to reset the timetable. 

1. The user inputs `timetable -reset`.  

2. ra.VI will ask for the current NUS week. This input is parsed and reinitialises the TimeTableManager with a new Timetable. 

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
|v2.0|user|add lessons to my timetable|view my timetable with a simple command|
|v2.0|user|delete lessons from my timetable|view my accurate timetable with a simple command|
|v2.0|user|view my timetable quickly|be aware of my classes and prepare for them quickly|
|v2.0|user|filter the lessons in my timetable |be aware of specific classes and prepare for them quickly|
|v2.0|user|view a summary of my tasks|be aware of my tasks and work on them as needed|
|v2.0|user|grade my modules|keep track of my grades for respective modules|
|v2.0|user|calculate my accumulative cap|keep track of my progress in university|
|v2.0|user|undo unintended commands|make amends quickly|
|v2.1|user|reset my timetable|prepare for another semester|

## Non-Functional Requirements

{Give non-functional requirements}
* Should work on any mainstream OS as long as it has Java 11 or above installed.
* A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Glossary

* *Mainstream OS* - Windows, Linux, OSX

## Instructions for manual testing

Given below are instructions to test the app manually.

1. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v2.0) and copy it into an empty folder.
2. Open a new terminal window and navigate to the same directory where ravi.jar is located. 
3. Enter the command `java -jar ravi.jar` into the terminal window to launch the application. The application should now be running.
4. Enter the command `help` to get a list of all available commands and its usages.
5. For a detailed list on the command features, refer to the [user guide](https://github.com/AY2021S1-CS2113T-T09-2/tp/blob/master/docs/UserGuide.md).
6. Simply enter `bye` to terminate and exit the application.

### Adding a task w/ deadline
1. Adding a task without deadline
    1. Test case: `add -t read a book`\
    Expected: Task `read a book` will be added to the task list. Details of the success of the added task will be shown.
    2. Test case: `add -t `\
    Expected: As there is no task to add, details of the associated error message will be shown.
2. Adding a task with deadline
    1. Test case: `add -t read a book -by 20-10-2020 1800`\
    Expected: Task `read a book` will be added to the task list. Additionally, the deadline 
    will be added to the task. Details of the added task is shown in the status message.
    2. Test case: `add -t read a book -by 20/20/2020`\
    Expected: Invalid deadline `20/20/2020` will not allow the task to be added to the task list. Details of the 
    associated error message will be shown.

### Adding a module
1. Adding a module
    1. Test case: `add -m CS2113T`\
    Expected: Module `CS2113T` will be checked against the NUS module list.  
    Since `CS2113T` is a valid module, it will be added to the module list. Details of the success of the added module will be shown.
    2. Test case: `add -m Fake Mod`\
    Expected: As there is no such module `Fake Mod` in the NUS module list, it will not be added to the module list. 
    Details of the associated error message will be shown.

### Deleting a task
1. Deleting a task
    1. Test case: `del -t 1` where `task` is index `0` in the task list.\
    Expected: The DeleteCommandParser parses `1` and converts it to index `0` in the task list. 
    As task `task` is the index `0` in the task list, `task` will be deleted from the task list.
    2. Test case: `del -t 10` where there is no task of index `9` in the task list.\
    Expected: The DeleteCommandParser parses `10` and converts it to index `9` in the task list. 
    As there is no task of index `9` in the task list, the deletion will give an error. 
    Details of the associated error message will be shown.

### Deleting a module
1. Deleting a module
    1. Test case: `del -m CS2113T` where `CS2113T` has been previously added to the module list.\
    Expected: As module `CS2113T` is in the module list, `CS2113T` will be deleted from the module list. 
    Details of the success of the deleted module will be shown.
    2. Test case: `del -m 0` where there is no module `0` in the module list.\
    Expected: As there is no such module in the module list, the deletion will give an error. 
    Details of the associated error message will be shown.
    
### Amend the timetable
1. Adding a lesson
    1. Test case: `timetable -add CS2113T MONDAY 1200 1400 LECTURE 0` where `CS2113T` is a module in module list.\
    Expected: The TimeTableCommandParser parses the lesson parameters, `CS2113T` is the associated module, 
    `MONDAY 1200 1400 LECTURE` reflects that the lesson is a lecture on Monday from 12pm to 2pm. `0` indicates 
    that this lesson only occurs once. Since `CS2113T` is a valid module in the module list, the lesson will be added 
    to the timetable. Details of the success of the added lesson will be shown.
2. Deleting a lesson
    1. Test case: `timetable -del MONDAY 0` where the only lesson on MONDAY is a CS2113T lecture from 12pm to 2pm.\
    Expected: The TimeTableCommandParser parses the lesson parameters, and searches for a lesson/s of index `0` on 
    `MONDAY`. However, the timetable does not contain such lesson/s. Details of the associated error message will be shown.

### Viewing the timetable
1. Timetable for the day
    1. Test case: `timetable -day`\
    Expected: If there are no lessons for today, this information is shown to the user. Otherwise, the lessons for today 
    are shown to the user.
2. Timetable for the week
    1. Test case: `timetable -week`\
    Expected: The lessons for Monday to Sunday of this week are shown to the user.

### Filtering the timetable
1. Filter all lessons
    1. Test case: `timetable -filter CS2113T - - - -`, which filters CS2113T lessons only.\
    Expected: All CS2113T lessons are shown to the user.
    2. Test case: `timetable -filter CS2113T - - - LECTURE`, which filters CS2113T **lectures** only.\
    Expected: All CS2113T lectures are shown to the user.
    3. Test case: `timetable -filter CS2113T MONDAY 1200 2000 LECTURE`, which filters CS2113T lectures **on Monday 
    between 1200 and 2000** only.\
    Expected: All CS2113T lectures on Monday between 1200 and 2000 are shown to the user.

### Resetting the timetable
1. Reset the timetable
    1. Test case: `timetable -reset`\
    Expected: TimeTableManager clears and reinitialises the timetable. The user is prompted to input the current week for reinitialisation.

### Marking a task as done / undone
1. Marking a task as done
    1. Test case: `done 1`, where `task` is index `0` in the task list.\
    Expected: The DoneCommandParser parses `1` and converts it to index `0` in the task list. 
    As task `task` is the index `0` in the task list, `task` will be marked as done.
    2. Test case: `done 10`, where there is no task of index `9` in the task list.\
    Expected: The DoneCommandParser parses `10` and converts it to index `9` in the task list. 
    As there is no task of index `9` in the task list, an error is thrown. Details of the associated error message will be shown.
    
### Editing a task
1. Editing a task 
    1. Test case: `edit -t 3 read a book`, where task index `3` has already been added previously into the task list.\
    Expected: As the task at index `3` is in the task list, the task description will be edited and changed to `read a book`. 
    Details of the success of the edit task will be shown.
    2. Test case: `edit -t 10 meet girlfriend`, where there is no task `10` in the task list.\
    Expected: As there is no such task in the task list, an error will be given to the user. Details of the associated error 
    message will be shown.

### Editing a module
1. Editing a module 
    1. Test case: `edit -m CG2271 GER1000`, where module `CG2271` has already been added previously into the module list.\
    Expected: As `CG2271` is in the module list, the module will be edited and changed to `GER1000`. Details of the success 
    of the edit module will be shown.
    2. Test case: `edit -m ACC1101 GER1000`, where there is no module `ACC1101` in the module list.\
    Expected: As there is no such module in the module list, an error will be given to the user. Details of the associated 
    error message will be shown.

### Calculating the Accumulated Cap after the current semester
1. Calculate the Cap:
    1. Test case: `cap 46 4.24`, where the modules in Module List has already been graded individually.\
    Excepted: Each module in `ModuleManager` will be checked for its `moduleCredit` and `grade`.
    Using the accumulative CAP formula, the user's most updated CAP will be calculated and shown to user.

### Grading an existing module in module list
1. Grade the module:
    1. Test case: `grade CS2101 4 A+`, where the module `CS2101` has been previously added to the module list.\
    Expected: Module `CS2101` will be checked if its inside ModuleManager.
    Since `CS2101` exists then the attributes of `4` and `A+` which are module credit and grade,
    will be added to the Module.  

### Undo the previous command
1. Undo previous action:
    1. Test case: `undo`, after the user has input in an initial command.\
    Expected: The previous command that was input will be undone. Details of the success of the undone will be shown.
    2. Test case: `undo`, without any initial input by the user.\
    Expected: Due to the fact that there is nothing to undo as there was no user input, details of the associated error message will be shown.

### Saving data
**Do note that if you exit the application without entering `bye`, ra.VI will not be able to retrieve any data that was 
amended during that session.**\
**Tampering with the files created by ra.VI, through any other application, will also cause it to malfunction and is 
strongly discouraged.**
1. Add tasks and modules, then exit
    1. Test case: `add -t task 1`, `add -m CS1010`, `bye`\
    Expected: `user_task_data.json` and `user_mod_data.json` should be created in `<ROOT>/data/` with a JSON
    representation of the task and module.
2. Loading tasks and modules
    1. Test case: Run ra.VI again after the first test case, then run `list -t` and `list -m`.\
    Expected: `task 1` should be shown in the task list, and `CS1010` should be shown in the module list.
