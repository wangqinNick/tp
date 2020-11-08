# Amalina Sani's Project Portfolio Page
## CS2113T Project: ra.VI
### Overview
Repository assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and timetables, optimized for use via a Command Line Interface (CLI). 
Ra.VI aims to help freshmen ease into and adapt to their new university schedules. 

### Summary of Contributions
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
  * What it does: A general help message allows the user to view the list of all available commands and its functions. 
  Detailed help messages for each command allows the user to view the command's format and example usage. A prompt to use `help` is also shown to the user when the user inputs an incorrect command. 
  * Justification: This feature is important in providing seamless assistance for the usage of ra.VI. It assists the user in navigating all the features and commands of the application. 
  * Highlights: The implementation of this feature was challenging as a unique help message had to be integrated into each existing command.

* **Summary Feature**: Added the ability to view a task summary in collaboration with [Jun You](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/f0fz.md). (Pull request [\#93](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/93))
  * What it does: Allows the user to view a task summary, categorising tasks into "Incomplete tasks with deadlines", "Incomplete tasks without deadlines", and "Completed tasks". 
  * Justification: This feature is important in providing the user with a brief, categorised list of tasks for quick viewing. 
  
* **View Timetable Feature**: Added the ability to view the timetable for the day or week in collaboration with [Sean Lim](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/aseanseen.md). (Pull request [\#120](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/120))
  * What it does: Allows the user to view the timetable, listing all lessons and lesson times for the day or week.
  * Justification: This feature is important in providing the user with a brief, day-to-day timetable of lessons for quick viewing. 
  * Highlights: The implementation of this feature required a clean text-based UI to display the timetable. 
  
* **Reset Timetable Feature**: Added the ability to reset the timetable. (Pull request [\#230](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/230))
  * What it does: Allows the user to clear and reinitisialise the timetable.
  * Justification: This feature is necessary for the user to populate the timetable with new lessons in preparation for a new semester.  
  
#### Code contributed [RepoSense link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=amalinasani&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

#### Documentation
  * User Guide:
    * Added documentation for the features `help`, `add`, `edit`, `del`, `list`, `done`, `bye` [\#106](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/106)
    * Added a command summary table [\#132](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/132)
    * Added documentation for the features `timetable -reset` [\#240](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/240)
    
  * Developer Guide:
    * Added instructions for manual testing. [\#128](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/128)
    * Added implementation details and sequence diagram for the `list` feature. [\#133](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/133)
    * Added documentation for `timetable -reset` [\#240](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/240)
    * Added user stories for existing features [\#251](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/251)
    * Added manual testing steps for `list`, `help`, and `summary` commands. [\#251](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/251)
    * Added implementation details and class diagram for the proposed `notes` feature [\#257](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/257)

#### Project management
* Added and managed issues for milestones v1.0, v2.0, v2.1. 

#### Enhancements to existing features
  * Wrote additional tests for `help`,`done`,`list` commands to increase coverage. (Pull requests [\#79](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/79), [\#252](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/252))
  * Solve issues surfaced from bug bounty. ([\#233](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/233))
    
#### Community
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S1-CS2113T-T12-2/tp/issues/226), [2](https://github.com/AY2021S1-CS2113T-T12-2/tp/issues/225), [3](https://github.com/AY2021S1-CS2113T-T12-2/tp/issues/224), [4](https://github.com/nus-cs2113-AY2021S1/tp/pull/36/files/8b45df880852c7cd42988080fb263f72f22a6328))
