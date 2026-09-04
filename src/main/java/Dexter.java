import java.util.Scanner;

public class Dexter {
    protected static Task[] tasks = new Task[100];
    protected static int itemCount = 0;
    protected static Task newTask;

    public static void printResponse(Response userInput) {
        switch(userInput) {
            case WELCOME:
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

    public static void function(String line, Command command) {
        String numberString = "";
        int index;
        int taskNumber;
        switch(command) {
            case MARK:
                index = 4;
                // Converts the number from string to integer
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                taskNumber = Integer.parseInt(numberString);
                tasks[taskNumber - 1].markAsDone();
                System.out.println("Alright! Marked it as done!");
                break;
            case UNMARK:
                index = 6;
                // Converted the number from string to integer
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                taskNumber = Integer.parseInt(numberString);
                tasks[taskNumber - 1].markAsUndone();
                System.out.println("Alright! I have unchecked the task!");
                break;
        }
    }

    public static void main(String[] args) {
        String banner = "DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR\n"
                + "D   D  E       X X     T    E      R   R\n"
                + "D   D  EEEE     X      T    EEEE   RRRR\n"
                + "D   D  E        X      T    E      R R\n"
                + "DDDD   EEEEE   X X     T    EEEEE  R  RR\n";
        System.out.println(banner);

        printResponse(Response.WELCOME);
        String line = "";
        Scanner in = new Scanner(System.in);

        while (!line.equals("bye")) {
            line = in.nextLine();
            if (line.equals("list")) {
                // Prints out the list
                printResponse(Response.LIST);
            } else if (line.indexOf("mark ") == 0) {     // Checks input for mark command
                function(line, Command.MARK);
            } else if (line.indexOf("unmark ") == 0) {     //Checks input for unmark command
                function(line, Command.UNMARK);
            } else if (line.indexOf("todo ") == 0) {     // Checks input for a new todo task
                parseTask(line, Type.TODO);
                // Adds new todo task to the list of tasks
                tasks[itemCount++] = newTask;
                printResponse(Response.ADDTASK);
            } else if (line.indexOf("deadline ") == 0) {     // Checks input for a new deadline task
                parseTask(line, Type.DEADLINE);
                // Adds new deadline task to the list of tasks
                tasks[itemCount++] = newTask;
                printResponse(Response.ADDTASK);
            } else if (line.indexOf("event ") == 0) {     // Checks input for a new event task
                parseTask(line, Type.EVENT);
                // Adds new event task to the list of tasks
                tasks[itemCount++] = newTask;
                printResponse(Response.ADDTASK);
            }
        }

        printResponse(Response.LEAVE);
    }
}
