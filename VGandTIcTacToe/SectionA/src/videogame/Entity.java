package videogame;

public abstract class Entity {
	
	protected String name;
	protected int lifePoints = 0;

	public Entity(String name, int lifePoints) {
		assert(lifePoints >= 0);
		this.name = name;
		this.lifePoints = lifePoints;
	}

	public final boolean isAlive() {
		/* TODO: Implement as part of Section A Question 2 */
		return lifePoints == 0;
	}

	//returns the points deducted
	public final int reduceLifePoints(int damageAmount) {
		assert damageAmount >= 0;
		int pointsDeducted  = Math.min(damageAmount, lifePoints);
		lifePoints = lifePoints - pointsDeducted;
		return pointsDeducted;
	}
	
	public final int applySpell(SpellCaster spellCaster) {
		return propagateDamage(spellCaster.getStrength());
	}
	
	protected abstract int propagateDamage(int damageAmount);

	public abstract int minimumStrikeToDestroy();
	
}
