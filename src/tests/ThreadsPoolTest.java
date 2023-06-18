package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import il.ac.hit.pooly.ITask;
import il.ac.hit.pooly.ThreadsPool;
import il.ac.hit.pooly.DemoTask;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Unit tests for the ThreadsPool class.
 */
public class ThreadsPoolTest {
    private ThreadsPool threadsPool;

    /**
     * Set up the ThreadsPool instance before each test.
     */
    @Before
    public void setUp() {
        threadsPool = new ThreadsPool(5);
    }

    /**
     * Clean up the ThreadsPool instance after each test.
     */
    @After
    public void tearDown() {
        threadsPool = null;
    }

    /**
     * Test for submitting a task to the thread pool.
     * The task should be added to the pool successfully.
     */
    @Test
    public void submit_AddTaskToPool() {
        // Create a new task
        ITask task = new DemoTask("Priority Task", 1);

        // Submit the task to the ThreadsPool
        threadsPool.submit(task);

        // Get the underlying PriorityBlockingQueue from the ThreadsPool
        PriorityBlockingQueue<ITask> priorityBlockingQueue = threadsPool.getPriorityBlockingQueue();

        // Check if the task exists in the PriorityBlockingQueue
        assertTrue("The task should be added to the pool", priorityBlockingQueue.contains(task));
    }


    /**
     * Test for performing a task directly.
     * The task's perform() method should execute without errors.
     */
    @Test
    public void perform_PerformTask() {
        // Create a sample task for testing
        ITask task = new DemoTask("Perform Task", 2);

        // Perform the task directly
        task.perform();

        // Add your assertions here to verify the behavior of the task's perform() method
        // For example:
        // - Check if the expected output is generated

        // Example assertion: Check if the task performs correctly
        // You may need to redirect the output to verify it in the test
        // For simplicity, let's assume the output is printed to the console,
        // and we capture it for testing
        // Here's a sample code snippet to redirect the output:
        // ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        // System.setOut(new PrintStream(outContent));
        // task.perform();
        // String expectedOutput = "Expected output";
        // assertEquals(expectedOutput, outContent.toString().trim());

        // Make sure to reset the output redirection after the test
        // System.setOut(System.out);
    }

    /**
     * Test for setting the priority of a task.
     * The task's priority should be set correctly.
     */
    @Test
    public void setPriority_SetTaskPriority() {
        // Create a sample task for testing
        ITask task = new DemoTask("Priority Task", 3);

        // Set the priority of the task
        task.setPriority(4);

        // Add your assertions here to verify the behavior of the task's setPriority() method
        // For example:
        // - Check if the priority is set correctly

        // Example assertion: Check if the priority is set correctly
        int expectedPriority = 4;
        assertEquals(expectedPriority, task.getPriority());
    }

    /**
     * Test for getting the priority of a task.
     * The task's priority should be returned correctly.
     */
    @Test
    public void getPriority_GetTaskPriority() {
        // Create a sample task for testing
        ITask task = new DemoTask("Priority Task", 3);

        // Get the priority of the task
        int priority = task.getPriority();

        // Add your assertions here to verify the behavior of the task's getPriority() method
        // For example:
        // - Check if the expected priority is returned

        // Example assertion: Check if the correct priority is returned
        int expectedPriority = 3;
        assertEquals(expectedPriority, priority);
    }

}
