public class Location {
  private int x = 0;
  private int y = 0;

  public Location(int var1, int var2) {
    this.x = var1;
    this.y = var2;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public double distanceTo(Location var1) {
    int var2 = var1.x - this.x;
    int var3 = var1.y - this.y;
    return Math.sqrt((double)(var2 * var2 + var3 * var3));
  }
}
