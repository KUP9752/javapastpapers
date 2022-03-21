package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Optional;
import java.util.Set;

public class RoundRobinScheduler implements Scheduler{

    private boolean firstCall = true;
    private int lastThreadId;


    @Override
    public int chooseThread(ConcurrentProgram program)
        throws DeadlockException {

        Set<Integer> ids = program.getEnabledThreadIds();
        if (ids.isEmpty()) {
            throw new DeadlockException();
        }

        if (firstCall) {
//            enabled threads cannot be empty at this point
            firstCall = false;
            lastThreadId = ids.stream().min(Integer::compare).get();
        } else {
            Optional<Integer> optId =  ids.stream().filter(id -> id > lastThreadId)
                    .min(Integer::compare);
            lastThreadId = optId.orElseGet(
                    () -> ids.stream().min(Integer::compare).get());
        }
        return lastThreadId;
    }
}
