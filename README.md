# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11 (use the exact version), update Intellij to the most recent version.

1. **Configure Intellij for JDK 11**, as described [here](https://se-education.org/guides/tutorials/intellijJdk.html).
1. **Import the project _as a Gradle project_**, as described [here](https://se-education.org/guides/tutorials/intellijImportGradleProject.html).
1. **Verify the set up**: After the importing is complete, locate the `src/main/java/seedu/duke/Duke.java` file, right-click it, and choose `Run Duke.main()`. If the setup is correct, you should see something like the below:
   ```
   > Task :compileJava
   > Task :processResources NO-SOURCE
   > Task :classes
   
   > Task :Duke.main()
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   
   What is your name?
   ```
   Type some word and press enter to let the execution proceed to the end.

## Build automation using Gradle

* This project uses Gradle for build automation and dependency management. It includes a basic build script as well (i.e. the `build.gradle` file).
* If you are new to Gradle, refer to the [Gradle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/gradle.html).

## Testing

### I/O redirection tests

* To run _I/O redirection_ tests (aka _Text UI tests_), navigate to the `text-ui-test` and run the `runtest(.bat/.sh)` script.

### JUnit tests

* A skeleton JUnit test (`src/test/java/seedu/duke/DukeTest.java`) is provided with this project template. 
* If you are new to JUnit, refer to the [JUnit Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/junit.html).

## Checkstyle

* A sample CheckStyle rule configuration is provided in this project.
* If you are new to Checkstyle, refer to the [Checkstyle Tutorial at se-education.org/guides](https://se-education.org/guides/tutorials/checkstyle.html).

## CI using GitHub Actions

The project uses [GitHub actions](https://github.com/features/actions) for CI. When you push a commit to this repo or PR against it, GitHub actions will run automatically to build and verify the code as updated by the commit/PR.

## Documentation

`/docs` folder contains a skeleton version of the project documentation.

Steps for publishing documentation to the public: 
1. If you are using this project template for an individual project, go your fork on GitHub.<br>
   If you are using this project template for a team project, go to the team fork on GitHub.
1. Click on the `settings` tab.
1. Scroll down to the `GitHub Pages` section.
1. Set the `source` as `master branch /docs folder`.
1. Optionally, use the `choose a theme` button to choose a theme for your documentation.

## Workflow

2 branches will be used alongside the master branch for individual work and testing. The master branch should have functional code only. Each branch will only have one of us working in it.

### Setting up

* Clone this remote repository by using `git clone https://github.com/f0fz/EE2026-X4-Project.git`.
   * Note that `origin` is now the name of this remote repository.
* `cd` into the repo, then do `git checkout <name>`. A local branch named <name> should be automatically created that will track the remote <name> branch.
* That's all. Everything should be up to date. You will be working on the local <name> branch, so take note of that.
   * You can do `git remote show` to check your remote branches.

### Pulling before each work session

* Do a `git pull origin <name>:<name>` before you start working each time. Since each branch only has one person working, this shouldn't really be an issue if you're only working on one machine.
   * The command means to pull from the remote <name> branch to the local <name> branch.

### Pushing after each work session

* First, `git add .` if you want to stage all files or `git add <path/to/file>` if you want to add specific files.
* `git commit -m 'commit message here'` followed by `git push origin <name>:<name>`.
   * DO NOT PUSH TO REMOTE'S MASTER BRANCH! i.e. `git push origin <name>` without the colon portion.
   * The colon portion denotes which remote branch to push to. If left blank, it will default to master.

### Rolling back

* Undoing a local commit: Do `git revert HEAD`. `git revert` works by bringing your previous commit in as a new commit to undo what you did so far.
   * `HEAD` refers to your current commit (which is the one you wanna undo).
   * If you want to revert back to BEFORE a specific commit, use `git log` and find the commit hash (a long string of characters) and do `git revert <hash>`
* Undoing a push to remote: Do `git push -f origin <hash>:<branch>`.
   * This forces a push of the `<hash>` commit, which is the one you want to revert to, to the specified remote
 branch in `<branch>`. Use `git log` to find the hash.
   * You can undo the local commit first, then `git push -f origin master:<branch>` instead of finding a specific commit.
* Hard reset:
   * First, try doing `git fetch` then `git reset --hard origin/<branch>`.
   * If that doesn't work, delete everything in your repo except the .git folder, download from the Github repo page (green button on top right of file window), put everything in the same folder, then `git add *`, `git commit`.
