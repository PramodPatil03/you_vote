import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Check {
    public static boolean isInputPresent(String filePath, String userInput) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals(userInput)) {
                    return true;  // Input found in the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;  // Input not found in the file
    }

    public static void main(String[] args) {
        String filePath = "D:\\5th\\CNS\\input.txt";  // Replace with your file path
        String userInput ;  // Replace with user input
        System.out.println("Emter string you want to search\n");
        Scanner sc = new Scanner(System.in);
        userInput = sc.nextLine();
        if (isInputPresent(filePath, userInput)) {
            System.out.println("Input found in the file.");
        } else {
            System.out.println("Input not found in the file.");
        }
    }
}

