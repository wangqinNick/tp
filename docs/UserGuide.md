# ra.VI User Guide

Welcome to the ra.VI User Guide! Choose a section or sub-section from the table of contents below, get step-by-step instructions, and plan your day.

## Table of contents
1. **About-Introduction-Product Overview-Quick Start**  
&nbsp;&nbsp;[1.1 About](#11-about) <br>
&nbsp;&nbsp;[1.2 Introduction](#12-introduction)  <br>
&nbsp;&nbsp;[1.3 Product Overview](#13-product-overview) <br>
&nbsp;&nbsp;[1.4 Quick Start](#14-quick-start)<br>
2. **Key Features** <br>
&nbsp;&nbsp;2.1 Key features <br>
3. **Installation-Getting Started** <br>
&nbsp;&nbsp;3.1 Installation <br>
&nbsp;&nbsp;3.2 Getting started <br>
[4. **Features**](#4-features)  <br>
&nbsp;&nbsp;[4.1 Viewing help](#41-viewing-help-help) <br>
&nbsp;&nbsp;[4.2 Add an item](#42-adding-an-item-add-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.2.1 Add a task](#421-adding-a-task-add--t--by) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.2.2 Add a module](#422-adding-a-module-add--m) <br>
&nbsp;&nbsp;[4.3 Edit an item](#43-editing-an-item-edit-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.3.1 Edit a task description](#431-editing-a-task-description-edit--t)<br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.3.2 Editing a module](#432-editing-a-module-edit--m) <br>
&nbsp;&nbsp;[4.4 Delete an item](#44-deleting-an-item-del-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.4.1 Delete a task](#441-deleting-a-task-del--t) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.4.2 Delete a module](#442-deleting-a-module-del--m) <br>
&nbsp;&nbsp;[4.5 List items](#45-listing-all-items-list-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.5.1 List tasks](#451-listing-all-tasks-list--t) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.5.2 List modules](#452-listing-all-modules-list--m)  <br>
&nbsp;&nbsp;[4.6 Grade a module](#46-grade-a-existing-module-grade) <br>
&nbsp;&nbsp;[4.7 Calculate CAP](#47-calculate-your-cap-after-the-semester-cap) <br>
&nbsp;&nbsp;[4.8 Mark task as done](#48-mark-as-done-done-task_index) <br>
&nbsp;&nbsp;[4.9 Undo previous command](#49-undo-a-command-undo) <br>
&nbsp;&nbsp;[4.10 Summary](#410-summary-summary) <br>
&nbsp;&nbsp;[4.11 Timetable](#411-timetable-timetable-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.11.1 View timetable](#4111-view-the-timetable-timetable-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.11.2 Add a lesson](#4112-add-a-lesson-timetable--add) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[4.11.3 Delete a lesson](#4113-delete-a-lesson-timetable--del) <br>
&nbsp;&nbsp;[4.12 Exit](#412-exiting-the-program-bye) <br>

### 1.1 About

This user guide provides in-depth documentation on the ra.VI installation process, system configuration and management. In addition, the quick start guide provides and end-to-end setup process to begin tracking your tasks and timetable with ra.VI.

### 1.2 Introduction

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and notes, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ra.VI can manage your tasks faster than traditional GUI apps.\
ra.VI helps you to manage your tasks, and their corresponding deadlines with ease. It can also help you plan your timetable and being organised keeps you ahead of the curve.

**1.2.1 Tasks**\
&nbsp;&nbsp;A task refers to something that you would like to get done. This refers to reading a book or doing an assignment. Tasks can be created with or without a deadline. <br>
**1.2.2 Deadline**\
&nbsp;&nbsp;A deadline refers to a date and time associated with a task. This is in the format: DAY-MONTH-YEAR TIME_24H, e.g. `23-12-2020 1400` or `1-2-2000 0800` <br>
**1.2.3 Modules**\
&nbsp;&nbsp;A module refers to a module taken under NUS. Entered modules will be checked against the list of NUS modules. <br>
**1.2.4 CAP**\
&nbsp;&nbsp;CAP refers to Cumulative Average Point, which is the grading system used by NUS. CAP will be calculated based on your modules. <br>
**1.2.5 Done**\
&nbsp;&nbsp;A task can be marked as done. This will signify completion of the task so that you can get an easy view of any remaining tasks. <br>
**1.2.6 Timetable**\
&nbsp;&nbsp;The timetable is specific to you. It allows you to add lessons to your timetable with respect to the modules that you are taking. After setting the timetable up the first time, you do not need to go through the set up again. <br>

### 1.3 Product Overview

ra.VI is targeted at the NUS freshman. As a freshman, there are many documents and new procedures that you must get familiar with. This may be daunting for you but ra.VI will provide you with the assistance you need. 
By helping you keep track of your tasks and deadlines, you will be able to keep on top of deadlines. 
Moreover, the timetable feature helps you to schedule your lessons, allowing you to be more prepared for lessons.
Adding on, ra.VI also allows you to keep track of your CAP, so that you can keep tabs on how well you are doing, motivating you towards that elusive CAP 5.0.

### 1.4 Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your scheduler. Double-click the file to start the app. 
4. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
Some example commands you can try:
* `add -t`: Add a task
* `list -t`: List all tasks
* `bye`: Exit the program read chapter 1`
5. Refer to the Features below for details of each command

## 4 Features 

### 4.1 Viewing help: `help`

Shows a message with the list of available commands and functions.

### 4.2 Adding an item: `add <opt> <args>`

#### 4.2.1 Adding a task: `add -t [-by]`

Add a task to the scheduler.  
Format: `add -t <task_name> [-by <deadline>]`

Example of usage: 
* `add -t read chapter 1`
* `add -t read chapter 1 -by 30-12-2020 1800`

#### 4.2.2 Adding a module: `add -m`

Add a module to the scheduler.  
Format: `add -m <module_code>`

Example of usage: 
* `add -m CS1231`

### 4.3 Editing an item: `edit <opt> <args>`

#### 4.3.1 Editing a task description: `edit -t`

Edit a task description in the scheduler.  
Format: `edit -t <task_index> <task_name>`

Example of usage: 
* `edit -t 3 revise for CS2113`

Note: You can find the task indexes with `list -t`  

#### 4.3.2 Editing a module: `edit -m`

Edit a module code in the scheduler.  
Format: `edit -m <module_code> <new_module_code>`

Example of usage: 
* `edit -m CS2113 CS2113T`

### 4.4 Deleting an item: `del <opt> <args>`

#### 4.4.1 Deleting a task: `del -t`

Remove a task from the scheduler.  
Format: `del -t <task_index>`

Example of usage: 
* `del -t 1`

#### 4.4.2 Deleting a module: `del -m`

Remove a module from the scheduler.  
Format: `del -m <module_code>`

Example of usage: 
* `del -m CS1010`

Note: You can find the task indexes with `list -t`  

### 4.5 Listing all items: `list <opt> <args>`

#### 4.5.1 Listing all tasks: `list -t`

List all tasks in the scheduler.   
Format: `list -t`

#### 4.5.2 Listing all modules: `list -m`

List all modules in the scheduler.  
Format: `list -m`

#### 4.6 Grade a existing module: `grade`

Assign a grade to a module in the Scheduler.
Format: `grade <module Code> <module credit> <grade>`

Example of usage:
* `grade CS2101 4 B+`

#### 4.7 Calculate your cap after the semester: `cap`

Calculate your new updated cap, accumulated from past semesters.
Format: `cap <total module credit taken> <current cap>`

Example of usage:
* `cap 46 4.24`

### 4.8 Mark as done: `done <task_index>`

Mark a task in the scheduler as done.  
Format: `done <task_index>`

Example of usage: 
* `done 1`

### 4.9 Undo a command: `undo`

Takes the previous command and revert its changes.\
Requires the previous command to be undone, a command that affects the storage. e.g. add, del, edit, done
Format: `undo`

Example of usage: 
1. `add -m CS2113T`
2. `undo`

### 4.10 Summary: `summary`

Gets an overall view of the tasks.
Format: `summary`

Example of usage: 
* `summary`

### 4.11 TimeTable: `timetable <opt> <args>`

#### 4.11.1 View the timetable: `timetable <args>`

View the timetable for the day or for the week.\
`<args>` : Must be one of the following : `-day` or `-week`

Example of usage: 
* `timetable -day`
* `timetable -week`

#### 4.11.2 Add a lesson: `timetable -add`

Adds a lesson to the timetable.\
Format: `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>`  
`<module>` : Must be added to the module list. See module list with `list -m`.\
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
`<start time>` : Must be in 24h format
`<end time>` : Must be in 24h format and is after `<start time>`
`<lesson type>` : Must be one of the following : `TUTORIAL`, `LECTURE`, `SEMINAR`, `LAB`, `RECITATION`, `SESSION`
`<repeat>` : 0 : One time lesson ; 1 : Once a week ; 2 : Every even week ; 3 : Every odd week

Example of usage: 
* `timetable -add CS2101 TUESDAY 0800 1000 LECTURE 0`
* `timetable -add CS2113T MONDAY 1300 1500 LAB 1`

#### 4.11.3 Delete a lesson: `timetable -del`

Deletes a lesson from the timetable.\
Format: `timetable -del <day> <lesson index>`  
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
`<lesson index>` : Index of the lesson to be deleted. See timetable and the indexes with `timetable -day` or `timetable -week`

Example of usage: 
* `timetable -del MONDAY 1`

### 4.12 Exiting the program: `bye`

Exits ra.VI.\
Format: `bye`

## FAQ

**Q**: Can I use ra.VI on operating system other than Windows?\
**A**: Yes. ra.VI is compatible with Windows, macOS and Linux.

**Q**: Can I use ra.VI throughout my time in NUS?\
**A**: Yes! In fact, we strongly encourage using ra.VI for your stint at NUS. ra.VI is robust enough to handle the trials of time.

**Q**: How do I transfer my data to another computer?\
**A**: Zip the folder where you have installed ra.VI on and transfer the zip file to the other computer. Unzip the folder on the new computer, and you are good to go!

**Q**: Can I edit the files created by ra.VI?\
**A**: It is not advised to edit the files created by ra.VI with any other software.

**Q**: Can I force close ra.VI?\
**A**: It is not advised to force close ra.VI. To close ra.VI, please enter `bye`

## Command Summary

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
| Undo previous action | `undo` | 
| Add lesson to timetable | `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>` |
| Delete lesson from timetable | `timetable -del <day> <lesson index>` |   
| List all tasks | `list -t` |
| List all modules | `list -m` |
| View task summary | `summary` |
| View day's timetable | `timetable -day` |
| View week's timetable | `timetable -week` |
| View week's timetable | `timetable -filter <module> <day> <start time> <end time> <lesson type>` |
| Get list of commands | `help` |
| Get detailed help message for each command | `help <command_word>` |
| Exit ra.VI | `bye` |

### To note:

* List of possible `<day>` values:\
`MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`

* List of possible `<lesson type>` values:\
`TUTORIAL`, `LECTURE`, `SEMINAR`, `LAB`, `RECITATION`, `SESSION`

* `<time>` must be in the 24h format, e.g. `0900`, `1415`
