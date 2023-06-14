package il.ac.hit.pooly;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class ThreadsPool {
    private PriorityBlockingQueue<ITask> priorityBlockingQueue;

    ThreadsPool(int numOfThreads) {
        this.priorityBlockingQueue = new PriorityBlockingQueue(numOfThreads, Comparator.comparing(ITask::getPriority));

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

    public void submit(ITask task) {
        priorityBlockingQueue.add(task);
    }
}
