package tests;

import il.ac.hit.pooly.ThreadsPool;
import org.junit.Test;

import il.ac.hit.pooly.ITask;
import static org.junit.Assert.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Unit tests for the ThreadsPool class.
 */
public class ThreadsPoolTest {
    // Make sure that all tasks are executed exactly once
    @Test
    public void verifyAllTasksAreDoneOnce() throws InterruptedException {
        class IntegerIncrementerTask implements ITask {
            private AtomicInteger num;

            public IntegerIncrementerTask(AtomicInteger num)
            {
                this.num = num;
            }

            @Override
            public void perform() {
                num.incrementAndGet();
            }

            @Override
            public void setPriority(int level) {}

            @Override
            public int getPriority() {
                return 0;
            }
        }

        var task1 = new AtomicInteger(0);
        var task2 = new AtomicInteger(0);
        var task3 = new AtomicInteger(0);

        var threadPool = new ThreadsPool(3);

        threadPool.submit(new IntegerIncrementerTask(task1));
        threadPool.submit(new IntegerIncrementerTask(task2));
        threadPool.submit(new IntegerIncrementerTask(task3));

        TimeUnit.SECONDS.sleep(3);

        assertEquals(task1.get(), 1);
        assertEquals(task2.get(), 1);
        assertEquals(task3.get(), 1);
    }

    // Make sure that when the thread-pool is busy, new tasks are selected by their priority
    @Test
    public void verifyTaskPriorityOrder() throws InterruptedException {
        class SleepingTask implements ITask {

            @Override
            public void perform() {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void setPriority(int level) {}

            @Override
            public int getPriority() {
                return 0;
            }
        }

        class AddPriorityToQueueTask implements ITask {
            private int priority;
            private BlockingQueue queue;

            public AddPriorityToQueueTask(int priority, BlockingQueue queue) {
                this.priority = priority;
                this.queue = queue;
            }

            @Override
            public void perform() {
                queue.add(priority);
            }

            @Override
            public void setPriority(int level) {
                priority = level;
            }

            @Override
            public int getPriority() {
                return priority;
            }
        }

        var threadPool = new ThreadsPool(1);

        threadPool.submit(new SleepingTask());

        var queue = new LinkedBlockingQueue();

        threadPool.submit(new AddPriorityToQueueTask(3, queue));
        threadPool.submit(new AddPriorityToQueueTask(1, queue));
        threadPool.submit(new AddPriorityToQueueTask(2, queue));

        assertEquals(queue.take(), 1);
        assertEquals(queue.take(), 2);
        assertEquals(queue.take(), 3);
    }
}
