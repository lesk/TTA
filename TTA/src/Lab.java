import java.util.ArrayList;


public class Lab extends BuildingCard {

	private int sciencePerWorker = 0;

	public Lab(int science, int cost, String name, int scienceCost){
		super(cost, name, scienceCost);
		sciencePerWorker = science;
	}

	@Override
	public Card afterUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Item> endTurnGeneration() {
		
		ArrayList<Item> result = new ArrayList<Item>();
		Item action = new Item(Item.itemTypes.science, sciencePerWorker * workers);
		result.add(action);

		return result;
	}

}
