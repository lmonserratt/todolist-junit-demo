package com.luis.todolist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ToDoList class using JUnit 5.
 *
 * This test class verifies that the ToDoList methods behave correctly
 * under normal conditions and when invalid input is provided.
 *
 * Concepts demonstrated:
 * - Unit testing individual methods
 * - Test setup using @BeforeEach
 * - Assertions for expected results
 * - Exception testing using assertThrows
 */
public class ToDoListTest {

    // Instance of ToDoList used in each test
    private ToDoList list;

    /**
     * This method runs before each test.
     * It ensures that every test starts with a fresh ToDoList object,
     * keeping tests independent from each other.
     */
    @BeforeEach
    void setUp() {
        list = new ToDoList();
    }

    /**
     * Tests that a valid item is successfully added to the list.
     */
    @Test
    void addItem_shouldAddValidItem() {
        // Add a valid task
        list.addItem("Study Java");

        // Verify the list size increased
        assertEquals(1, list.size());

        // Verify the correct item was stored
        assertEquals("Study Java", list.getItems().get(0));
    }

    /**
     * Tests that extra whitespace is removed when adding an item.
     */
    @Test
    void addItem_shouldTrimWhitespace() {
        // Add an item with leading and trailing spaces
        list.addItem("   Buy milk   ");

        // Verify the item was trimmed before being stored
        assertEquals(1, list.size());
        assertEquals("Buy milk", list.getItems().get(0));
    }

    /**
     * Tests that adding an empty string throws an IllegalArgumentException.
     */
    @Test
    void addItem_shouldThrowExceptionWhenEmpty() {
        // Expect an exception when adding a blank item
        assertThrows(IllegalArgumentException.class,
                () -> list.addItem("   "));
    }

    /**
     * Tests that adding a null item throws an IllegalArgumentException.
     */
    @Test
    void addItem_shouldThrowExceptionWhenNull() {
        // Expect an exception when adding a null item
        assertThrows(IllegalArgumentException.class,
                () -> list.addItem(null));
    }

    /**
     * Tests that deleteItem removes the correct item using a 1-based index.
     */
    @Test
    void deleteItem_shouldRemoveUsingOneBasedIndex() {
        // Add two items to the list
        list.addItem("Task 1");
        list.addItem("Task 2");

        // Remove the first item (1-based index)
        list.deleteItem(1);

        // Verify only one item remains
        assertEquals(1, list.size());

        // Verify the remaining item is correct
        assertEquals("Task 2", list.getItems().get(0));
    }

    /**
     * Tests that deleting an invalid index throws an IndexOutOfBoundsException.
     */
    @Test
    void deleteItem_shouldThrowWhenIndexInvalid() {
        // Add a single item
        list.addItem("Only task");

        // Attempt to delete a non-existing item
        assertThrows(IndexOutOfBoundsException.class,
                () -> list.deleteItem(2));
    }

    /**
     * Tests that clearAll removes all items from the list.
     */
    @Test
    void clearAll_shouldRemoveEverything() {
        // Add multiple items
        list.addItem("One");
        list.addItem("Two");

        // Clear the list
        list.clearAll();

        // Verify the list is empty
        assertEquals(0, list.size());
        assertTrue(list.getItems().isEmpty());
    }
}
