package concurrency;

import java.util.LinkedList;
import java.util.List;

import concurrency.schedulers.Scheduler;

public class Executor {

	private ConcurrentProgram program;
	private Scheduler scheduler;

	public Executor(ConcurrentProgram program, Scheduler scheduler) {
		this.program = program;
		this.scheduler = scheduler;
	}

	/**
	 * Executes program with respect to scheduler
	 *
	 * @return the final state and history of execution
	 */
	public String execute() {
		List<Integer> history = new LinkedList<Integer>();
		boolean deadlockOccurred = false;

		while(!program.isTerminated() && !deadlockOccurred) {
			try {
				int id = scheduler.chooseThread(program);
				program.step(id);
				history.add(id);
			} catch (DeadlockException e){
				deadlockOccurred = true;
			}
		}

		StringBuilder result = new StringBuilder();
		result.append("Final state: " + program + "\n");
		result.append("History: " + history + "\n");
		result.append("Termination status: "
				+ (deadlockOccurred ? "deadlock" : "graceful") + "\n");
		return result.toString();
	}

//should also overwrite the hashCode method for complete correction
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Executor otherExe) {
			return program.toString().equals(otherExe.program.toString());
		}
		return false;
	}

	// An incorrect attempt at overriding the equals method
	// of Object
	public boolean equals(Executor that) {
		return program.toString() == that.program.toString();
	}

}
