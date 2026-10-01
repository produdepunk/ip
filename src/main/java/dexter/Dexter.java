package dexter;

import dexter.classifications.Command;
import dexter.exceptions.EmptyListException;
import dexter.exceptions.InvalidTaskException;
import dexter.exceptions.MissingDescriptionException;
import dexter.exceptions.MissingIndexException;
import dexter.parse.Parser;
import dexter.tasks.Task;
import dexter.data.Storage;
import dexter.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** Coordinates command execution, task storage, and user interaction. */
public class Dexter {
    protected static ArrayList<Task> tasks = new ArrayList<>();
    protected static int itemCount = 0;
    private static Ui ui = new Ui();

    public static void function(String line, Command command) throws MissingIndexException, NumberFormatException, EmptyListException {
        String[] parts = line.split(" ");
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MissingIndexException();
        }
        if (itemCount == 0) {
            throw new EmptyListException();
        }
        int taskNumber;
        try {
            switch (command) {
                case MARK:
                    taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println("Alright! Marked it as done!");
                    break;
                case UNMARK:
                    taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).markAsUndone();
                    System.out.println("Alright! I have unchecked the task!");
                    break;
                case DELETE:
                    taskNumber = Integer.parseInt(parts[1]);
                    if (taskNumber > itemCount) {
                        throw new IndexOutOfBoundsException();
                    }
                    Task deletedTask = tasks.get(itemCount - 1);
                    itemCount--;
                    ui.showTaskDeleted(deletedTask, itemCount);
                    tasks.remove(taskNumber - 1);
            }
        } catch (NumberFormatException e) {
                System.out.println("Oh no! The task number is not valid. Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        ui.showWelcome();
        String line;
        Scanner in = new Scanner(System.in);
        try {
            tasks = storage.readFromDatabase();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
        itemCount = tasks.size();
        while (true) {
            line = in.nextLine();
            try {
                if (line.equals("list")) {
                    ui.showTaskList(tasks);
                } else if (line.startsWith("mark")) {
                    function(line, Command.MARK);
                    storage.writeToDatabase(tasks);
                } else if (line.startsWith("unmark")) {
                    function(line, Command.UNMARK);
                    storage.writeToDatabase(tasks);
                } else if(line.startsWith("delete")) {
                    function(line, Command.DELETE);
                    storage.writeToDatabase(tasks);
                } else if (line.equals("bye")) {
                    ui.showFarewell();
                    return;
                } else {
                    Task newTask = Parser.parseTask(line);
                    tasks.add(newTask);
                    itemCount++;
                    ui.showTaskAdded(newTask, itemCount);
                    storage.writeToDatabase(tasks);

                }
            } catch (MissingDescriptionException e) {
                System.out.println("Oh, I think you may have missed out some details. Can you repeat?");
            } catch (InvalidTaskException e) {
                System.out.println("Sorry but I do not understand. Can you repeat?");
            } catch (MissingIndexException e) {
                System.out.println("Oh no! You need to have a number to indicate the item in the list.");
            } catch (EmptyListException e) {
                System.out.println("Hey! Your list is still empty!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter a value within the list size!");
            } catch (IOException e) {
                System.out.println("Sorry I have trouble ");
            }
        }
    }
}
