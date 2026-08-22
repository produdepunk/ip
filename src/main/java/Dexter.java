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
        while (!line.equals("bye")) {
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            System.out.println(line);
        }

        System.out.println("See you again soon!");
    }
}
