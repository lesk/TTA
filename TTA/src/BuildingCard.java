public abstract class BuildingCard extends Card {

	public int costPerWorker = 0;
	public int science = 0;

	public BuildingCard(int cost, String name, int scienceCost) {
		super(name);
		costPerWorker = cost;
		science = scienceCost;
	}

	@Override
	public Item[] preRequisites() {
		return new Item[] {new Item(Item.itemTypes.science,science)};
	}
	
	@Override
	public Item[] usageCost() {
		return new Item[] {new Item(Item.itemTypes.resource,costPerWorker)};
	}

	@Override
	public boolean playCard(Player player) {
//		if (player.scienceTotal > science){
//			player.playMat.add(this);
//			player.scienceTotal -= science;
//			return true;
//		}
		return false;
	}

}