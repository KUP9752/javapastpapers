package domain.agents;

import domain.MarketPlace;
import domain.goods.PlasticGood;

public class Consumer extends Agent {

  public Consumer(int thinkingTimeInMillis, MarketPlace marketPlace) {
    super(thinkingTimeInMillis, marketPlace);
  }

  @Override
  protected void doAction() {
    marketPlace.buyPlasticGood().ifPresent(this::waitAndDispose);
//
//            (value) -> {
//              this.think();
//              marketPlace.disposePlasticGood(value);
//            });
  }
  private void waitAndDispose(PlasticGood good) {
    think();
    marketPlace.disposePlasticGood(good);
  }
}
