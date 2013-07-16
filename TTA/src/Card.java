import java.util.ArrayList;


public abstract class Card  {

	abstract Item[] preRequisites();
	
	abstract Item[] usageCost();
	
	abstract Card afterUsage();
	
	abstract ArrayList<Item> endTurnGeneration();
	
	@Override
	public String toString() {
		return "Card [workers=" + workers + ", name=" + name + "]";
	}

	protected int workers = 0;
	public String name;
	
	public Card(String name) {
		this.name = name;
	}
	
	public int addWorker() {
		return workers++;
	}
	
	public int removeWorker() {
		if (workers == 0) throw new Error("no worker to remove");
		return workers--;
	}

	public abstract boolean playCard(Player player);
	
}
