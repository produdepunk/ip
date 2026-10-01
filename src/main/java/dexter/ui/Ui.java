package dexter.ui;

import dexter.tasks.Task;

import java.util.List;

/** Handles messages displayed to the user by the Dexter application. */
public class Ui {

    public void showWelcome() {
        String banner = "DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR\n"
                + "D   D  E       X X     T    E      R   R\n"
                + "D   D  EEEE     X      T    EEEE   RRRR\n"
                + "D   D  E        X      T    E      R R\n"
                + "DDDD   EEEEE   X X     T    EEEEE  R  RR\n";
        System.out.println(banner);
        System.out.println("Welcome my fellow big-brainer! What question do you have in mind?");
    }

    public void showFarewell() {
        System.out.println("See you again soon!");
    }

    public void showTaskAdded(Task task, int itemCount) {
        System.out.println("Alright! Added:");
        System.out.println(task);
        System.out.println("Now you have " + itemCount + " items");
    }

    public void showTaskDeleted(Task task, int itemCount) {
        System.out.println("Ok! Removed:");
        System.out.println(task);
        System.out.println("Now you have " + itemCount + " items");
    }

    public void showTaskList(List<Task> tasks) {
        System.out.println("Sure! Here is your list.");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
