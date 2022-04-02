import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AutoPlayer extends AbstractPlayer {
    private HashMap<Rank, Long> map;

    AutoPlayer(CardPile left, CardPile right, String name) {
        super(left, right, name);
    }

    private Optional<Rank> findMajority() {
        map = Arrays.stream(cards).map(Card::getRank)
                .collect(Collectors.groupingBy(
                    Function.identity(),
                    HashMap::new,
                    Collectors.counting())
                );


        for (Card card : cards) {
            Rank rank = card.getRank();
            if (map.containsKey(rank)) {
                map.replace(rank, map.get(rank) + 1);
            }
            map.putIfAbsent(rank, 1);
        }
        map.entrySet().stream().max()

    }
    @Override
    protected int selectCard() {

    }

}