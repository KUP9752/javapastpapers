package market;

import datastructures.CoarseStockImpl;
import domain.Agent;
import domain.producttypes.Product;
import domain.producttypes.RawMaterial;
import goods.Laptop;
import goods.RawAluminium;
import goods.RawGlass;
import java.util.Optional;

public class MarketImpl implements Market {

  private final CoarseStockImpl<RawGlass> glassStock = new CoarseStockImpl<>();
  private final CoarseStockImpl<RawGlass> reGlassStock = new CoarseStockImpl<>();

  private final CoarseStockImpl<RawAluminium> alumStock = new CoarseStockImpl<>();
  private final CoarseStockImpl<RawAluminium> reAlumStock = new CoarseStockImpl<>();


  private final CoarseStockImpl<Laptop> laptopStock = new CoarseStockImpl<>();
  private final CoarseStockImpl<Laptop> disposedLaptopStock = new CoarseStockImpl<>();

  private final CoarseStockImpl<Product> disposedProduct = new CoarseStockImpl<>();



  @Override
  public void sellRawAluminium(RawAluminium item, Agent agent) {
    switch (item.origin) {
      case NEW -> alumStock.push(item, agent);
      case RECYCLED -> reAlumStock.push(item, agent);
    }
  }

  @Override
  public Optional<RawAluminium> buyRawAluminium() {
    Optional<RawAluminium> item;
    if (reAlumStock.isEmpty()) {
      item = alumStock.pop();
    } else {
      item = reAlumStock.pop();
    }
    return item;
  }

  @Override
  public void sellRawGlass(RawGlass item, Agent agent) {
    switch (item.origin) {
      case NEW -> glassStock.push(item, agent);
      case RECYCLED -> reGlassStock.push(item, agent);
    }
  }

  @Override
  public Optional<RawGlass> buyRawGlass() {
    Optional<RawGlass> item;
    if (glassStock.isEmpty()) {
      item = glassStock.pop();
    } else {
      item = reGlassStock.pop();
    }
    return item;
  }

  @Override
  public void sellLaptop(Laptop item, Agent agent) {
    laptopStock.push(item, agent);
  }

  @Override
  public Optional<Laptop> buyLaptop() {
    return laptopStock.pop();
  }

  @Override
  public void disposeLaptop(Laptop item, Agent agent) {
    disposedProduct.push(item, agent);
  }

  @Override
  public Optional<Product> collectDisposedGood() {
    return disposedProduct.pop();
  }
}
