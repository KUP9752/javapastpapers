package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Set;

public class FewestWaitsScheduler implements Scheduler{
    @Override
    public int chooseThread(ConcurrentProgram program) throws DeadlockException {
        Set<Integer> ids = program.getEnabledThreadIds();
        if (ids.isEmpty()) {
            throw new DeadlockException();
        }


    }
}
