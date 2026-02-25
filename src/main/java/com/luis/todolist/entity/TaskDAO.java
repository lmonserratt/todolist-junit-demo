package com.luis.todolist.dao;

import com.luis.todolist.entity.Task;
import com.luis.todolist.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * TaskDAO
 * Performs CRUD operations using Hibernate.
 */
public class TaskDAO {

    public void addTask(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Item cannot be empty.");
        }

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(new Task(description.trim()));
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public List<Task> getAllTasks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Task ORDER BY id ASC", Task.class).getResultList();
        }
    }

    // Deletes by the 1-based number shown in the UI
    public void deleteByNumber(int itemNumber) {
        List<Task> tasks = getAllTasks();
        int index = itemNumber - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid item number.");
        }

        Integer id = tasks.get(index).getId();

        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Task task = session.get(Task.class, id);
            if (task != null) {
                session.remove(task);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }
    }

    public boolean isEmpty() {
        return getAllTasks().isEmpty();
    }
}
