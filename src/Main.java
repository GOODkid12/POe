import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Main {

    static Map<String, String> users = new HashMap<String, String>();
    static Map<String, ArrayList<Task>> userTasks = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice=0;
        while(choice!=3) {
            String input = JOptionPane.showInputDialog(null, "1. Registration\n2. Login\n3. Quit");
            choice = Integer.parseInt(input);
            switch(choice){
                case 1: registerUser(sc);
                    break;
                case 2: loginUser(sc);
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
        String user = JOptionPane.showInputDialog("create a username");
        String password = JOptionPane.showInputDialog("create a password");
        String name = JOptionPane.showInputDialog("Enter name");
        String surname = JOptionPane.showInputDialog("Enter surname");
        if (users.containsKey(user)) {
            System.out.println("User already exists");
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

    static void loginUser(Scanner sc){
        String user = JOptionPane.showInputDialog("Enter your username: ");
        String password = JOptionPane.showInputDialog("Enter your password: ");
        if (!users.containsKey(user)) {
            System.out.println("Invalid username or password");
        } else {
            if (users.get(user).equals(password)) {
                System.out.println("Login Successful.");
                JOptionPane.showMessageDialog(null, "Welcome " + user + ", it is great to see you again");
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
        String taskDescription = JOptionPane.showInputDialog("Enter the task Description: ");
        String firstName = JOptionPane.showInputDialog("Enter developer name: ");
        String sSurname = JOptionPane.showInputDialog("Enter developer surname: ");
        String[] statuses = {"To do", "Doing", "Done"};
        String status = (String) JOptionPane.showInputDialog(null, "Select task status");
        ArrayList<Task> tasks = userTasks.get(user);
        tasks.add(new Task(taskDescription, firstName, sSurname, status));
        System.out.println("Task added");
        }
    }
        static void showReport() {
            System.out.println("Tasks for " + user + ": ");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". Description: " + task.getDescription() + ", Developer: " + task.getFirstName() + " " + task.getSSurname() + ", Status: " + task.getStatus());
    }
}
class Task {
private String Description;
private String firstName;
private String sSurname;
private String status;

    public Task(String description, String firstName, String lastName, String status) {
        this.Description = description;
        this.firstName = firstName;
        this.sSurname = lastName;
        this.status = status;
    }
}
public String getDecription() {
return description;
}

