package seedu.duke.command;

/**
 * Indicates the type of user commands and corresponding command results
 * WARNING: the command results a waring message
 * INFORMATIVE: the command results a help message
 * EDIT: the command is a EDIT command
 * NONE: the command is a basic command
 */
public enum PromptType {
    WARNING,
    INFORMATIVE,
    NONE,
    EDIT
}
