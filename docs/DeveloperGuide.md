# Developer Guide

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
5. If necessary, locate the `build.gradle` file and select it. Click `OK`.

### Verifying the setup

1. Run the program by selecting the `run` Gradle task at the top right and running it.
2. Test the program by trying to run commands.
3. Additionally, select the `test` Gradle task and run it. Check that all the tests pass.

### Configure coding style

RaVi's code is strictly styled using the Gradle Checkstyle plugin.
It is advised to change [IntelliJ's built-in code style
options](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to match the requirements.

The checkstyle configurations is in `<ROOT>/config/checkstyle/` by default. Here is some information on [how to use the
Checkstyle plugin with IntelliJ IDEA](https://se-education.org/guides/tutorials/checkstyle.html).

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}\
This section describes some noteworthy details on how certain features are implemented.

## Product scope
### Target user profile

The target user profile for RaVi is described by the following:
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

RaVi helps students to manage their school-related information in a compact, stripped-down interface that does not bombard them with too much information.
When you receive your modules and lessons, simply enter them into RaVi as they arrive. RaVi will keep track of all of it
for you.
You can create tasks, give them deadlines, and tag them to certain modules. You can see all of your tasks and deadlines at a glance.
You can even write and save your notes in RaVi, uncluttering your work environment even further.

RaVi is even integrated with NUSMods, bringing its comprehensive library of information to your fingertips.
All of the above features are wrapped in a compact, no-frills command-line interface. No confusing menus and dropdowns
to distract you; only simple commands to give you what you want.

## Implementation
This section describes some noteworthy details on how certain features are implemented.

### Add/Delete Feature
This feature is facilitated by the TaskManager, ModuleManager and proposed NoteManager classes.
Extending from the Command classes are the AddModule, AddTask and proposed AddNotes Command classes. It implements the following operations:
* AddTask#addTask() - Add a task to the task list through TaskManager.add()
* AddModule#addModule() - Add a module to the module list through ModuleManager.add()
* DeleteTask#deleteTask() - Deletes a task from the task list through TaskManager.delete()
* DeleteModule#deleteModule() - Deletes a module from the module list through ModuleManager.delete()\

To add on, each Command class has a execute() which will run in the main loop. This returns a CommandResult object which will print a message to the user.

#### Sequence Diagram for the AddModule Class
![alt text](diagrams/Sequence_AddModule.png)

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
|v2.0|user|write notes for my modules|manage my notes together with my tasks and modules|

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
    Expected: Task `read a book` will be added to the task list. Details of the added task is shown in the status message.
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
    Since `CS2113T` is a valid module, it will be added to the module list. Details of the added module is shown in the status message.
    2. Test case: `add -m Fake Mod`
    Expected: As there is no such module `Fake Mod` in the NUS module list.
    It will not be added to the module list. Details of the associated error message will be shown.
    
### Adding a lesson to the timetable

### Marking a task as done / undone

### Editing a task

### Editing a module

### Viewing the timetable

### Undo the previous command

### Redo the previous command

### Saving data
