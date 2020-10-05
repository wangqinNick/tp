package seedu.duke.ui;

import seedu.duke.util.Message;
import java.util.Scanner;


public class TextUi {
    private static Scanner in;


    public TextUi(Scanner in) {
        this.in = in;
    }

    public static void showGoodByeMessage() {
        System.out.println(Message.MESSAGE_GOODBYE);
    }

    public static void showWelcomeMessage(){
        System.out.println(Message.MESSAGE_WELCOME);
    }

    /**
     * Trims spacing and checks if input is empty
     *
     * @param rawInputLine full input from user
     * @return true if inputline is a legit command
     */
    private static boolean inputChecker(String rawInputLine){
        return rawInputLine.trim().isEmpty();
    }

    /**
     * gets the User's input command
     *
     * @return the trimmed command input
     */
    public static String getUserCommand(){
        System.out.println("Enter Command: ");
        String userInput = in.nextLine();

        while (inputChecker(userInput)){
            userInput = in.nextLine();
        }

        return userInput;
    }

}
