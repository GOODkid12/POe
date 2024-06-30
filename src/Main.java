import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.regex.*;

public class Main {

    static Map<String, String> users = new HashMap<>();
    // these arrayList store the taskID, the tasksName, developers, tasks duration and taskStatus
    private static List<String> taskIDList = new ArrayList<>();
    private static List<String> taskNameList = new ArrayList<>();
    private static List<String> developerList = new ArrayList<>();
    private static List<Integer> taskDurationList = new ArrayList<>();
    private static List<String> taskStatusList = new ArrayList<>();
    public static int totalHours = 0;

    public static void main(String[] args) {
        // part 1
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
                    if (loginUser(sc)) {
                        taskManager();
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }
        sc.close();
    }

    // part 1
    static void registerUser(Scanner sc) {
        String user;
        while (true) {
            user = JOptionPane.showInputDialog("Create a Username: ");
            if (checkUsername(user)) {
                JOptionPane.showMessageDialog(null, "Username successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.");
            }
        }
        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Create a password: ");
            if (checkPassword(password)) {
                JOptionPane.showMessageDialog(null, "Password successfully captured");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Password is not correctly formatted, please ensure that the password contains at least 8 characters, an uppercase letter, a lowercase letter, and a number.");
            }
        }

        String name = JOptionPane.showInputDialog("Enter name:");
        String surname = JOptionPane.showInputDialog("Enter surname:");

