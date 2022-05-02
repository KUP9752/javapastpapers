package generators.integer;

public class MissingPrimesGenerator implements IntegerGenerator{

    private Integer current = 1;
    private final Integer SKIP1 = 3;
    private final Integer SKIP2 = 5;


    @Override
    public boolean hasNext() { // infinite number of
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) throw new UnsupportedOperationException();
        boolean mult1 = current % SKIP1 == 0;
        boolean mult2 = current % SKIP2 == 0;

        if (mult1 ^ mult2) {
            current++;
            return next();
        }

        return current++;
    }

    public static void main(String[] args) {
        MissingPrimesGenerator m = new MissingPrimesGenerator();
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
        System.out.println(m.next());
    }
}
