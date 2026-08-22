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

        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("list")) {

                for (int i = 0; i < itemCount; i++) {
                    System.out.println(i + 1 + ". " + itemList[i]);
                }
            } else if (!line.equals("bye")) {
                itemList[itemCount++] = line;
                System.out.println("added: " + line + "\n");
            }
        }

        System.out.println("See you again soon!");
    }
}
