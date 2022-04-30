package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;
import domain.goods.RawPlastic;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Manufacturer extends Agent {

  private final int neededRawPlasticBatches;
  private final List<RawPlastic> mats = new ArrayList<>();

  public Manufacturer(int neededRawPlasticBatches, int thinkingTimeInMillis,
      MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
    this.neededRawPlasticBatches = neededRawPlasticBatches;
    if (neededRawPlasticBatches < 1) throw new IllegalArgumentException();
  }

  @Override
  protected void doAction() {
    while (mats.size() != neededRawPlasticBatches) {
      Optional<RawPlastic> material = marketPlace.buyRawPlastic();
      material.ifPresentOrElse(mats::add, this::think);
    }

    marketPlace.sellPlasticGood(new PlasticGood(mats));

  }
}
