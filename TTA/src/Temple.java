import java.util.ArrayList;


public class Temple extends BuildingCard {

	private int culturePerWorker = 0;
	private int happinessPerWorker = 0;

	public Temple(int culture, int happy, int cost, String name, int scienceCost){
		super(cost, name, scienceCost);
		culturePerWorker = culture;
		happinessPerWorker = happy;
	}
	
	@Override
	public Card afterUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> endTurnGeneration() {
		
		ArrayList<Item> result = new ArrayList<Item>();
		Item action = new Item(Item.itemTypes.culture, culturePerWorker * workers);
		result.add(action);

		return result;
	}

	@Override
	public boolean playCard(Player player) {
		if (super.playCard(player)){
			if (name.equals("Religion")) player.playedTemples[0] = this;
			else if (name.equals("Theology")) player.playedTemples[1] = this;
			else if (name.equals("Organized Religion")) player.playedTemples[2] = this;
			else if (name.equals("NoLevelExists")) player.playedTemples[3] = this;
			return true;
		}
		return false;
	}
}
