import java.util.ArrayList;


public class Temple extends BuildingCard {

	private int culturePerWorker = 0;
	private int happinessPerWorker = 0;

	public Temple(int culture, int happy, int cost){
		super(cost);
		culturePerWorker = culture;
		happinessPerWorker = happy;
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
		
		ArrayList<Item> result = new ArrayList<Item>();
		Item action = new Item(Item.itemTypes.culture, culturePerWorker * workers);
		result.add(action);

		return result;
	}

}
