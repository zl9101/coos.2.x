package top.coos.cron.task;

/**
 * {@link Runnable} 的 {@link Task}包装

 *
 */
public class RunnableTask implements Task{
	private Runnable runnable;
	
	public RunnableTask(Runnable runnable) {
		this.runnable = runnable;
	}

	@Override
	public void execute() {
		runnable.run();
	}
}
