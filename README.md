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

The workflow will go like this:
* Synchronise your local repo with the team repo.
* Make a separate branch for your work session.
* Commit and push your work to the corresponding branch on your remote repo.
* Make a pull request to merge your remote repo's work to the team repo and resolve merge conflicts.

### Setting up for each work session

*  `git fetch upstream`, `git rebase upstream/master`
   * The command means to fetch from the team repo (upstream) and then rebase master.
   * Your local master should be synchronised with the upstream master now.
* `git switch -c <branchname>`
   * Make a new branch for your local work (to preserve master's functionality).
   * `git checkout -b <branchname>` does the same thing.

### Pushing and merging your work after each work session

* First, `git add .` if you want to stage all files or `git add <path/to/file>` if you want to add specific files.
* `git commit -m 'commit message here'` followed by `git push origin <branchname>`.
   * Git will create the corresponding branch on your remote repo and push your commits there.
* Merge your work into the team repo's master with a pull request over Github's interface.
   * Do not confirm merge! Let the rest of the team view the pull request first before we confirm that it can be merged.
   * Any merge conflicts should be resolved AFTER we okay the merge.
   * After we all okay-ed the merge, the merge can continue and your work is complete.

### Rolling back

* First off, you can bookmark [this website](https://ohshitgit.com/) because you will need it at some point.
* Undoing a local commit: Do `git revert HEAD`. `git revert` works by bringing your previous commit in as a new commit to undo what you did so far.
   * `HEAD` refers to your current commit (which is the one you want to undo).
   * If you want to revert back to __BEFORE__ a specific commit, use `git log` and find the commit hash (a long string of characters but you only need the first 6 or so) and do `git revert <hash>`.
* Undoing a push to remote: Do `git push -f origin <hash>:<branch>`.
   * This forces a push of the `<hash>` commit, which is the one you want to revert __to__, to the specified remote branch in `<branch>`. Use `git log` to find the hash.
   * Alternatively, you can undo the local commit first (as in point 1), then `git push -f origin master:<branch>` instead of finding a specific commit.
* Hard reset:
   * First, try doing `git fetch` then `git reset --hard origin/<branch>`. This gets your remote's latest commits (which shouldn't include your mistakes) and then resets your working directory to match the latest commit on your remote. __ALL WORK ON LOCAL THAT IS NOT ALSO ON REMOTE IS LOST!__
   * If that doesn't work, delete everything in your repo except the .git folder, download from the Github repo page (green button on top right of file window), put everything in the same folder, then `git add *`, `git commit`.
