# Sim Jun You - Project Portfolio Page

## Overview

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and timetable,
optimized for use via a Command Line Interface (CLI). It is written in Java and is built to run on Java 11 on Linux, Mac
and Windows machines.

### Summary of Contributions
#### Code contributed

[RepoSense link here.](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=f0fz)

#### Features implemented

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

#### Enhancements

* Assisted in resolving bugs and adding functionality to many other areas, like the various Commands and Parser classes
as well as the main class. Some examples:
    * Made adding, editing, and deleting tasks/modules/lessons show the results of the change.
    * Reduced dependencies in the main class by packaging all dependencies needed to execute a command into the
    `Executor` class, following the Single Responsibility Principle.
    * Added the shutdown hook to catch unexpected shutdowns.
    * Improved the `CommandResult` class to make it more useful and output-friendly.
    * Worked heavily on the `TextUi` class to improve formatting and created the helper class `TextHelper` to decrease
    bloat and to follow the Single Responsibility Principle.
* Polished the user interface formatting as well as each message to make it cleaner and easier to read.

#### Contributions to documentation

* DG:
    * Wrote the following sections: Setting Up, Value Proposition, Implementation.
    * Added class diagrams for the Main Class, and the Command/Data/Parser/TimeTable Family Classes.
    * Added sequence diagram for the main loop.

* UG:
    * 

#### Contributions to team-based tasks

* Did general code enhancements (cleanups, refactoring).
* Introduced the use of PlantUML for our UML diagrams.
* Documented non-feature-specific portions in our DG.

#### Review/mentoring contributions

* PRs reviewed (with non-trivial review comments): #13 #17 #44 #57 #76 #106 #107 #231 #235 #243

#### Contributions beyond the project team

* Reviewed and gave [detailed issues](https://github.com/f0fz/ped/issues) for other teams' projects