package com.luis.todolist;

/**
 * Main
 * Author: Luis Augusto Monserratt Alvarado
 *
 * This class serves as the entry point of the ToDoList application.
 * It demonstrates how the ToDoList class works during normal execution.
 *
 * Note:
 * Unit testing is handled separately using JUnit 5.
 * This class exists only to show the application in action.
 */
public class Main {

    /**
     * Application entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Create a new ToDoList instance
        ToDoList list = new ToDoList();

        System.out.println("=== ToDoList Application Demo ===");

        // Add tasks to the list
        list.addItem("Study for CEN 4802");
        list.addItem("Run JUnit tests");
        list.addItem("Submit Assignment 3");

        // Display current tasks
        System.out.println("Current tasks (" + list.size() + "): " + list.getItems());

        // Remove a task using a 1-based index
        list.deleteItem(2);

        // Display tasks after deletion
        System.out.println("After deleting task #2: " + list.getItems());

        System.out.println("Application finished successfully.");
    }
}
