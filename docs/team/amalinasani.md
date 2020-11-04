# Amalina Sani's Project Portfolio Page

## Project: ra.VI
### Overview
Repository assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and notes, optimized for use via a Command Line Interface (CLI).  
If you can type fast, ra.VI can manage your tasks faster than traditional GUI apps.
ra.VI helps you to manage your tasks, and their corresponding deadlines with ease. It can also help you plan your timetable and being organised keeps you ahead of the curve.

### Summary of Contributions
Given below are my contributions to the project.

#### Features
* **Done Feature**: Added the ability to mark a task as done in ra.VI. (Pull request [\#38](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/38))
  * What it does: Allows the user to mark a task in the task list as done.
  * Justification: This feature is integral to the product so that the user can update the completion status of tasks.
  * Highlights: This enhancement ties in with the `summary` command, in which the tasks will be listed according to completion status. 
  
* **List Feature**: Added the ability to list all added tasks and modules in ra.VI. (Pull request [\#38](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/38))
  * What it does: Allows the user to view the list of tasks or modules. 
  * Justification: This feature is integral to the product so that the user can view all the tasks and modules that has been added at a quick glance. 
  * Highlights: This enhancement affects existing commands as the task index from `list` is required as a parameter for `done` and `edit` commands.

* **Help Feature**: Added the ability to view the list of all available commands, and help messages for each command. (Pull requests [\#39](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/39), [\#107](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/107))
  * What it does: 
  A general help message allows the user to view the list of all available commands and its functions. 
  Detailed help messages for each command allows the user to view the command's format and example usage.
  A prompt to use `help` is also shown to the user when the user inputs an incorrect command. 
  * Justification: This feature is important in providing seamless assistance for the usage of ra.VI. 
  It assists the user in navigating all the features and commands of the application. 
  * Highlights: This enhancement affects existing commands as the task index from `list` is required as a parameter for `done` and `edit` commands.
  The implementation was challenging as a unique help message had to be integrated into each existing command.

* **Summary Feature**: Added the ability to view a task summary. (Pull request [\#93](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/93))
  * What it does: Allows the user to view a task summary, categorising tasks into "Incomplete tasks with deadlines", "Incomplete tasks without deadlines", and "Completed tasks". 
  * Justification: This feature is important in providing the user with a brief, categorised list of tasks for quick viewing. 
  * Highlights: This implementation of this feature required that 
  -------
  affects existing commands as the task index from `list` is required as a parameter for `done` and `edit` commands. 
  It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands. 
  
* **View Timetable Feature**: Added the ability to list all added tasks and modules in ra.VI. (Pull request [\#120](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/120))

* **Reset Timetable Feature**: Added the ability to list all added tasks and modules in ra.VI. (Pull request [\#230](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/230))

#### Code contributed
[RepoSense link]()

#### Project management
* Managed releases `v1.0` - `v2.1` (3 releases) on GitHub

#### Enhancements to existing features
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for `help` and `done` commands to increase coverage from 88% to 92% (Pull requests [\#79]())
  * Solve issues [\#233]()
  
#### Documentation
  * User Guide:
    * Added documentation for the features `help`, `add`, `edit`, `del`, `list`, `done`, `bye` [\#106]()
    * Added a command summary table [\#132]()
    * Added documentation for the features `timetable -reset` [\#240]()
    
  * Developer Guide:
    * Did grammatical and language tweaks to existing documentation [\#128]()
    * Added instructions for manual testing. [\#128]()
    * Added implementation details of the `list` feature. [\#133]()
    * Added sequence diagram for the `list` feature. [\#133]()
    * Added documentation `timetable -reset` [\#240]()
    
    
#### Community
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
