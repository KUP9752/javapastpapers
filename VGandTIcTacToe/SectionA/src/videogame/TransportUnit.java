package videogame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransportUnit extends Entity {

    public static final int damageDecayMultiplier = 2;
    private final List<Entity> entities;

    public TransportUnit(String name, int lifePoints) {
        super(name, lifePoints);
        this.entities = new ArrayList<>();
    }

    public void add(Entity entity) {
        entities.add(entity);

    }

    @Override
    protected int propagateDamage(int damageAmount) {
        int pointsDeducted = reduceLifePoints(damageAmount);

        Optional<Integer> allPointsDeducted = entities.stream().map(
                entity -> entity.propagateDamage(damageAmount / 2)
        ).reduce(Integer::sum);

        return pointsDeducted + allPointsDeducted.orElse(0);
    }

    @Override
    public int minimumStrikeToDestroy() {
        Optional<Integer> minDamage = entities.stream().map(
                entity -> damageDecayMultiplier * entity.minimumStrikeToDestroy()
        ).reduce(Integer::max);
        return minDamage.orElse(lifePoints);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("(").append(lifePoints).append(")")
                .append(" transporting: [");

        for (Entity entity : entities) {
            sb.append(entity.toString()).append(", ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(),"]");
        return sb.toString();
    }


}
// TODO: complete this class as part of Section A Question 4
