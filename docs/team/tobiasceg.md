# Tan Jian Hui Tobias - Project Portfolio Page

## CS2113t Project: ra.VI

The Repository Assistant with a Versatile Interface is a user-friendly desktop application for managing task,deadline
,modules and planning of timetable via a Command Line Interface (CLI). ra.VI aims to help freshmen of Universities to
better integrate and manage their hectic schedule. 

## Overview of my contributions to the project.

### Features implemented: 
  * Added and Managed the parser commands (excluding timetable parser).
    * What it does: Parses the user's input using java REGEX to parse and filter specific words and phrases, calling the
    relevant constructors to ensure that the subsequent command executes with the appropriate arguments.
    * Justification: There is a specific parser command for each respective command in ra.VI , each and every parser has
    its very own specialised REGEX format as the different commands require their own specific variables to execute and achieve
    the desired results
    * Highlights: This filter affects almost every command in ra.VI as the user's raw input has to be parsed to ensure that 
    the relevant desired inputs by the user are captured by the program and executed correctly. Most important and general 
    exceptions are caught and filtered by the parser class.
    * Credits: Special thanks to teammates, [Sean Lim](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/aseanseen.md) and [Jun You](https://github.com/AY2021S1-CS2113T-T09-2/tp/tree/master/docs/team/f0fz.md) for the assistance in debugging and correcting the REGEX used, as
    it is a relatively new and unknown area of java to tackle, thus a lot of problems and bugs were found making parser successful.

  * Added and Managed the grade and cap command.
    * What it does: Allows the user to grade modules that have been previously input and keyed into their personalised module list,
    thus allowing them to assign its module credits and the grade achieved by the user on the module. Subsequently, the user is able 
    to calculate their new Cumulative Average Point (CAP) after grading all their respective modules.
    * Justification: This enhancement improves the product in a way that allows users to not only keep track of the modules taken, but
    also add the relevant details such as the module credit and grade attained for that specific module. Therefore, allowing the
    user to calculate and keep track of their new CAP after the semester has ended. 
    * Highlights: This enhancement affects and compliments existing commands adding additional attributes of existing modules in
    module manager and subsequently consolidating the relevant attributes, to calculate the user's CAP. Multiple grades and scenarios 
    had to be taken into account to ensure that the CAP calculated was as accurate as possible. 
    
* **Code Contribution**: [RepoSense Link](https://nus-cs2113-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=tobiasceg&sort=groupTitle&sortWithin=title&since=2020-09-27&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Assisted in release `v1.0 - v2.1` (3 releases) on GitHub
  
* **Enhancements to existing features**:
  * Split Parser to handle individual commands separately (Pull request [\#78](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/78))
  * Added option for `CS/CU` for Cap command (Pull request [\#243](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/243))
  * Wrote additional tests for existing features to increase coverage. (Pull request [\#119](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/119), [\#59](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/59))
 
### Contributions to Documentation:
  * **User Guide**:
    * Added Documentation for the features `Cap` and `Grade`. (Pull request [\#99](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/99), [\#115](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/115))
    * Solved issues raised after bug bounties (Pull request [\#238](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/238))
    * Added in certain cosmetic tweaks to existing documentation of features and tables (Pull request [\#225](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/225))
    
  * **Developer Guide**:
    * Added Documentation for the features `Cap` and `Grade` including their respective sequence diagrams. (Pull request [\#99](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/99)), [\#112](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/112))
    * Enhancement in certain cosmetic areas to existing documentation of features and tables (Pull request [\#129](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/129))
    
### Community Contributions:
  * PRs reviewed: [\#237](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/237), [\#242](https://github.com/AY2021S1-CS2113T-T09-2/tp/pull/242)
  
    
