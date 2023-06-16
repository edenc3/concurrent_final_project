package il.ac.hit.pooly;

/**
 * The ITask interface represents a task that can be performed.
 */
public interface ITask {
    /**
     * Performs the task.
     */
    public abstract void perform();

    /**
     * Sets the priority level of the task.
     *
     * @param level the priority level to set
     */
    public abstract void setPriority(int level);

    /**
     * Retrieves the priority level of the task.
     *
     * @return the priority level of the task
     */
    public abstract int getPriority();
}
