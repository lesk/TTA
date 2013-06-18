public abstract class BuildingCard extends Card {

	public int costPerWorker = 0;

	public BuildingCard(int cost) {
		super();
		costPerWorker = cost;
	}

	@Override
	public Item[] usageCost() {
		return new Item[] {new Item(Item.itemTypes.resource,costPerWorker)};
	}

}