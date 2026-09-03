import java.util.Scanner;

public class Dexter {
    public static void main(String[] args) {
        String banner = "DDDD   EEEEE  XX XX  TTTTT  EEEEE  RRRR\n"
                + "D   D  E       X X     T    E      R   R\n"
                + "D   D  EEEE     X      T    EEEE   RRRR\n"
                + "D   D  E        X      T    E      R R\n"
                + "DDDD   EEEEE   X X     T    EEEEE  R  RR\n";
        System.out.println(banner);

        System.out.println("Welcome my fellow big-brainer! What question do you have in mind?");

        String line = "";
        Task[] tasks = new Task[100];
        int itemCount = 0;

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("list")) {
                System.out.println("Here is your list of tasks!");
                // Prints out the list
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(tasks[i]);
                }
            } else if (line.indexOf("mark") == 0) {     // Checks input for mark command
                String numberString = "";
                int index = 4;
                // Converts the number from string to integer
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                int convertedNumber = Integer.parseInt(numberString);
                tasks[convertedNumber - 1].markAsDone();
                System.out.println("Alright! Marked it as done!");
            } else if (line.indexOf("unmark ") == 0) {     //Checks input for unmark command
                String numberString = "";
                int index = 6;
                // Converted the number from string to integer
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                int convertedNumber = Integer.parseInt(numberString);
                tasks[convertedNumber - 1].markAsUndone();
                System.out.println("Alright! I have unchecked the task!");
            } else if (line.indexOf("todo") == 0) {     // Checks input for a new task
                String description = line.substring(5);
                Task newTask = new Task(description);
                // Adds new todo task to the list of tasks
                tasks[itemCount++] = newTask;
                System.out.println("Alright! Added:");
                System.out.println(tasks[itemCount - 1]);
                System.out.println("Now you have " + itemCount + " items");
            } else if (line.indexOf("deadline ") == 0) {     // Checks input for a new task
                int index = line.indexOf("/by ");
                String description = line.substring(9, index - 1);
                index += 3;
                String dueDate = line.substring(index);
                Deadline newTask = new Deadline(description, dueDate);
                // Adds new todo task to the list of tasks
                tasks[itemCount++] = newTask;
                System.out.println("Alright! Added:");
                System.out.println(tasks[itemCount - 1]);
                System.out.println("Now you have " + itemCount + " items");
            } else if (line.indexOf("event ") == 0) {     // Checks input for a new task
                int fromIndex = line.indexOf("/from ");
                String description = line.substring(6, fromIndex - 1);
                fromIndex += 6;
                int toIndex = line.indexOf("/to ");
                String startDate = line.substring(fromIndex,toIndex - 1);
                toIndex += 4;
                String endDate = line.substring(toIndex);
                Event newTask = new Event(description, startDate, endDate);
                // Adds new todo task to the list of tasks
                tasks[itemCount++] = newTask;
                System.out.println("Alright! Added:");
                System.out.println(tasks[itemCount - 1]);
                System.out.println("Now you have " + itemCount + " items");
            }
        }

        System.out.println("See you again soon!");
    }
}
