package concurrency.schedulers;

import concurrency.ConcurrentProgram;
import concurrency.DeadlockException;
import concurrency.statements.Stmt;
import concurrency.statements.WaitStmt;

import java.util.*;
import java.util.stream.Collectors;

public class FewestWaitsScheduler extends RoundRobinScheduler{
    private boolean firstCall = true;
    private int lastId;

    @Override
    protected int schedulingAlgorithm(ConcurrentProgram program, Set<Integer> ids) {
        final Map<Integer, Integer> waitToId = new HashMap<>();

        for (int id : ids) {
            int remWaits = program.remainingStatements(id)
                    .stream().filter(stmt -> stmt instanceof WaitStmt)
                    .toList().size();
            if (waitToId.containsKey(remWaits)) {
                if (id < waitToId.get(remWaits)) {
                    waitToId.replace(remWaits, id);
                }
            } else {
                waitToId.put(remWaits, id);
            }
        }
        //cannot be empty
        int minWait = waitToId.keySet().stream().min(Integer::compare).get();
        return waitToId.get(minWait);
    }
}