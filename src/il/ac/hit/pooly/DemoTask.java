package il.ac.hit.pooly;

// The DemoTask class represents a demo task that implements the ITask interface.
public class DemoTask implements ITask {
    private String priorityTask;
    private int priority;

    // Constructs a DemoTask object with the specified priority.
    //
    // @param priority the priority of the task
    public DemoTask(String priorityTask, int priority) {
        this.priorityTask = priorityTask;
        setPriority(priority);
    }

    @Override
    public void perform() {
        System.out.println("I'm a task: " + priorityTask);
    }

    @Override
    public void setPriority(int level) {
        this.priority = level;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
}
