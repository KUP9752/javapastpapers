package agents;

import domain.Agent;
import market.Market;

public class Consumer extends Agent {

  public Consumer(int thinkingTimeInMillis, Market market) {
    super(thinkingTimeInMillis, market);
  }

  @Override
  public void doAction() {
    market.buyLaptop().ifPresent(
            (laptop) -> {
            think();
            market.disposeLaptop(laptop, this);
    });
  }
}
