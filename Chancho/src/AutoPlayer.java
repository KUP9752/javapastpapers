import java.util.*;
import java.util.stream.Collectors;

public class AutoPlayer extends AbstractPlayer {
    private HashMap<Rank, Integer> map;

    AutoPlayer(CardPile left, CardPile right, String name) {
        super(left, right, name);
    }

    private Optional<Rank> findMajority() {

        for (Card card : cards) {
            Rank rank = card.getRank();
            if (map.containsKey(rank)) {
                map.replace(rank, map.get(rank) + 1);
            }
            map.putIfAbsent(rank, 1);
        }
        Integer max = map.values().stream().max(Integer::compare).get();

        if (max > 1) { //there is some majority
            List<Rank> majorityRanks = map.entrySet().stream().filter(e -> Objects.equals(e.getValue(), max))
                    .map(Map.Entry::getKey).toList();
            Collections.shuffle(majorityRanks);
            return Optional.of(majorityRanks.get(0)); // randomly selects a majority
        } else {
            return Optional.empty();
        }
    }

    private int randomIndex(Optional<Rank> toKeep){
        Rank toDiscard;
        List<Rank> remRanks;
        if (toKeep.isPresent()) {
            remRanks = new ArrayList<Rank>(map.keySet().stream()
                    .filter(key -> !key.equals(toKeep.get()))
                    .toList());
        } else {
            remRanks = map.keySet().stream().toList();
        }
        // nogt present = no majority

        Collections.shuffle(remRanks);
        toDiscard = remRanks.get(0);
        return Arrays.stream(cards).map(Card::getRank).toList()
                .lastIndexOf(toDiscard);
        // throw one of the same ranks, suit is not important



    }

    @Override
    protected int selectCard() {
        return randomIndex(findMajority());

    }

}