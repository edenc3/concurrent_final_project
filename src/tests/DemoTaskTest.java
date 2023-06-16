package il.ac.hit.pooly;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

import static org.junit.Assert.*;

public class DemoTaskTest {

    private ThreadsPool threadsPool;

    /**
     * Set up the ThreadsPool instance before each test.
     */
    @Before
    public void setUp() {
        threadsPool = new ThreadsPool(3);
    }

    /**
     * Clean up the ThreadsPool instance after each test.
     */
    @After
    public void tearDown() {
        threadsPool = null;
    }

    /**
     * Test for adding a task to the pool.
     * The task should be added successfully.
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
     * Test for polling tasks from the pool in priority order.
     * Tasks with different priorities are submitted,
     * and they should be polled in the expected priority order.
     *
     * @throws InterruptedException if an interrupt occurs while waiting for tasks
     */
    @Test
    public void submit_PollTasksFromPoolInPriorityOrder() throws InterruptedException {
        // Create tasks with different priorities
        ITask task1 = new DemoTask("Priority Task", 3);
        ITask task2 = new DemoTask("Priority Task", 1);
        ITask task3 = new DemoTask("Priority Task", 2);

        // Submit the tasks to the ThreadsPool
        threadsPool.submit(task1);
        threadsPool.submit(task2);
        threadsPool.submit(task3);

        // Get the underlying PriorityBlockingQueue from the ThreadsPool
        PriorityBlockingQueue<ITask> priorityBlockingQueue = threadsPool.getPriorityBlockingQueue();

        // Verify that the tasks are polled from the pool in priority order
        assertEquals("The first task should have the highest priority", task2, priorityBlockingQueue.take());
        assertEquals("The second task should have the second-highest priority", task3, priorityBlockingQueue.take());
        assertEquals("The third task should have the lowest priority", task1, priorityBlockingQueue.take());
    }
}
