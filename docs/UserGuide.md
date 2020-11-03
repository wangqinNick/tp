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
&nbsp;&nbsp;[3.7 Calculate CAP](#37-calculate-your-cap-after-the-semester-cap) <br>
&nbsp;&nbsp;[3.8 Mark task as done](#38-mark-as-done-done-task_index) <br>
&nbsp;&nbsp;[3.9 Undo previous command](#39-undo-a-command-undo) <br>
&nbsp;&nbsp;[3.10 Summary](#310-summary-summary) <br>
&nbsp;&nbsp;[3.11 Timetable](#311-timetable-timetable-opt-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.1 View timetable](#3111-view-the-timetable-timetable-args) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.2 Add a lesson](#3112-add-a-lesson-timetable--add) <br>
&nbsp;&nbsp;&nbsp;&nbsp;[3.11.3 Delete a lesson](#3113-delete-a-lesson-timetable--del) <br>
&nbsp;&nbsp;[3.12 Exit](#312-exiting-the-program-bye) <br>
[4. **FAQ**](#4-faq) <br>
[5. **Command Summary**](#5-command-summary) <br>

### 1 Introduction

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and notes, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ra.VI can manage your tasks faster than traditional GUI apps.\
ra.VI helps you to manage your tasks, and their corresponding deadlines with ease. It can also help you plan your timetable and being organised keeps you ahead of the curve.

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
2. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v2.0).
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
* `help add`
* `help timetable`
* `help help`

Get detailed help message for each command

### 3.2 Adding an item: `add <opt> <args>`

#### 3.2.1 Adding a task: `add -t [-by]`

Add a task to the scheduler.  
Format: `add -t <task_name> [-by <deadline>]`

Example of usage: 
* `add -t read chapter 1`
* `add -t read chapter 1 -by 30-12-2020 1800`

#### 3.2.2 Adding a module: `add -m`

Add a module to the scheduler.  
Format: `add -m <module_code>`

Example of usage: 
* `add -m CS1231`

### 3.3 Editing an item: `edit <opt> <args>`

#### 3.3.1 Editing a task description: `edit -t`

Edit a task description in the scheduler.  
Format: `edit -t <task_index> <task_name>`

Example of usage: 
* `edit -t 3 revise for CS2113`

Note: You can find the task indexes with `list -t`  

#### 3.3.2 Editing a module: `edit -m`

Edit a module code in the scheduler.  
Format: `edit -m <module_code> <new_module_code>`

Example of usage: 
* `edit -m CS2113 CS2113T`

### 3.4 Deleting an item: `del <opt> <args>`

#### 3.4.1 Deleting a task: `del -t`

Remove a task from the scheduler.  
Format: `del -t <task_index>`

Example of usage: 
* `del -t 1`

#### 3.4.2 Deleting a module: `del -m`

Remove a module from the scheduler.  
Format: `del -m <module_code>`

Example of usage: 
* `del -m CS1010`

Note: You can find the task indexes with `list -t`  

### 3.5 Listing all items: `list <opt> <args>`

#### 3.5.1 Listing all tasks: `list -t`

List all tasks in the scheduler.   
Format: `list -t`

#### 3.5.2 Listing all modules: `list -m`

List all modules in the scheduler.  
Format: `list -m`

<<<<<<< HEAD
### 4.6 Grade a existing module: `grade`
=======
#### 3.6 Grade a existing module: `grade`
>>>>>>> master

Assign a grade to a module in the Scheduler.
Format: `grade <module Code> <module credit> <grade>`

**Note:** For special grades: <br>
&nbsp;&nbsp;&nbsp;&nbsp;CS,CU,S,U are not covered in the calculator.

Example of usage:
* `grade CS2101 4 B+`

<<<<<<< HEAD
### 4.7 Calculate your cap after the semester: `cap`
=======
#### 3.7 Calculate your cap after the semester: `cap`
>>>>>>> master

Calculate your new updated cap, accumulated from past semesters.
Format: `cap <total module credit taken> <current cap>`

**Note:** For first semester, key in <br>
&nbsp;&nbsp;&nbsp;&nbsp;`<total module credit taken>` = 0 <br>
&nbsp;&nbsp;&nbsp;&nbsp;`<current cap>` = 0 <br>
To get the current semester's CAP.

Example of usage:
* `cap 46 4.24`

### 3.8 Mark as done: `done <task_index>`

Mark a task in the scheduler as done.  
Format: `done <task_index>`

Example of usage: 
* `done 1`

### 3.9 Undo a command: `undo`

Takes the previous command and revert its changes.\
Requires the previous command to be undone, a command that affects the storage. e.g. add, del, edit, done
Format: `undo`

Example of usage: 
1. `add -m CS2113T`
2. `undo`

### 3.10 Summary: `summary`

Gets an overall view of the tasks.
Format: `summary`

Example of usage: 
* `summary`

### 3.11 TimeTable: `timetable <opt> <args>`

#### 3.11.1 View the timetable: `timetable <args>`

View the timetable for the day or for the week.\
`<args>` : Must be one of the following : `-day` or `-week`

Example of usage: 
* `timetable -day`
* `timetable -week`

#### 3.11.2 Add a lesson: `timetable -add`

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

#### 3.11.3 Delete a lesson: `timetable -del`

Deletes a lesson from the timetable.\
Format: `timetable -del <day> <lesson index>`  
`<day>` : Must be one of the following : `MONDAY`, `TUESDAY`, `WEDNESDAY`, `THURSDAY`, `FRIDAY`, `SATURDAY`, `SUNDAY`
`<lesson index>` : Index of the lesson to be deleted. See timetable and the indexes with `timetable -day` or `timetable -week`

Example of usage: 
* `timetable -del MONDAY 1`

### 3.12 Exiting the program: `bye`

Exits ra.VI.\
Format: `bye`

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
| Undo previous action | `undo` | 
| Add lesson to timetable | `timetable -add <module> <day> <start time> <end time> <lesson type> <repeat>` |
| Delete lesson from timetable | `timetable -del <day> <lesson index>` |   
| List all tasks | `list -t` |
| List all modules | `list -m` |
| View task summary | `summary` |
| View day's timetable | `timetable -day` |
| View week's timetable | `timetable -week` |
| Filter timetable | `timetable -filter <module> <day> <start time> <end time> <lesson type>` |
| Get list of commands | `help` |
| Get detailed help message for each command | `help <command_word>` |
| Exit ra.VI | `bye` |