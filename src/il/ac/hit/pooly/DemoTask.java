package il.ac.hit.pooly;

import il.ac.hit.pooly.ITask;

public class DemoTask implements ITask, Comparable<DemoTask> {
    private String name;
    private int priority;

    public DemoTask(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int level) {
        this.priority = level;
    }

    @Override
    public int compareTo(DemoTask other) {
        return Integer.compare(this.priority, other.getPriority());
    }

    @Override
    public void perform() {
        // Implementation of the task's perform method
    }
}
