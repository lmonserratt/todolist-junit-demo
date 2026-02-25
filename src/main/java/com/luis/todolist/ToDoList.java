package com.luis.todolist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ToDoList
 * Author: Luis Augusto Monserratt Alvarado
 *
 * This class stores and manages to-do items internally.
 * It provides public methods to add, delete, and view items.
 *
 * Notes for unit testing:
 * - getItems() returns an unmodifiable view to safely inspect items in tests.
 * - Methods validate inputs and throw meaningful exceptions.
 */
public class ToDoList {

    // Internal list that holds the to-do items
    private final List<String> items;

    // Constructor initializes the list
    public ToDoList() {
        items = new ArrayList<>();
    }

    /**
     * Adds a new to-do item to the list.
     *
     * @param item the task to be added
     * @throws IllegalArgumentException if item is null or blank
     */
    public void addItem(String item) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Item cannot be empty.");
        }
        items.add(item.trim());
    }

    /**
     * Deletes an item using a 1-based index (user-friendly).
     *
     * @param itemNumber the number of the item to delete (1-based)
     * @throws IndexOutOfBoundsException if itemNumber is invalid
     */
    public void deleteItem(int itemNumber) {
        int index = itemNumber - 1; // convert to 0-based index

        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid item number.");
        }

        items.remove(index);
    }

    /**
     * Returns a read-only view of the items.
     * Useful for unit tests or displaying items without allowing direct modification.
     *
     * @return unmodifiable list of items
     */
    public List<String> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns how many items are currently stored.
     *
     * @return number of items
     */
    public int size() {
        return items.size();
    }

    /**
     * Clears all items (optional helper method).
     * Useful in workflows and can be tested too if desired.
     */
    public void clearAll() {
        items.clear();
    }
}
