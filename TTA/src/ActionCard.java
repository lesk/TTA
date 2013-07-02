import java.util.ArrayList;


public class ActionCard extends Card {

	public ArrayList<Item> actionBenefit;

	public ActionCard(ArrayList<Item> benefit){
		actionBenefit = benefit;
	}

	@Override
	public Item[] preRequisites() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Card afterUsage() {
		// discard after use
		return null;
	}

	@Override
	public ArrayList<Item> endTurnGeneration() {
		return new ArrayList<Item>();
	}

	@Override
	Item[] usageCost() {
		return new Item[] {new Item(Item.itemTypes.whiteAction,1)};
	}

}
