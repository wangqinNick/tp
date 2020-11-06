# Sean Lim Zhi Xiang - Project Portfolio Page

## Overview

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for managing tasks, deadlines, and timetable, optimized for use via a Command Line Interface (CLI). It is written in Java and is built to run on Java 11 on Linux, Mac and Windows machines.

### Summary of Contributions
#### Code contributed

https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aseanseen

#### Features implemented

* Added the timetable feature in collaboration with [Jun You](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/f0fz.md) #101 #118
    * Implemented the parsing of the timetable commands.
        * Used Regex to ensure that only timetable commands with the appropriate format create `TimeTableCommand` objects.
    * Implemented the `TimeTableManager` to handle the lessons from the timetable.
        * Implemented `TimeTableManager` to contain a hashmap of week number of the year to `LessonManager` objects where each `LessonManager` contained the lessons for a week.\
        `TimeTableManager` creates the number of `LessonManager`(s) based on the number of weeks left in one semester.
        * Amended by [Jun You](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/f0fz.md) to include a `TimeTable` object instead due to the difficulty faced during saving.
    * Implemented the commands to add and delete lessons from the timetable.
        * Created `TimeTableCommand`, `TimeTableAddCommand`, `TimeTableDeleteCommand`.
* Added add/delete tasks and modules feature #17 #35 #54
    * Created `AddTaskCommand`, `AddModuleCommand`, `DeleteTaskCommand`, `DeleteModuleCommand`.
* Added main class and exit command #40 #53

#### Enhancements

* After a successful undo command, show the user the command that was undone. #241
    * Added an ArrayList to the `State` to save the successful command input.
* Fix checkstyle for many classes. #145
    * Based on the Java coding standard, I amended `checkstyle.xml` to make it more comprehensive.

#### Contributions to documentation

* DG:
    * Added documentation for the `timetable`, `add`, `delete` features. #96 #97 #122 #136
    * Added class diagrams for `Command`, `TimeTable`. #122 #127 #136 #244
    * Added sequence diagram for `AddCommand`. #110

* UG:
    * Added documentation for the Introduction. #116 #235
    * Added documentation for the FAQ. #235
    * Added documentation for the `timetable` feature. #116

#### Contributions to team-based tasks
##### Review/mentoring contributions

* PRs reviewed (with non-trivial review comments): #242 #29 #106 #44 #226 #238
