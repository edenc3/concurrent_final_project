package il.ac.hit.pooly;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

// The ThreadsPool class represents a thread pool that manages a PriorityBlockingQueue of tasks.
public class ThreadsPool {
    private PriorityBlockingQueue<ITask> tasks;

    /*
     * Constructs a ThreadsPool object with the specified number of threads.
     *
     * @param numOfThreads the number of threads in the pool
     */
    public ThreadsPool(int numOfThreads) {
        this.tasks = new PriorityBlockingQueue<>(numOfThreads, Comparator.comparing(ITask::getPriority));

        for (int i = 0; i < numOfThreads; i++) {
            new Thread(() -> {
                System.out.println("Waiting to poll...");

                try {
                    while (true) {
                        ITask task = tasks.take();
                        System.out.println("Polled by:" + Thread.currentThread().getName());
                        task.perform();
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            }).start();
        }
    }

    /*
     * Submits a task to the thread pool for execution.
     *
     * @param task the task to submit
     */
    public void submit(ITask task) {
        tasks.add(task);
    }
}
