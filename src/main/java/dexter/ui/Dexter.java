package dexter.ui;

import dexter.classifications.Command;
import dexter.classifications.Response;
import dexter.classifications.Type;
import dexter.exceptions.EmptyListException;
import dexter.exceptions.InvalidTaskException;
import dexter.exceptions.MissingDescriptionException;
import dexter.exceptions.MissingIndexException;
import dexter.tasks.Deadline;
import dexter.tasks.Event;
import dexter.tasks.Task;

import java.util.Scanner;

public class Dexter {
    protected static Task[] tasks = new Task[100];
    protected static int itemCount = 0;
    protected static Task newTask;

    public static void printResponse(Response userInput) {
        switch (userInput) {
            case WELCOME:
                String banner = "DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR\n"
                        + "D   D  E       X X     T    E      R   R\n"
                        + "D   D  EEEE     X      T    EEEE   RRRR\n"
                        + "D   D  E        X      T    E      R R\n"
                        + "DDDD   EEEEE   X X     T    EEEEE  R  RR\n";
                System.out.println(banner);
                System.out.println("Welcome my fellow big-brainer! What question do you have in mind?");
                break;
            case LEAVE:
                System.out.println("See you again soon!");
                break;
            case ADDTASK:
                System.out.println("Alright! Added:");
                System.out.println(tasks[itemCount - 1]);
                System.out.println("Now you have " + itemCount + " items");
                break;
            case LIST:
                System.out.println("Sure! Here is your list.");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(tasks[i]);
                }
                break;
        }
    }

    public static void parseTask(String line, Type type) {
        String description;
        switch(type) {
            case TODO:
                description = line.substring(5);
                newTask = new Task(description);
                break;
            case DEADLINE:
                int index = line.indexOf("/by ");
                description = line.substring(9, index - 1);
                index += 3;
                String dueDate = line.substring(index);
                newTask = new Deadline(description, dueDate);
                break;
            case EVENT:
                int fromIndex = line.indexOf("/from ");
                description = line.substring(6, fromIndex - 1);
                fromIndex += 6;
                int toIndex = line.indexOf("/to ");
                String startDate = line.substring(fromIndex,toIndex - 1);
                toIndex += 4;
                String endDate = line.substring(toIndex);
                newTask = new Event(description, startDate, endDate);
                break;
        }
    }

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
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println("Alright! Marked it as done!");
                    break;
                case UNMARK:
                    taskNumber = Integer.parseInt(parts[1]);
                    tasks[taskNumber - 1].markAsUndone();
                    System.out.println("Alright! I have unchecked the task!");
                    break;
            }
        } catch (NumberFormatException e) {
                System.out.println("Oh no! The task number is not valid. Please enter a valid number.");
        }
    }

    public static Type inputCommand(String line) throws MissingDescriptionException, InvalidTaskException {
        if (line == null || line.isBlank()) {
            throw new InvalidTaskException();
        }
        String[] parts = line.split(" ");
        Type type;
        switch (parts[0]) {
            case "todo":
                type = Type.TODO;
                break;
            case "deadline":
                type = Type.DEADLINE;
                break;
            case "event":
                type = Type.EVENT;
                break;
            default:
                throw new InvalidTaskException();
        }
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MissingDescriptionException();
        }
        return type;
    }

    public static void main(String[] args) {
        printResponse(Response.WELCOME);
        String line = "";
        Scanner in = new Scanner(System.in);

        while (!line.equals("bye")) {
            line = in.nextLine();
            try {
                if (line.equals("list")) {
                    printResponse(Response.LIST);
                } else if (line.startsWith("mark")) {
                    function(line, Command.MARK);
                } else if (line.startsWith("unmark")) {
                    function(line, Command.UNMARK);
                } else {
                    Type type = inputCommand(line);
                    parseTask(line, type);
                    tasks[itemCount++] = newTask;
                    printResponse(Response.ADDTASK);
                }
            } catch (MissingDescriptionException e) {
                System.out.println("Oh, I think you may have missed out some details. Can you repeat?");
            } catch (InvalidTaskException e) {
                System.out.println("Sorry but I do not understand. Can you repeat?");
            } catch (MissingIndexException e) {
                System.out.println("Oh no! You need to have a number to mark the indicated item in the list.");
            } catch (EmptyListException e) {
                System.out.println("Hey! Your list is still empty!");
            }
        }
        printResponse(Response.LEAVE);
    }
}
