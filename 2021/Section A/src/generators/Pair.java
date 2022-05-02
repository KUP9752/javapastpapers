package generators;

final public class Pair<S, T> {
    private final S first;
    private final T second;

    public Pair(S first, T second) {
        this.first = first;
        this.second = second;
    }

    public S getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return (first.hashCode() * second.hashCode()) % 101;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pair otherPair) {
            return first == otherPair.getFirst()
                    && second == otherPair.getSecond();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(first).append(second).append(">");
        return sb.toString();
    }
}
