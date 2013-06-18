import java.util.ArrayList;


public class ResourceCard extends BuildingCard {

	public int resources = 0;

	public ResourceCard(int cost){
		super(cost);
	}

	@Override
	public Item[] preRequisites() {
		// TODO Auto-generated method stub
		return null;
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

}
