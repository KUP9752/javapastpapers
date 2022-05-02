package agents;

import domain.Agent;
import domain.producttypes.RawMaterial;
import goods.RawAluminium;
import goods.RawGlass;
import market.Market;

import java.util.stream.Stream;

import static domain.producttypes.RawMaterial.Origin.NEW;
import static domain.producttypes.RawMaterial.Origin.RECYCLED;

public class RecycleCenter extends Agent {

  public RecycleCenter(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    market.collectDisposedGood().ifPresent(
            (good) -> {
              Stream<RawMaterial> newMats =  good.getConstituentMaterials().stream()
                      .filter(rawMat -> rawMat.origin == NEW);

              newMats.filter(mat -> mat instanceof RawAluminium).forEach(
                      alum -> market.sellRawAluminium(new RawAluminium(RECYCLED), this));

              newMats.filter(mat -> mat instanceof RawGlass).forEach(
                      glass -> market.sellRawGlass(new RawGlass(RECYCLED), this));
            }
    );
  }
}
