package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class RecycleCenter extends Agent {

    private final List<RawPlastic> rePlastics = new ArrayList<>();

  public RecycleCenter(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }

  @Override
  protected void doAction() {
    Optional<PlasticGood> disposedGood = marketPlace.collectDisposedGood();
    disposedGood.ifPresent(
            (good) -> {
                Collection<RawPlastic> mats = good.getBasicMaterials();

                for (RawPlastic mat : mats) {
                    switch (mat.origin) {
                        case NEW -> marketPlace.sellRawPlastic(
                                new RawPlastic(RawPlastic.Origin.RECYCLED));
                        case RECYCLED -> rePlastics.add(mat);
                    }
                }
                int newReFromRecycled = rePlastics.size() / 2; // DIV
                IntStream.range(0, newReFromRecycled).forEach(
                        i -> {
                            rePlastics.remove(0);
                            rePlastics.remove(0);
                            marketPlace.sellRawPlastic(
                                    new RawPlastic(RawPlastic.Origin.RECYCLED));
                        }
                );

            }
    );
  }

    public static void main(String[] args) {
        List<Integer> ints = new ArrayList<>(List.of(1, 2, 3, 4, 5));

        System.out.println(ints);
        System.out.println(ints.remove(0));
        System.out.println(ints);
    }
}
