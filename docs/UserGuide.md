# ra.VI User Guide
## Table of contents
1. **About-Introduction-Product Overview-Quick Start**  
1.1 About  
1.2 Introduction  
1.3 Product Overview  
1.4 Quick Start
2. **Key Features**  
2.1 Key features  
3. **Installation-Getting Started**  
3.1 Installation  
3.2 Getting started  
4. **Features**  
4.1 Viewing help  
4.2 Add an item  
... 4.2.1 Add a task  
... 4.2.2 Add a module  
4.3 Edit an item  
... 4.2.1 Edit a task description  
... 4.2.2 Editing a module  
4.4 Delete an item  
... 4.2.1 Delete a task  
... 4.2.2 Delete a module  
4.4 List items  
... 4.2.1 List tasks  
... 4.2.2 List modules  
4.5 Grade a module  
4.6 Calculate CAP  
4.7 Mark task as done  
4.8 Undo previous command  
4.9 Summary  
4.10 Timetable  
... 4.10.1 View timetable  
... 4.10.2 Add a lesson  
... 4.10.3 Delete a lesson  
4.11 Exit  
  
## Introducing ra.VI

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and notes, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ra.VI can manage your tasks faster than traditional GUI apps.

This user guide provides in-depth documentation on the ra.VI installation process, system configuration and management. In addition, the quick start guide prvoides and end-to-end setup process to get you started.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your scheduler. Double-click the file to start the app. 
4. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
Some example commands you can try:
* `add -t`: Add a task
* `list -t`: List all tasks
* `bye`: Exit the program read chapter 1`
5. Refer to the Features below for details of each command

## Features 

{Give detailed description of each feature}
### Viewing help: `help`
Shows a message with the list of available commands and functions.

### Adding an item: `add <opt> <args>`
#### Adding a task: `add -t [-by]`
Add a task to the scheduler.  
Format: `add -t <task_name> [-by <deadline>]`

Example of usage: 
* `add -t read chapter 1`
* `add -t read chapter 1 -by 30-12-2020 1800`

#### Adding a module: `add -m`
Add a module to the scheduler.  
Format: `add -m <module_code>`

Example of usage: 
* `add -m CS1231`

### Editing an item: `edit <opt> <args>`
#### Editing a task description: `edit -t <task_index> <task_name>`
Edit a task description in the scheduler.  
Format: `edit -t <task_index> <task_name>`

Example of usage: 
* `edit -t 3 revise for CS2113`

Note: You can find the task indexes with `list -t`  

#### Editing a module: `edit -m <module_code> <new_module_code>`
Edit a module code in the scheduler.  
Format: `edit -m <module_code> <new_module_code>`

Example of usage: 
* `edit -m CS2113 CS2113T`

### Deleting an item: `del <opt> <args>`
#### Deleting a task: `del -t <task_index>`
Remove a task from the scheduler.  
Format: `del -t <task_index>`

Example of usage: 
* `del -t 1`

#### Deleting a module: `del -m <module_code>`
Remove a module from the scheduler.  
Format: `del -m <module_code>`

Example of usage: 
* `del -m CS1010`

Note: You can find the task indexes with `list -t`  

### Listing all items: `list <opt> <args>`
#### Listing all tasks: `list -t`
List all tasks in the scheduler.   
Format: `list -t`

#### Listing all modules: `list -m`
List all modules in the scheduler.  
Format: `list -m`

#### Grade a existing module: `grade <module code> <grade>`
Assign a grade to a module in the Scheduler.
Format: `grade <moduleCode> <grade>`

Example of usage:
* `grade CS2101 B+`

#### Calculate your cap after the semester: `cap <total module credit taken> <current cap>`
Calculate your new updated cap, accumulated from past semesters.
Format: `cap <total module credit taken> <current cap>`

Example of usage:
* `cap 46 4.24`

### Mark as done: `done <task_index>`
Mark a task in the scheduler as done.  
Format: `done <task_index>`

Example of usage: 
* `done 1`

### Undo a command: `undo`
Takes the previous command and revert its changes.\
Requires the previous command to be undone, a command that affects the storage. e.g. add, del, edit, done
Format: `undo`

Example of usage: 
1. `add -m CS2113T`
2. `undo`

### Summary: `summary`
Gets an overall view of the tasks.
Format: `summary`

Example of usage: 
* `summary`

### TimeTable: `timetable <opt> <args>`
#### View the timetable: `timetable <args>`
View the timetable for the day or for the week.\
`<args>` : Must be one of the following : `-day` or `-week`

Example of usage: 
* `timetable -day`
* `timetable -week`

#### Add a lesson: `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>`
Adds a lesson to the timetable.\
`<module>` : Must be added to the module list. See module list with `list -m`.\
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
`<start time>` : Must be in 24h format
`<end time>` : Must be in 24h format and is after `<start time>`
`<lesson type>` : Must be one of the following : `TUTORIAL`, `LECTURE`, `SEMINAR`, `LAB`, `RECITATION`, `SESSION`
`<repeat>` : 0 : One time lesson ; 1 : Once a week ; 2 : Every even week ; 3 : Every odd week

Example of usage: 
* `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 0`
* `timetable -add CS2113T MONDAY 1300 1500 LAB 1`

#### Delete a lesson: `timetable -del <day> <lesson index>`
Deletes a lesson from the timetable.\
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
`<lesson index>` : Index of the lesson to be deleted. See timetable and the indexes with `timetable -day` or `timetable -week`

Example of usage: 
* `timetable -del CS2101 1`

### Exiting the program: `bye`
Exits ra.VI.  
Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add task `add -t TASK_NAME DEADLINE`
* Add module `add -m MODULE_NAME`

