# ra.VI User Guide

## Introduction

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and notes, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ra.VI can manage your tasks faster than traditional GUI apps.

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `ra.VI` from [here](https://github.com/AY2021S1-CS2113T-T09-2/tp/releases/tag/v1.0).
3. Copy the file to the folder you want to use as the home folder for your scheduler. Double-click the file to start the app. 
4. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.  
Some example commands you can try:
* `add -t`: Add a task
* `list -t`: List all tasks
* `exit`: Exit the program read chapter 1`
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
* `add -t read chapter 1 -by 2-2-2020 1800`

#### Adding a module: `add -m`
Add a module to the scheduler.  
Format: `add -m <module_code>`

Example of usage: 
* `add -m CS1231`

### Editing an item: `edit <opt><args>`
#### Editing a task description: `edit -t <task_index> <args>`
Edit a task description in the scheduler.  
Format: `edit -t <task_index> <args>`

Example of usage: 
* `edit -t 3 revise for CS2113`

#### Editing a module: `edit -m <module_code> <new_module_code>`
Edit a module code in the scheduler.  
Format: `edit -m <module_code> <new_module_code>`

Example of usage: 
* `edit -m CS2113 CS2113T`

### Deleting an item: `del <opt><args>`
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

### Listing all items: `list <opt> <args>`
#### Listing all tasks: `list -t`
List all tasks in the scheduler.   
Format: `list -t`

#### Listing all modules: `list -m`
List all modules in the scheduler.  
Format: `list -m`

### Mark as done: `done <task_index>`
Mark a task in the scheduler as done.  
Format: `done <task_index>`

Example of usage: 
* `done 1`

### Exiting the program: `bye`
Exits ra.VI.  
Format: `bye`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
