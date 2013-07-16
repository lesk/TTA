import java.util.ArrayList;


public class ActionCard extends Card {

	@Override
	public String toString() {
		return "ActionCard [actionBenefit=" + actionBenefit + "]" + super.toString();
	}

	public ArrayList<Item> actionBenefit;

	public ActionCard(ArrayList<Item> benefit, String name){
		super(name);
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

	@Override
	public boolean playCard(Player player) {
		switch (name){
			case "WorkofArt" : player.addIncome(actionBenefit); return true;
			case "RichLand" : return player.placeWorker(false, true);
			case "RevolutionaryIdea" : player.addIncome(actionBenefit); return true;
			case "Patriotism" : break;
			case "MineralDeposits" : player.addIncome(actionBenefit); return true;
			case "IdealBuildingSite" : return player.placeWorker(true, false);
			case "EngineeringGenius" : break;
			case "EfficientUpgrade" : break;
			case "Frugality" : return player.buildWorker();
			case "Breakthrough" : break;
			case "BountifulHarvest" : player.addIncome(actionBenefit); return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

}
