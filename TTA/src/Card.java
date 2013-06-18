import java.util.ArrayList;


public abstract class Card  {

	abstract Item[] preRequisites();
	
	abstract Item[] usageCost();
	
	abstract Card afterUsage();
	
	abstract ArrayList<Item> endTurnGeneration();
	
	protected int workers = 0;
	
	public int addWorker() {
		return workers++;
	}
	
	public int removeWorker() {
		if (workers == 0) throw new Error("no worker to remove");
		return workers--;
	}
	
}
