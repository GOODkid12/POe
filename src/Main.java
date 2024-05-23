import java.util.*;
import javax.swing.JOptionPane;

public class Main {

    static Map<String, String> users = new HashMap<String, String>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice != 3) {
            String input = JOptionPane.showInputDialog(null, "1. Registration\n2. Login\n3. Quit");
            choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    registerUser(sc);
                    break;
                case 2:
                    loginUser(sc);
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
        sc.close();
    }

    // user input for regestration
    static void registerUser(Scanner sc) {
        String user = JOptionPane.showInputDialog("create a username: ");
        String password = JOptionPane.showInputDialog("create a password: ");
        String name = JOptionPane.showInputDialog("Enter name:");
        String surname = JOptionPane.showInputDialog("Enter surname: ");
        if (users.containsKey(user)) {
            System.out.println("User already exists.");
        } else {
            users.put(user, password);
            System.out.println("Registration Successful.");
        }
        // if password meet checkUsername and checkPassword then displays the following
        if (checkUsername(user)) {
            JOptionPane.showMessageDialog(null, "Username successfully captured");
        } else {
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters long and contain '_' character");
        }
        if (checkPassword(password)) {
            JOptionPane.showMessageDialog(null, "password successfully captured");
        } else {
            JOptionPane.showMessageDialog(null, "password is not correctly formatted, please ensure that the password conatains at least 8 characters, a capital letter, a number and a special character");
        }
    }

    static void loginUser(Scanner sc) {
        String user = JOptionPane.showInputDialog("Enter your username: ");
        String password = JOptionPane.showInputDialog("Enter your password: ");
        if (!users.containsKey(user)) {
            System.out.println("Invalid username or password");
        } else {
            if (users.get(user).equals(password)) {
                System.out.println("Login Successful.");
                JOptionPane.showMessageDialog(null, "Welcome to easy kanban!!");
                JOptionPane.showMessageDialog(null, "Welcome " + user + " , it is great to see you again.");
                int choice = 0;
                while (choice != 3) {
                    String input = JOptionPane.showInputDialog(null, "1. Add Task\n2. Show report\n3. Quit");
                    choice = Integer.parseInt(input);
                    switch (choice) {
                        case 1:
                            addTask(user);
                            break;
                        case 2:
                            showReport();
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid choice");

                    }
                }
            } else {
                System.out.println("Username or password incorrect, please try again");
            }
        }
    }

    // Check if the username meets conditions
    public static boolean checkUsername(String username) {
        return username.length() >= 5 && username.contains("_");
    }

    // Check if the password meets conditions
    public static boolean checkPassword(String password) {
        if (password.length() >= 8 && password.length() <= 10) {
            // Check if the password has special characters
            String specialCharacters = "!@#$%^&*()-_+=~`[]{}|\\:;\"'<>,.?/";
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                if (specialCharacters.contains(String.valueOf(c))) {
                    return true;
                }
            }
        }
        return false;
    }

    static void addTask(String user) {
        String taskDescription;
        do {
            taskDescription = JOptionPane.showInputDialog("Enter the task Description: ");
            if (taskDescription.length() > 50) {
                JOptionPane.showMessageDialog(null, "Please enter a Task Description of less than 50 characters");
            }
        } while (taskDescription.length() > 50);

        JOptionPane.showMessageDialog(null, "Task successfully captured");

        //entering basic information
        String firstName = JOptionPane.showInputDialog("Enter developer name: ");
        String sSurname = JOptionPane.showInputDialog("Enter developer surname: ");
        // this is an array that allows you to choose between the 3
        Scanner scanner = new Scanner(System.in);
        String[] statuses = {"To do", "Doing", "Done"};
        statuses[0] = "To do";
        statuses[1] = "Doing";
        statuses[2] = "Done";
        System.out.println(Arrays.toString(statuses));
        System.out.println("Task added");
    }

    static void showReport() {
    JOptionPane.showMessageDialog(null,"Coming soon.");
    }
}

