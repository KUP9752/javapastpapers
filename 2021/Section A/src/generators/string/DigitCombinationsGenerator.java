package generators.string;

import java.util.ArrayList;
import java.util.List;

public class DigitCombinationsGenerator implements StringGenerator{

    private final List<String> set = new ArrayList<>(List.of("2", "3", "4", "5"));
    private List<String> current = new ArrayList<>();

    public DigitCombinationsGenerator() {
        current.add("");
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String next() {
        if (!hasNext()) throw new UnsupportedOperationException();
        String next = current.remove(0);
        addStrings(next);
        return next;
    }

    private void addStrings(String base) {
        for (String str : set) {
            current.add(base + str);
        }
    }
}
