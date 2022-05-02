package generators.integer;

public class FixedIntegerSequenceGenerator implements IntegerGenerator{

    private final int FIRST = 0;
    private final int LAST = 30; //excluding
    private int current = FIRST;

    @Override
    public boolean hasNext() {
        return current != LAST;
    }

    @Override
    public Integer next() {
        if (hasNext()) return current++;
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        FixedIntegerSequenceGenerator f =  new FixedIntegerSequenceGenerator();
        System.out.println(f.next());
        System.out.println(f.next());
        System.out.println(f.next());

    }
}