        if (users.containsKey(user)) {
            JOptionPane.showMessageDialog(null, "User already exists.");
        } else {
            users.put(user, password);
            JOptionPane.showMessageDialog(null, "Registration Successful.");
        }
    }

    static boolean loginUser(Scanner sc) {
        String user = JOptionPane.showInputDialog("Enter your username: ");
        String password = JOptionPane.showInputDialog("Enter your password: ");
        if (!users.containsKey(user) || !users.get(user).equals(password)) {
            JOptionPane.showMessageDialog(null, "Invalid username or password.");
        } else {
            JOptionPane.showMessageDialog(null, "Welcome " + user + ", it is great to see you again.");
        }
        return true;
    }

        public static boolean checkUsername (String username){
            return username.length() <= 5 && username.contains("_");
        }

        public static boolean checkPassword (String password){
            return Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,10}$", password);
        }
        static void taskManager () {
            // part 3
            while (true) {
                String[] options = {"Add Task", "Show Report", "Search Task Name", "Search Developer", "Delete Task", "Quit"};
                int choose = JOptionPane.showOptionDialog(null, "Menu", "Task Manager",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                switch (choose) {
                    case 0:
                        addTask();
                        break;
                    case 1:
                        showReport();
                        break;
                    case 2:
                        searchTaskName();
                        break;
                    case 3:
                        searchDeveloper();
                        break;
                    case 4:
                        deleteTask();
                        break;
                    case 5:
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid Choice.");
                }
            }
        }
// part 2
        static void addTask () {
            String enterTaskCount = JOptionPane.showInputDialog("How many tasks would you like to input?");
            int taskCount = Integer.parseInt(enterTaskCount);

            for (int i = 0; i < taskCount; i++) {
                JOptionPane.showMessageDialog(null, "Task " + (i + 1));
                String taskName = JOptionPane.showInputDialog("Enter task name: ");
                String taskDescription;
                do {
                    taskDescription = JOptionPane.showInputDialog("Enter task description (max 50 characters):");
                } while (!checkTaskDescription(taskDescription));

                String firstName = JOptionPane.showInputDialog("Enter developer first name: ");
                String surname = JOptionPane.showInputDialog("Enter developer surname: ");
                String developer = firstName + " " + surname;

                String taskID = createTaskID(taskName, i, firstName, surname);
                taskIDList.add(taskID);
                taskNameList.add(taskName);
                developerList.add(developer);

                String inputTaskDuration = JOptionPane.showInputDialog("Enter task duration in hours: ");
                int taskDuration = Integer.parseInt(inputTaskDuration);
                taskDurationList.add(taskDuration);
                totalHours += taskDuration;

                String taskStatus = chooseTaskStatus();
                taskStatusList.add(taskStatus);

                printTaskDetails(taskStatus, developer, i, taskName, taskDescription, taskID, taskDuration);
            }
        }

        private static boolean checkTaskDescription (String description){
            if (description.length() > 50) {
                JOptionPane.showMessageDialog(null, "Enter a task description with less than 50 characters.");
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Task successfully captured.");
                return true;
            }
        }

        private static String createTaskID (String taskName,int taskNumber, String firstName, String surname){
            String firstTwoLetters = taskName.substring(0, 2).toUpperCase();
            String lastThreeLetters = surname.substring(0, Math.min(surname.length(), 3)).toUpperCase();
            return firstTwoLetters + ":" + taskNumber + ":" + lastThreeLetters;
        }

        public static String chooseTaskStatus () {
            String[] statuses = {"To do", "Doing", "Done"};
            while (true) {
                int statusChoice = JOptionPane.showOptionDialog(null, "Select task status:", "Task Status", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
                switch (statusChoice) {
                    case 0:
                        return "To do";
                    case 1:
                        return "Doing";
                    case 2:
                        return "Done";
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option, try again.");
                }
            }
        }

        static void showReport () {
            StringBuilder report = new StringBuilder("REPORT OF ALL CAPTURED TASKS:\n");

            for (int i = 0; i < taskIDList.size(); i++) {
                report.append("Task ID: ").append(taskIDList.get(i)).append("\n")
                        .append("Task Name: ").append(taskNameList.get(i)).append("\n")
                        .append("Developer: ").append(developerList.get(i)).append("\n")
                        .append("Duration: ").append(taskDurationList.get(i)).append("\n")
                        .append("Status: ").append(taskStatusList.get(i)).append("\n\n");
            }

            JOptionPane.showMessageDialog(null, report.toString());
        }

        private static void printTaskDetails (String taskStatus, String developer,int taskNumber, String
        taskName, String taskDescription, String taskID,int taskDuration){
            String taskDetails = "Task Status: " + taskStatus + "\n"
                    + "Developer: " + developer + "\n"
                    + "Task Number: " + taskNumber + "\n"
                    + "Task Name: " + taskName + "\n"
                    + "Task Description: " + taskDescription + "\n"
                    + "Task ID: " + taskID + "\n"
                    + "Task Duration: " + taskDuration + " hours\n";
            JOptionPane.showMessageDialog(null, taskDetails);
        }

        public static void searchTaskName () {
            String inputTaskName = JOptionPane.showInputDialog("Enter Task Name.");
            int taskIndex = taskNameList.indexOf(inputTaskName);
            if (taskIndex != -1) {
                String taskDetails = "Task Status: " + taskStatusList.get(taskIndex) + "\n"
                        + "Developer: " + developerList.get(taskIndex) + "\n"
                        + "Task Name: " + taskNameList.get(taskIndex) + "\n"
                        + "Task ID: " + taskIDList.get(taskIndex) + "\n"
                        + "Task Duration: " + taskDurationList.get(taskIndex) + " hours\n";
                JOptionPane.showMessageDialog(null, taskDetails);
            } else {
                JOptionPane.showMessageDialog(null, "Task not Found.");
            }
        }

        public static void searchDeveloper () {
            String Developer = JOptionPane.showInputDialog("Search by developer name");
            String report = "Task assigned to " + Developer + ":\n";

            for (int i = 0; i < developerList.size(); i++) {
                if (developerList.get(i).equalsIgnoreCase(Developer)) {
                    report += "-Task Name:" + taskNameList.get(i) + ", Status: " + taskStatusList.get(i) + "\n";

                }
            }
            JOptionPane.showMessageDialog(null, report);
        }

        public static void deleteTask () {
            String taskName = JOptionPane.showInputDialog(null, "Enter task name to delete.");
            int taskIndex = taskNameList.indexOf(taskName);
            if (taskIndex != -1) {
                developerList.remove(taskIndex);
                taskNameList.remove(taskIndex);
                taskIDList.remove(taskIndex);
                taskDurationList.remove(taskIndex);
                taskStatusList.remove(taskIndex);
                JOptionPane.showMessageDialog(null, "Task deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Task not found");

            }
        }
    }