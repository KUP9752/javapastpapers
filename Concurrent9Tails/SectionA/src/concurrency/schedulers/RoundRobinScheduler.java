package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RoundRobinScheduler implements Scheduler{
    private boolean firstCall = true;
    private int lastId;

    protected int schedulingAlgorithm(ConcurrentProgram program, Set<Integer> ids) {
        if (firstCall) {
            firstCall = false;
            lastId = ids.stream().reduce(Integer::min).get();
        } else {
            Optional<Integer> optId = ids.stream().filter(id -> id > lastId)
                    .min(Integer::compare);

            lastId = optId.orElse(ids.stream().min(Integer::compare).get());
        }
        return lastId;

    }


    @Override
    public int chooseThread(ConcurrentProgram program) throws DeadlockException {
        Set<Integer> ids = program.getEnabledThreadIds();
        if (ids.isEmpty()) throw new DeadlockException();

        return schedulingAlgorithm(program, ids);

    }
}

