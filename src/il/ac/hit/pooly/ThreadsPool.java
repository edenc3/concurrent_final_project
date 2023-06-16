package il.ac.hit.pooly;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

// The ThreadsPool class represents a thread pool that manages a PriorityBlockingQueue of tasks.
public class ThreadsPool {
    private PriorityBlockingQueue<ITask> priorityBlockingQueue;

    /*
     * Constructs a ThreadsPool object with the specified number of threads.
     *
     * @param numOfThreads the number of threads in the pool
     */
    public ThreadsPool(int numOfThreads) {
        this.priorityBlockingQueue = new PriorityBlockingQueue<>(numOfThreads, Comparator.comparing(ITask::getPriority));

        for (int i = 0; i < numOfThreads; i++) {
            new Thread(() -> {
                System.out.println("Waiting to poll...");

                try {
                    while (true) {
                        ITask t = priorityBlockingQueue.take();
                        System.out.println("Polled by:" + Thread.currentThread().getName());
                        t.perform();
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
        priorityBlockingQueue.add(task);
    }

    /*
     * Returns the underlying PriorityBlockingQueue used by the thread pool.
     *
     * @return the PriorityBlockingQueue instance
     */
    public PriorityBlockingQueue<ITask> getPriorityBlockingQueue() {
        return priorityBlockingQueue;
    }
}
