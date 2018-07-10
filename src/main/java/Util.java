import java.util.Scanner;

public class Util {

    public static boolean invalid(String string){
        if(string.isEmpty() || string.matches(".*[^a-zA-Z0-9,.\\s].*")){ //find all alphanumeric (a-ZA-Z0-9), and spaces (\s), ^ negates finding them, meaning find everything else apart from letters, spaces, and numbers
            return true;
        }
        return false;
    }

    public static String input(String message, String errorCode){
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        String input = scanner.nextLine();

        while(invalid(input)){
            System.out.println(errorCode);
            input = scanner.nextLine();
        }

        return input;

    }
}