package com.luis.todolist;

import com.luis.todolist.dao.TaskDAO;
import com.luis.todolist.entity.Task;
import com.luis.todolist.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskDAO taskDAO = new TaskDAO();
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option (1-4): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItemFlow(scanner, taskDAO);
                    break;
                case "2":
                    deleteItemFlow(scanner, taskDAO);
                    break;
                case "3":
                    viewItemsFlow(taskDAO);
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1-4.");
            }
            System.out.println();
        }

        scanner.close();
        HibernateUtil.shutdown();
    }

    private static void printMenu() {
        System.out.println("==== TO-DO LIST MENU ====");
        System.out.println("1) Add a to-do item");
        System.out.println("2) Delete a to-do item");
        System.out.println("3) View to-do items");
        System.out.println("4) Exit");
        System.out.println("=========================");
    }

    private static void addItemFlow(Scanner scanner, TaskDAO taskDAO) {
        System.out.print("Enter the new to-do item: ");
        String item = scanner.nextLine();

        try {
            taskDAO.addTask(item);
            System.out.println("Item added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Could not save item to the database.");
            e.printStackTrace();
        }
    }

    private static void deleteItemFlow(Scanner scanner, TaskDAO taskDAO) {
        if (taskDAO.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }

        viewItemsFlow(taskDAO);
        System.out.print("Enter the item number to delete: ");
        String input = scanner.nextLine();

        try {
            int itemNumber = Integer.parseInt(input);
            taskDAO.deleteByNumber(itemNumber);
            System.out.println("Item deleted successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Could not delete item from the database.");
            e.printStackTrace();
        }
    }

    private static void viewItemsFlow(TaskDAO taskDAO) {
        List<Task> tasks = taskDAO.getAllTasks();

        if (tasks.isEmpty()) {
            System.out.println("Your to-do list is empty.");
            return;
        }

        System.out.println("---- Your To-Do Items ----");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ") " + tasks.get(i).getDescription());
        }
        System.out.println("--------------------------");
    }
}
