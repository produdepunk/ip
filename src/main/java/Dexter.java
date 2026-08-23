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
        String[] itemList = new String[100];
        int itemCount = 0;
        String[] completionStatus = new String[100];

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("list")) {

                System.out.println("Here is your list of tasks!");
                for (int i = 0; i < itemCount; i++) {
                    System.out.println(i + 1 + ".[" + completionStatus[i] + "] " + itemList[i]);
                }
            } else if (line.indexOf("mark") == 0) {
                String numberString = "";
                int index = 4;
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                int numberInt = Integer.parseInt(numberString);
                completionStatus[numberInt - 1] = "X";
                System.out.println("Alright! Marked it as done!");
            } else if (line.indexOf("Unmark") == 0) {
                String numberString = "";
                int index = 4;
                while (index < line.length()) {
                    if (line.charAt(index) >= '0' && line.charAt(index) < '9') {
                        numberString += line.charAt(index);
                    }
                    index++;
                }
                int numberInt = Integer.parseInt(numberString);
                completionStatus[numberInt - 1] = " ";
                System.out.println("Alright! I have unchecked the task!");
            } else if (!line.equals("bye")) {
                itemList[itemCount] = line;
                completionStatus[itemCount] = " ";
                itemCount++;
                System.out.println("added: " + line);
            }
        }

        System.out.println("See you again soon!");
    }
}
