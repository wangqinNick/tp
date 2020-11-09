# [Wang Qin] - Project Portfolio Page

## Overview
Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and timetable, optimized for use via a Command Line Interface (CLI). It is written in Java and is built to run on Java 11 on Linux, Mac and Windows machines.

## My contributions
### Features implemented for Individual GUI Team Project:

* **New Feature**: Added the Graphical User Interface to the project
    * What it does: Allows user to have more intuitive concepts about the modules and related tasks.
    * Justification: The GUI is done by myself, thus due to the limited of time and human resources, it could only do limited features.
    * Highlights: 1. Different kinds of pop up windows and notifications are added.
                  2. The GUI is implemented with the console parts (console screen and console).
    * In-depth contribution:
        * Created a MainStage for the basic view.
        * Besides the MainStage, different kinds of pop-up components will be shown when the user clicks some buttons.
        * Add different kinds of interactions. For example, when the user hangs the mouse over a section, some windows will also be shown.

* **New Feature**: Added Directory System feature
    * What it does: The Directory System treats all the modules as directories. 
    * Justification: It follows the UNIX style. 
    * Highlights: 1. The directory level hierarchy is 'Root -> Module -> Task'
        * In-depth contribution:
            * Users could traverse through modules and root by using Change Directory command.
            * The traversing is facilitated by the traverse stack. This stack is used to store the level of directories.

* **New Feature**: Traverse previous commands feature.
    * What it does: Use the Up/Down arrow on the keyboard to traverse previous input commands in the GUI.
    * Justification: Users (students) would like to input a previous command. Like our terminal or CMD, the UP/DOWN arrow on the keyboard is used to traverse.
    * Highlights: 1. Add the event listener to catch the key press action.
        * In-depth contribution:
            * Add a CommandManager class to manage the history of all commands.

### Features implemented for Team CLI Team Project:

* **New Feature**: Added Edit task feature [#44](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/44)
    * What it does: Allows users (students) to edit existing tasks.
    * Justification: Users (students) may make some mistakes when adding tasks, like entering the wrong task description. This feature allows them to edit the task description.
    * Highlights: N.A.
    
* **New Feature**: Added Undo feature [#77](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/77)
    * What it does: Recover the previous operation.
    * Justification: The undo command could only recover the previous data-changed operations. The User could undo as many times as he wants. 
    * Highlights: 1. All commands are classified into "Edit", "Info" types.
                  2. Add the State class to save the copy of all data after user input "Data-Changed" operations.
                  3. Add the State Manager class to manage the saving states.
        * In-depth contribution:
            * Add encoder function to encoder the Module and Task objects to Json strings when saving.
            * Add decoder function to decode the Json strings back to Module and Task objects when loading.

* **New Feature**: Module Loader feature [#28](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/28)
    * What it does: Allow the program load all module information when it starts.
    * Justification: The module information is provided by NUSMODS.
    * Highlights: Add the IoManager class to load modules when the program starts.
        * In-depth contribution:
                * Parse the Json file (module information) to the Module object.
                * Once all modules are parsed, save them to the HashMap that stores the moduleMap.

    
* **New Feature**: Added Undo feature [#77](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/77)
    * What it does: Recover the previous operation.
    * Justification: The undo command could only recover the previous data-changed operations. The User could undo as many times as he wants. 
    * Highlights: 1. All commands are classified into "Edit", "Info" types.
                  2. Add the State class to save the copy of all data after user input "Data-Changed" operations.
                  3. Add the State Manager class to manage the saving states.
        * In-depth contribution:
            * Add encoder function to encoder the Module and Task objects to Json strings when saving.
            * Add decoder function to decode the Json strings back to Module and Task objects when loading.


#### Code contributed
* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=wangqinNick)
* **Code contributed For Individual Team Project**: The Graphical User Interface branch. [GUI Branch link](https://github.com/wangqinNick/tp/tree/branch-iptp)

#### Features implemented
* **For Individual Team Project**:
* Built the entire `data` package, excluding State and StateManager.
    * Built the user-based data classes such as `Lesson`, `Module`, `Task`, and their respective "Managers". Also,
    maintained and made tests for them to ensure reliability.
    * Created a modular system where each atomic data item (like `Lesson`, `Task`, etc.) are stored in progressively 
    larger "Managers" (`LessonManager`, etc.) which can be held in even larger "Managers"
    (i.e. `TimeTableManager` > `TimeTable` > `LessonManager` > `Lesson`).
* Built the entire `storage` package that allows ra.VI to save and load. Integrated `InputOutputManager`
into the main codebase for seamless saving and loading when ra.VI starts/exits.
    * Created a granular and robust system for saving and loading, with multiple fallbacks and comprehensive error
    handling.
    * Maintained the package, and was in charge of fixing all bugs for saving and loading.
    * Was not the one who first implemented FastJSON usage, but took over its implementation and maintenance. Also,
    did not write the HTTP functions that call the NUSMods API.
* Implemented the logging feature, which improved our ability to debug ra.VI during the development process.


#### Contributions to documentation
#### Contributions to the DG
* **Developer Guide**:
        * Added documentation for the `editTask`, `undo` features. [#96]
        * Added class diagrams for `State`, `StateManager`. 
        * Added sequence diagram for `editTask`, `undo`. 
        
### Contributions to team-based tasks
* **Project Management**:
    * Managed individual releases `v2.1` (1 releases) on Github

##### Review/mentoring contributions
* **Review/mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [#26](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/26)

##### Contributions beyond the project team
* **Individual Team Project**:
    * The Graphical User Interface branch. [GUI Branch link](https://github.com/wangqinNick/tp/tree/branch-iptp)
    
#### [Optional] Contributions to the User Guide (Extracts)
* **User Guide**:
        * Added documentation for the Conclusion. 
        * Added documentation for the FAQ. 
        * Added documentation for the Graphical User Interface v3.0. 
        * Added documentation for the `undo` feature. 
