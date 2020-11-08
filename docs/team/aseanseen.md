# Sean Lim Zhi Xiang - Project Portfolio Page

# CS2113T Project: ra.VI

Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for NUS freshmen, helping them manage tasks, deadlines, and their timetable. It is optimized for use via a Command Line Interface (CLI), written in Java and is built to run on Java 11 on Linux, Mac and Windows machines.

## My contributions

### Features implemented:

* **New Feature**: Added the timetable feature [#101](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/101) [#118](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/118)
    * What it does: Allows the user to manage their lessons in their timetable. This includes adding, deleting, viewing and filtering.
    * Justification: This feature allows students to manage all of their classes in addition to their tasks and deadlines. This allows the student be aware of their lessons on a daily basis and prepare for them.
    * Highlights: This feature was challenging due to the Time element. Therefore, we had to be very careful during our implementation, creating a robust data structure to handle the timetable.
    * In-depth contribution:
        * Implemented the parsing of the timetable commands.
            * Used Regex to ensure that only timetable commands with the appropriate format create `TimeTableCommand` objects.
        * Implemented the `TimeTableManager` to handle the lessons from the timetable.
            * Implemented `TimeTableManager` to contain a hashmap of week number of the year to `LessonManager` objects where each `LessonManager` contained the lessons for a week.\
            `TimeTableManager` creates the number of `LessonManager`(s) based on the number of weeks left in one semester.
            * Amended by [Jun You](https://ay2021s1-cs2113t-t09-2.github.io/tp/team/f0fz.html) to include a `TimeTable` object instead due to the difficulty faced during saving.
        * Implemented the commands to add and delete lessons from the timetable.
            * Created `TimeTableCommand`, `TimeTableAddCommand`, `TimeTableDeleteCommand`.
    * Credits: [Jun You](https://ay2021s1-cs2113t-t09-2.github.io/tp/team/f0fz.html)

* **New Feature**: Added add/delete tasks and modules feature [#17](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/17) [#35](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/35) [#54](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/54)
    * What it does: Allows the user to manage their tasks and modules. This includes adding, deleting and handling of deadlines.
    * Justification: This feature allows students to manage their tasks, deadlines and modules. This allows the student be on top of their tasks and deadlines, while keeping track of the modules they are taking / have taken.
    * Highlights: Implemented with adaptations from my [iP](https://github.com/Aseanseen/ip).
    * In-depth contribution:
        * Created `AddTaskCommand`, `AddModuleCommand`, `DeleteTaskCommand`, `DeleteModuleCommand`.
    * Credits: Implemented with adaptations from my [iP](https://github.com/Aseanseen/ip)

* **New Feature**: Added main class and exit command [#40](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/40) [#53](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/53)
    * What it does: Main entry and exit points for the app.
    * Justification: This is a fundamental feature for the app to work.
    * Credits: Implemented with adaptations from my [iP](https://github.com/Aseanseen/ip) and [AddressBook (Level 2)](https://se-education.org/addressbook-level2/).
    
* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aseanseen)

* **Enhancements**:

    * After a successful undo command, show the user the command that was undone. [#241](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/241)
        * Added an ArrayList to the `State` to save the successful command input.
    * Fix checkstyle for many classes. [#145](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/145)
        * Based on the Java coding standard, I amended `checkstyle.xml` to make it more comprehensive.
    * Added Jansi library
        * Did cosmetic tweaks to add some colors to the application.

### Contributions to team-based tasks

* **Project Management**:

    * Managed releases `v1.0` - `v2.1` (3 releases) on Github

* **Documentation**:

    * Developer Guide:
        * Added documentation for the `timetable`, `add`, `delete` features. [#96](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/96) [#97](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/97) [#122](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/122) [#136](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/136)
        * Added class diagrams for `Command`, `TimeTable`. [#122](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/122) [#127](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/127) [#136](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/136) [#244](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/244)
        * Added sequence diagram for `AddCommand`. [#110](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/110)
    
    * User Guide:
        * Added documentation for the Introduction. [#116](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/116) [#235](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/235)
        * Added documentation for the FAQ. [#235](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/235)
        * Added documentation for the `timetable` feature. [#116](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/116)

* **Review/mentoring contributions**:

    * PRs reviewed (with non-trivial review comments): [#242](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/242) [#29](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/29) [#106](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/106) [#44](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/44) [#226](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/226) [#238](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/238)
