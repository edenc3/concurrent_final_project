package il.ac.hit.pooly;

// CR: maybe this should be IRunnable
public class DemoTask implements ITask{

    // CR: remove name
    public String name;
    private int priority;

    DemoTask(String name, int priority) {
        this.name = name;
        setPriority(priority);
    }

    @Override
    public void perform() {
        System.out.println("I'm a task");
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
