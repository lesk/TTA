import java.util.ArrayList;


public abstract class ResourceCard extends BuildingCard {

	public int resources = 0;

	public ResourceCard(int cost, String name, int scienceCost){
		super(cost, name, scienceCost);
	}

	@Override
	public Card afterUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> endTurnGeneration() {
		return new ArrayList<Item>();
	}

	public void addResource(Item blueChips, Item resource) {
		resources += Math.min(resource.count, blueChips.count);
		blueChips.count -= Math.min(resource.count, blueChips.count);
	}

	public abstract void produce(Item blueChips, Item population);
}
