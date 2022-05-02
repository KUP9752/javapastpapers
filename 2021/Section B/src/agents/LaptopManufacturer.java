package agents;

import domain.Agent;
import goods.Laptop;
import goods.RawAluminium;
import goods.RawGlass;
import market.Market;

import java.util.Optional;

public class LaptopManufacturer extends Agent {

  public LaptopManufacturer(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    Optional<RawAluminium> al1 = Optional.empty();
    Optional<RawAluminium> al2 = Optional.empty();
    Optional<RawGlass> gl = Optional.empty();

    while (al1.isEmpty()) {
      al1 = market.buyRawAluminium();
    }
    while (al2.isEmpty()) {
      al2 = market.buyRawAluminium();
    }
    while (gl.isEmpty()) {
      gl = market.buyRawGlass();
    }

    market.sellLaptop(new Laptop(gl.get(), al1.get(), al2.get()), this);



  }
}
