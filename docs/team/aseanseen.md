### Sean Lim Zhi Xiang - Project Portfolio Page
#### CS2113T Project: ra.VI
Repository Assistant with a Versatile Interface (ra.VI) is a desktop app for NUS freshmen, helping them manage tasks, deadlines, and their timetable. It is optimized for use via a Command Line Interface (CLI).
#### My contributions
#### Features implemented:
* **New Feature**: Added the timetable feature [#101](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/101) [#118](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/118)
    * What it does: Allows the user to manage their lessons in their timetable. This includes adding, deleting, viewing and filtering.
    * Justification: This feature allows students to manage all of their classes in addition to their tasks and deadlines. This allows the student be aware of their lessons on a daily basis and prepare for them.
    * Highlights: This feature was challenging due to the Time element. Therefore, we had to be very careful during our implementation, creating a robust data structure to handle the timetable.
    * In-depth contribution:
        * Implemented the parsing of the timetable commands.
        * Implemented the `TimeTableManager` to handle the lessons from the timetable.
        * Implemented the commands to add and delete lessons from the timetable.
    * Credits: [Jun You](https://ay2021s1-cs2113t-t09-2.github.io/tp/team/f0fz.html)
* **New Feature**: Added add/delete tasks and modules feature [#17](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/17) [#35](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/35) [#54](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/54)
    * What it does: Allows the user to manage their tasks and modules. This includes adding, deleting and handling of deadlines.
    * Justification: This allows the student be on top of their tasks and deadlines, while keeping track of the modules they are taking / have taken.
    * Credits: Implemented with adaptations from my [iP](https://github.com/Aseanseen/ip)
* **New Feature**: Added main class and exit command [#40](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/40) [#53](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/53)
    * Credits: Implemented with adaptations from my [iP](https://github.com/Aseanseen/ip) and [AddressBook (Level 2)](https://se-education.org/addressbook-level2/).
* **Code contributed**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=aseanseen)
* **Enhancements**:
    * After a successful undo command, show the user the command that was undone. [#241](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/241)
    
#### Contributions to team-based tasks
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
* **Review / mentoring contributions**:
    * PRs reviewed (with non-trivial review comments): [#242](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/242) [#29](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/29) [#106](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/106) [#44](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/44) [#226](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/226) [#238](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/238)
    * Reported bugs and suggestions for other teams' tp (examples: [1](https://github.com/Aseanseen/ped/issues), [2](https://github.com/AY2021S1-CS2113T-T09-4/tp/issues/282), [3](https://github.com/AY2021S1-CS2113T-T09-4/tp/issues/289), [4](https://github.com/AY2021S1-CS2113T-T09-4/tp/issues/288))