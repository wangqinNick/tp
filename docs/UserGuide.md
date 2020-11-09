# ra.VI User Guide

Welcome to the ra.VI User Guide! Choose a section or sub-section from the table of contents below, get step-by-step instructions, and plan your day.

## Table of contents
[1. **Introduction**](#1-introduction) <br>
&nbsp;&nbsp;[1.1 About](#11-about) <br>
&nbsp;&nbsp;[1.2 Product Overview](#12-product-overview) <br>
&nbsp;&nbsp;[1.3 Keywords](#13-keywords)<br>
[2. **Quick Start**](#2-quick-start)<br>
[3. **Features**](#3-features) <br>
&nbsp;&nbsp;[3.1 Viewing help](#31-viewing-help-help) <br>
&nbsp;&nbsp;[3.1.1 Viewing help for a specific command](#311-viewing-help-for-a-specific-command-help-command_word) <br>
&nbsp;&nbsp;[3.2 Add an item](#32-adding-an-item-add-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.2.1 Add a task](#321-adding-a-task-add--t--by) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.2.2 Add a module](#322-adding-a-module-add--m) <br>
&nbsp;&nbsp;[3.3 Edit an item](#33-editing-an-item-edit-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.3.1 Edit a task description](#331-editing-a-task-description-edit--t)<br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.3.2 Editing a module](#332-editing-a-module-edit--m) <br>
&nbsp;&nbsp;[3.4 Delete an item](#34-deleting-an-item-del-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.4.1 Delete a task](#341-deleting-a-task-del--t) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.4.2 Delete a module](#342-deleting-a-module-del--m) <br>
&nbsp;&nbsp;[3.5 List items](#35-listing-all-items-list-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.5.1 List tasks](#351-listing-all-tasks-list--t) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.5.2 List modules](#352-listing-all-modules-list--m)  <br>
&nbsp;&nbsp;[3.6 Grade a module](#36-grade-a-existing-module-grade) <br>
&nbsp;&nbsp;[3.7 Calculate CAP](#37-calculate-your-cap-cap) <br>
&nbsp;&nbsp;[3.8 Mark task as done](#38-mark-as-done-done-task_index) <br>
&nbsp;&nbsp;[3.9 Undo previous command](#39-undo-a-command-undo) <br>
&nbsp;&nbsp;[3.10 Summary](#310-summary-summary) <br>
&nbsp;&nbsp;[3.11 Timetable](#311-timetable-timetable-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.1 View timetable](#3111-view-the-timetable-timetable-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.2 Add a lesson](#3112-add-a-lesson-timetable--add) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.3 Delete a lesson](#3113-delete-a-lesson-timetable--del) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.4 Reset timetable](#3114-reset-the-timetable-timetable--reset) <br>
&nbsp;&nbsp;[3.12 Exit](#312-exiting-the-program-bye) <br>
[4. **FAQ**](#4-faq) <br>
[5. **Command Summary**](#5-command-summary) <br>
[6. **v3.0 Graphical User Interface**](#6-graphical_user_interface) <br>

### 1 Introduction

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and timetable, optimized for use via a Command Line Interface (CLI). If you can type fast, ra.VI can manage your tasks and timetable faster than traditional GUI apps.

### 1.1 About

This user guide provides a clear documentation of ra.VI's features. In addition, the quick start guide provides an end-to-end setup process to begin tracking your tasks and timetable with ra.VI.

### 1.2 Product Overview

ra.VI is targeted at the NUS freshman. As a freshman, there are many documents and new procedures that you must get familiar with. This may be daunting for you but ra.VI will provide you with the assistance you need. 
By helping you keep track of your tasks and deadlines, you will be able to keep on top of deadlines. 
Moreover, the timetable feature helps you to schedule your lessons, allowing you to be more prepared for lessons.
Adding on, ra.VI also allows you to keep track of your CAP, so that you can keep tabs on how well you are doing, motivating you towards that elusive CAP 5.0.

### 1.3 Keywords

**Tasks**

A task refers to something that you would like to get done. This refers to reading a book or doing an assignment. Tasks can be created with or without a deadline. <br>

**Deadline**

A deadline refers to a date and time associated with a task. This is in the format: DAY-MONTH-YEAR TIME_24H, e.g. `23-12-2020 1400` or `1-2-2000 0800` <br>

**Modules**

A module refers to a module taken under NUS. Entered modules will be checked against the list of NUS modules. <br>

**CAP**

CAP refers to Cumulative Average Point, which is the grading system used by NUS. CAP will be calculated based on your modules. <br>

**Done**

A task can be marked as done. This will signify completion of the task so that you can get an easy view of any remaining tasks. <br>

**Timetable**

The timetable is specific to you. It allows you to add lessons to your timetable with respect to the modules that you are taking. After setting the timetable up the first time, you do not need to go through the set up again. <br>

**Day**

Values for `<day>`:\
`MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`

**Lesson Type**

Values for `<lesson type>`:\
`TUTORIAL`, `LECTURE`, `SEMINAR`, `LAB`, `RECITATION`, `SESSION`

**Time**

Format of `<time>`:\
Must be in the 24h format e.g. `0900`, `1415`

### 2 Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v2.1).
3. Copy the file to the folder you want to use as the home folder for `ra.VI`.
4. Open a command prompt in the folder from step 3 and enter `java -jar ravi.jar`.
5. Enter the current NUS week number as prompted.
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will show the help message.  
Some example commands you can try:
* `add -t`: Add a task
* `list -t`: List all tasks
* `bye`: Exit the program
6. Refer to the Features below for details of each command, or refer to the command summary for a quick look at all possible commands.

## 3 Features 

### 3.1 Viewing help: `help`

Shows a message with the list of available commands and functions.

#### 3.1.1 Viewing help for a specific command: `help <command_word>`

Example of usage: 
* `help`
* `help timetable`
* `help add`

Get detailed help message for each command

Example of output:
```
Command: 
⋗	help
════════════════════════════════════════════════════════════════════════════════
Hello! I'm ra.VI, your personal NUS assistant.
I'm here to help you manage your tasks, modules, and lessons.
This help message appears when you type 'help', or an unrecognised command.
To find out more about any of my commands, type 'help <command>'.
Here's a list of my commands to help you out:

Action commands:
	» add       - Add a task or module
	» del       - Delete a task or module
	» edit      - Edit a task or module
	» done      - Mark a task as complete
	» grade     - Grades and allocates MCs to a Module
	» cap       - Calculates your CAP
	» undo      - Undo the previous action (if you made changes)
	» timetable - Manage your timetable
Viewing commands:
	» list      - Lists all tasks or modules
	» summary   - See a neat summary of your tasks
	» timetable - View your timetable, by day or by week
Utility commands:
	» help      - Get detailed help for each command
	» bye       - Exit ra.VI (saves all changes!)
════════════════════════════════════════════════════════════════════════════════
```

```
Command: 
⋗	help add
════════════════════════════════════════════════════════════════════════════════
Add a task to the scheduler.
	Format: add -t <task_name> [-by <deadline>]
	Example usage: add -t Read Book
	               add -t Return Book -by 30-12-2020 1800

Add a module from NUSMods to the scheduler.
	Format: add -m <module_code>
	Example usage: add -m CS2113T
════════════════════════════════════════════════════════════════════════════════
```

### 3.2 Adding an item: `add <opt> <args>`

#### 3.2.1 Adding a task: `add -t [-by]`

Add a task to the scheduler.  
Format: `add -t <task_name> [-by <deadline>]`

Example of usage: 
* `add -t read chapter 1`
* `add -t read chapter 1 -by 30-12-2020 1800`

Example of output:
```
Command: 
⋗	add -t read a book
════════════════════════════════════════════════════════════════════════════════
Your task has been added successfully.
Your new task:
'read a book [x]'

════════════════════════════════════════════════════════════════════════════════
```

```
Command: 
⋗	add -t read a book -by 02-02-2020 1900
════════════════════════════════════════════════════════════════════════════════
Your task has been added successfully.
Your new task:
'read a book [x], by 07:00PM, Sunday, 02 Feb 2020'

════════════════════════════════════════════════════════════════════════════════
```

#### 3.2.2 Adding a module: `add -m`

Add a module to the scheduler.  
Format: `add -m <module_code>`

Example of usage: 
* `add -m CS1231`

Example of output:
```
Command: 
⋗	add -m CS1010s
════════════════════════════════════════════════════════════════════════════════
Your module has been added successfully.
Your new module:
'CS1010S: Programming Methodology (0.0MC) (Grade: No grade yet)'

════════════════════════════════════════════════════════════════════════════════
```

### 3.3 Editing an item: `edit <opt> <args>`

#### 3.3.1 Editing a task description: `edit -t`

Edit a task description in the scheduler.  
Format: `edit -t <task_index> <task_name>`

Example of usage: 
* `edit -t 3 revise for CS2113`

**Note:** <br>
 You can find the task indexes with `list -t`  

Example of output:
```
Command: 
⋗	edit -t 1 buy stuff
════════════════════════════════════════════════════════════════════════════════
Your task has been edited successfully.
Your task before editing - 'go running [x]'
Your task after editing  - 'buy stuff [x]'

════════════════════════════════════════════════════════════════════════════════
```

#### 3.3.2 Editing a module: `edit -m`

Edit a module code in the scheduler.  
Format: `edit -m <module_code> <new_module_code>`

Example of usage: 
* `edit -m CS2113 CS2113T`

Example of output:
```
Command: 
»	edit -m CG1112 ACC1002
════════════════════════════════════════════════════════════════════════════════
Your module has been edited successfully.
Your module before editing - 'CG1112: Engineering Principles and Practice II (0.0MC) (Grade: No grade yet)'
Your module after editing  - 'ACC1002: Financial Accounting (0.0MC) (Grade: No grade yet)'

════════════════════════════════════════════════════════════════════════════════
```

### 3.4 Deleting an item: `del <opt> <args>`

#### 3.4.1 Deleting a task: `del -t`

Remove a task from the scheduler.  
Format: `del -t <task_index>`

Example of usage: 
* `del -t 1`

Example of output:
```
Command: 
⋗	del -t 1
════════════════════════════════════════════════════════════════════════════════
Your task has been deleted successfully.
Your deleted task - 'buy stuff [x]'

════════════════════════════════════════════════════════════════════════════════
```

#### 3.4.2 Deleting a module: `del -m`

Remove a module from the scheduler.  
Format: `del -m <module_code>`

Example of usage: 
* `del -m CS1010` 

Example of output:
```
Command: 
⋗	del -m CG1112
════════════════════════════════════════════════════════════════════════════════
Your module has been deleted successfully.
Your deleted module - 'CG2271: Real-Time Operating Systems (4.0MC) (Grade: CS)'

════════════════════════════════════════════════════════════════════════════════
```


### 3.5 Listing all items: `list <opt> <args>`

#### 3.5.1 Listing all tasks: `list -t`

List all tasks in the scheduler.   
Format: `list -t`

Example of output:
```
Command: 
⋗	list -t
════════════════════════════════════════════════════════════════════════════════
Here's your list:

1. go running [x]
2. buy something [x], by 07:00PM, Sunday, 02 Feb 2020

════════════════════════════════════════════════════════════════════════════════
```

#### 3.5.2 Listing all modules: `list -m`

List all modules in the scheduler.  
Format: `list -m`

Example of output:
```
Command: 
⋗	list -m
════════════════════════════════════════════════════════════════════════════════
Here's your list:

1. CG1111: Engineering Principles and Practice I (4.0MC) (Grade: A+)
2. CS1231: Discrete Structures (0.0MC) (Grade: No grade yet)
3. CS1010: Programming Methodology (0.0MC) (Grade: No grade yet)
4. CS1010X: Programming Methodology (0.0MC) (Grade: No grade yet)
5. ACC1701: Accounting for Decision Makers (0.0MC) (Grade: No grade yet)
6. CG2271: Real-Time Operating Systems (0.0MC) (Grade: No grade yet)
7. CS2101: Effective Communication for Computing Professionals (4.0MC) (Grade: B+)
8. GEH1032: Modern Technology in Medicine and Health (0.0MC) (Grade: No grade yet)
════════════════════════════════════════════════════════════════════════════════
```

#### 3.6 Grade a existing module: `grade`

Assign a grade to a module in the Scheduler.
Format: `grade <module Code> <module credit> <grade>`

**Note:** <br>
&nbsp;&nbsp;&nbsp;&nbsp;CS & CU are covered in the cap calculator <br>
&nbsp;&nbsp;&nbsp;&nbsp;All grades input must be in Caps. ie `A+`

Example of usage:
* `grade CS2101 4 B+`

Example of output:
```
Command: 
⋗	grade CS2101 4 A-
════════════════════════════════════════════════════════════════════════════════
Your module has been graded successfully.
The module - 'CS2101: Effective Communication for Computing Professionals (4.0MC) (Grade: A-)'. 

════════════════════════════════════════════════════════════════════════════════
```

#### 3.7 Calculate your cap: `cap`

Calculate your new updated cap, accumulated from past semesters.
Format: `cap`

Example of usage:
* `cap`

Example of output:
```
Command: 
⋗	cap
════════════════════════════════════════════════════════════════════════════════
Your current CAP is 
4.31

════════════════════════════════════════════════════════════════════════════════
```

### 3.8 Mark as done: `done <task_index>`

Mark a task in the scheduler as done.  
Format: `done <task_index>`

Example of usage: 
* `done 1`

**Note:** <br>
 You can find the task indexes with `list -t`  

Example of output:
```
Command: 
⋗	done 1
════════════════════════════════════════════════════════════════════════════════
The task has been successfully marked as complete.
Your completed task - 'buy new stuff [√]'

════════════════════════════════════════════════════════════════════════════════
```

### 3.9 Undo a command: `undo`

Takes the previous command and revert its changes.\
Requires the previous command to be undone, a command that affects the storage. e.g. add, del, edit, done
Format: `undo`

Example of usage: 
1. `add -m CS2113T`
2. `undo`

Example of output:
```
Command: 
⋗	undo
════════════════════════════════════════════════════════════════════════════════
Undo is successful.
Command undone - 'done 1'

════════════════════════════════════════════════════════════════════════════════
```

### 3.10 Summary: `summary`

Gets an overall view of the tasks.
Format: `summary`

Example of usage: 
* `summary`

Example of output:
```
Command: 
⋗	summary
════════════════════════════════════════════════════════════════════════════════
Here's a summary of your latest tasks...

Incomplete tasks with deadlines:
1. buy something [x], by 07:00PM, Sunday, 02 Feb 20

Incomplete tasks with no deadline:
1. go running [x]

Completed tasks:
Your list is empty.

════════════════════════════════════════════════════════════════════════════════
```

### 3.11 TimeTable: `timetable <opt> <args>`

#### 3.11.1 View the timetable: `timetable <args>`

View the timetable for the day or for the week.\
`<args>` : Must be one of the following : `-day` or `-week`

Example of usage: 
* `timetable -day`
* `timetable -week`

Example of output:
```
Command: 
⋗	timetable -day
════════════════════════════════════════════════════════════════════════════════
Current NUS Week: 1

TUESDAY, 03-11-20:
 ┌───────────┬────┬────────────────────┐
 │   Time    │ ID │       Lesson       │
 ├───────────┼────┼────────────────────┤
 │ 1300-1400 │ 01 │  CS2101 Tutorial   │
 └───────────┴────┴────────────────────┘

════════════════════════════════════════════════════════════════════════════════
```

```
Command: 
⋗	timetable -week
════════════════════════════════════════════════════════════════════════════════
Current NUS Week: 1

No lessons on MONDAY, 02-11-20.

TUESDAY, 03-11-20:
 ┌───────────┬────┬────────────────────┐
 │   Time    │ ID │       Lesson       │
 ├───────────┼────┼────────────────────┤
 │ 1300-1400 │ 01 │  CS2101 Tutorial   │
 └───────────┴────┴────────────────────┘

No lessons on WEDNESDAY, 04-11-20.

THURSDAY, 05-11-20:
 ┌───────────┬────┬────────────────────┐
 │   Time    │ ID │       Lesson       │
 ├───────────┼────┼────────────────────┤
 │ 0900-1200 │ 01 │     CG1111 Lab     │
 └───────────┴────┴────────────────────┘

No lessons on FRIDAY, 06-11-20.

No lessons on SATURDAY, 07-11-20.

No lessons on SUNDAY, 08-11-20.

════════════════════════════════════════════════════════════════════════════════
```

#### 3.11.2 Add a lesson: `timetable -add`

Adds a lesson to the timetable.\
Format: `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>` <br>
`<module>` : Must be added to the module list. See module list with `list -m`. <br>
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY` <br>
`<start time>` : Must be in 24h format <br>
`<end time>` : Must be in 24h format and is after `<start time>` <br>
`<lesson type>` : Must be one of the following : `TUTORIAL`, `LECTURE`, `SEMINAR`, `LAB`, `RECITATION`, `SESSION` <br>
`<repeat>` : 0 : One time lesson ; 1 : Once a week ; 2 : Every even week ; 3 : Every odd week

Example of usage: 
* `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 0`
* `timetable -add CS2113T MONDAY 1300 1500 LAB 1`

Example of output:
```
Command: 
⋗	timetable -add cg1111 MONDAY 0900 1200 LAB 1
════════════════════════════════════════════════════════════════════════════════
Your lesson has been added successfully.
Your new lesson - 'CG1111 Lab: MONDAY 900-1200'
Added to: every week.

════════════════════════════════════════════════════════════════════════════════
```

#### 3.11.3 Delete a lesson: `timetable -del`

Deletes a lesson from the timetable.<br> 
Format: `timetable -del <day> <lesson index>` <br> 
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY` <br>
`<lesson index>` : Index of the lesson to be deleted. See timetable and the indexes with `timetable -day` or `timetable -week` <br>

Example of usage: 
* `timetable -del MONDAY 1`

Example of output:
```
Command: 
⋗	timetable -del MONDAY 1
════════════════════════════════════════════════════════════════════════════════
Your lesson has been deleted successfully from all weeks.
Your deleted lesson - 'CG1111 Lab: MONDAY 900-1200'

════════════════════════════════════════════════════════════════════════════════
```

#### 3.11.4 Reset the timetable: `timetable -reset`

Clears the current timetable and creates a new timetable.\
Format: `timetable -reset`

**Note:** <br>
After reset, it is mandatory to key in the current academic week

Example of usage: <br>
* `timetable -reset` <br>
* `3`

Example of output:
```
Command: 
⋗	timetable -reset
════════════════════════════════════════════════════════════════════════════════
Please enter the current week num e.g. 1 - 14 where 7 is recess week.
In ra.Vi, NUS week 7 onwards is week 8 onwards. 
For example, in Academic week 9, please input the current week num to be 10
════════════════════════════════════════════════════════════════════════════════
3
════════════════════════════════════════════════════════════════════════════════
Timetable reset successful.
════════════════════════════════════════════════════════════════════════════════
```

### 3.12 Exiting the program: `bye`

Exits ra.VI.\
Format: `bye`

Example of output:
```
Command: 
⋗	bye
════════════════════════════════════════════════════════════════════════════════
Goodbye, hope to see you soon!
════════════════════════════════════════════════════════════════════════════════
```

## 4 FAQ

**Q**: Can I use ra.VI on operating system other than Windows?\
**A**: Yes. ra.VI is compatible with Windows, macOS and Linux.

**Q**: Can I use ra.VI throughout my time in NUS?\
**A**: Yes! In fact, we strongly encourage using ra.VI for your time at NUS. ra.VI is robust enough to handle the trials of time.

**Q**: How do I transfer my data to another computer?\
**A**: Zip the folder where you have installed ra.VI on and transfer the zip file to the other computer. Unzip the folder on the new computer, and you are good to go!

**Q**: Can I edit the files created by ra.VI?\
**A**: It is not advised to edit the files created by ra.VI with any other software.

**Q**: Can I force close ra.VI?\
**A**: It is not advised to force close ra.VI. If you decide to do so, the changes made during that current session may get corrupted. To close ra.VI, please enter `bye`.

## 5 Command Summary

| Function | Command |
|--------|---------------------------------------|
| Add a task | `add -t <task_name> [-by <deadline>]`|
| Add a module | `add -m <module_code>` |
| Delete a task | `del -t <task_index>` |
| Delete a module | `del -m <module_code>` |
| Edit a task | `edit -t <task_index> <task_name>` |
| Edit a module | `edit -m <module_code> <new_module_code>` |
| Mark task as Done | `done <task_index>` |
| Grade and allocated MCs to a module | `grade <module_code> <grade>` | 
| Calculate cap | `cap <total mc taken> <current cap>` |
| Undo previous action | `undo` | 
| Add lesson to timetable | `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>` |
| Delete lesson from timetable | `timetable -del <day> <lesson index>` |   
| List all tasks | `list -t` |
| List all modules | `list -m` |
| View task summary | `summary` |
| View day's timetable | `timetable -day` |
| View week's timetable | `timetable -week` |
| Filter timetable | `timetable -filter <module> <day> <start time> <end time> <lesson type>` |
| Reset timetable | `timetable -reset` |
| Get list of commands | `help` |
| Get detailed help message for each command | `help <command_word>` |
| Exit ra.VI | `bye` |

## 6 Graphical_user_interface

### 6.1 Change Directory Command `cd`
Traverse to the target directory.

**Note:** <br>
All the modules and tasks are treated as directories, like the folders in the Window OS.
To create a task related to Module CS2101, the user need to go into the Directory CS2101, to create the task.
Format: `cd <module code>` <br> 
        `cd ..` <br> 
        
Example of usage: <br>
* `cd CS2101` <br>
* `cd ..` <br>

Example of output:
* `CS2101` <br>
* `Root` <br>

### 6.2 General Add `add`
The generic way to add a module or a task to the system.

**Note:** <br>
There are two types of add commands in the system: add a module and add a task.
The general add command combines and simplifies the above two command.
The ra.VI system could parse the general add command to different add commands according to the user current directory level.
Format: `add <module code or task description>` <br> 
Example of usage: <br>
* `add CS2101` <br>
* `add read a book` <br>

Example of output:
* Module has been added <br>
* Task has been added <br>

### 6.3 Undo  `undo`
Recover the data from the previous "Data-changed" operations.

**Note:** <br>
"Data-changed" operations refer to Add, Edit, Delete operations only.

Example of usage: 
1. `add -m CS2113T`
2. `undo`

Example of output:
* Undo Successfully <br>

### 6.3 Week Command  `week`
A window will pop out and the task number on the each day of the upcoming week will be listed.

Example of usage: 
* `week`

### 6.4 Directory Command  `dir`
A window will pop out and all module with related tasks will be listed.

Example of usage: 
* `dir`





