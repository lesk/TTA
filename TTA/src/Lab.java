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

	@Override
	public boolean playCard(Player player) {
		if (super.playCard(player)){
			if (name.equals("Philosophy")) player.playedLabs[0] = this;
			else if (name.equals("Alchemy")) player.playedLabs[1] = this;
			else if (name.equals("Scientific Method")) player.playedLabs[2] = this;
			else if (name.equals("Computers")) player.playedLabs[3] = this;
			return true;
		}
		return false;
	}
}
