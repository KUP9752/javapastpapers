package domain;

import domain.goods.PlasticGood;
import domain.goods.RawPlastic;
import utils.CoarseSafeQueue;

import java.util.Optional;


public class MarketPlaceImpl implements MarketPlace {

  private final boolean DEBUG_MESSAGES = true;

  private final CoarseSafeQueue<RawPlastic> rePlastics = new CoarseSafeQueue<>();
  private final CoarseSafeQueue<RawPlastic> newPlastics = new CoarseSafeQueue<>();
  private final CoarseSafeQueue<PlasticGood> plasticGoods = new CoarseSafeQueue<>();
  private final CoarseSafeQueue<PlasticGood> disposedGoods = new CoarseSafeQueue<>();




  public void sellRawPlastic(RawPlastic plasticItem) {
    if (DEBUG_MESSAGES) {
      System.out
          .println("Thread: " + Thread.currentThread().getId() + " - Sell plastic: " + plasticItem);
    }

    if (plasticItem.origin == RawPlastic.Origin.RECYCLED) rePlastics.push(plasticItem);
    else newPlastics.push(plasticItem);

  }

  public Optional<RawPlastic> buyRawPlastic() {

    if (rePlastics.size() !=0) return rePlastics.pop();
    //else
    return newPlastics.pop();
  }

  public void sellPlasticGood(PlasticGood good) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: " + Thread.currentThread().getId() + " - Sell good: " + good);
    }
    plasticGoods.push(good);
  }

  public Optional<PlasticGood> buyPlasticGood() {
    return plasticGoods.pop();
  }

  public void disposePlasticGood(PlasticGood good) {
    if (DEBUG_MESSAGES) {
      System.out.println("Thread: " + Thread.currentThread().getId() + " - Dispose good: " + good);
    }
    disposedGoods.push(good);
  }

  public Optional<PlasticGood> collectDisposedGood() {
    return disposedGoods.pop();
  }
}
