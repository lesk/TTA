import java.util.ArrayList;


public class GovernmentCard extends Card {

	private int whiteActions = 0;
	private int redActions = 0;
	
	public GovernmentCard(int white, int red, String name){
		super(name);
		whiteActions = white;
		redActions = red;
	}
	
	@Override
	Item[] preRequisites() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Item[] usageCost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	Card afterUsage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	ArrayList<Item> endTurnGeneration() {
		ArrayList<Item> result = new ArrayList<Item>();
		for (int i=0; i<whiteActions; i++) {
			Item action = new Item(Item.itemTypes.whiteAction, 1);
			result.add(action);
		}
		for (int i=0; i<redActions; i++) {
			Item action = new Item(Item.itemTypes.redAction, 1);
			result.add(action);
		}
		return result;
	}

	@Override
	public boolean playCard(Player player) {
		// TODO Auto-generated method stub
		return false;
	}

}
