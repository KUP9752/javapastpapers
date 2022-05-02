package agents;

import domain.Agent;
import goods.RawGlass;
import market.Market;

import static domain.producttypes.RawMaterial.Origin.NEW;

public class RawGlassSupplier extends Agent {

  public RawGlassSupplier(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    market.sellRawGlass(new RawGlass(NEW), this);
  }
}
