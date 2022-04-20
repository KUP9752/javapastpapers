package videogame;
public class Magician extends Entity implements SpellCaster  {

    public static int damageMultiplier = 2;

    public Magician(String name, int lifePoints) {
        super(name, lifePoints);
    }

    @Override
    public int getStrength() {
        return damageMultiplier * lifePoints;
    }

    @Override
    protected int propagateDamage(int damageAmount) {
        return super.reduceLifePoints(damageAmount);
    }

    @Override
    public int minimumStrikeToDestroy() {
        return lifePoints;
    }

    @Override
    public String toString() {
        return name + "(" + lifePoints + ")";
    }

}
