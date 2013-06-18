import java.util.ArrayList;
import java.util.Random;


public class Player {
	
	private ArrayList<Card> inHand = new ArrayList<Card>();
	
	private ArrayList<Item> government = new ArrayList<Item>();
	
	private ArrayList<Item> workerPool = new ArrayList<Item>();
	
	private ArrayList<Card> playMat = new ArrayList<Card>();
	
	private Mine m = new Mine(1,3);
	private Farm f = new Farm(1,3);
	private Lab l = new Lab(1,3);
	private Temple t = new Temple(1,1,3);
	
	private Item population = new Item(Item.itemTypes.worker,18);
	private Item blueChips = new Item(Item.itemTypes.resource,18);
	
	private Random rand = new Random();
		
	public int scienceTotal = 0;
	
	public int cultureTotal = 0;
	
	public int militaryTotal = 0;

	private int workerLimit = 2;
	
	private int playerNumber = 0;
	
	public Player(int initialActions) {
		// Do first turn setup
		
		playerNumber = initialActions;
		
		// Define despot government
		GovernmentCard currentGovernment = new GovernmentCard(4,2);
		playMat.add(currentGovernment);
		government.addAll(currentGovernment.endTurnGeneration());
		
		// Create mine and farm
		m.addWorker();
		m.addWorker();
		playMat.add(m);
		f.addWorker();
		f.addWorker();
		playMat.add(f);
		
		// Create spare worker
		workerPool.add(new Item(Item.itemTypes.worker,1));
		
		// Create Lab (with worker)
		l.addWorker();
		playMat.add(l);
		
		// Create Temple (w/o worker)
		playMat.add(t);

		// Create Infantry
		
		// Create resource and worker areas
		
		// Pick cards upto player number
		
		// End turn production
		doEndTurn();
		
	}
	
	public void doTurn() {
		
		if (blueChips.count + m.resources + f.resources != 18)
			System.out.println("breakpoint missing bluchips at start doTurn for player " + playerNumber);
		
		performActions();
		
		if (blueChips.count + m.resources + f.resources != 18)
			System.out.println("breakpoint missing bluchips after performActions for player " + playerNumber);

		doEndTurn();

		if (blueChips.count + m.resources + f.resources != 18)
			System.out.println("breakpoint missing bluchips after endturn for player " + playerNumber);
}

	private void doEndTurn() {
		
		ArrayList<Item> income = new ArrayList<Item>();
		
		government = new ArrayList<Item>(); // remove any unused actions
		
		for (Card c:playMat){
			income.addAll(c.endTurnGeneration());
		}
		
		for (Item i:income){
			switch (i.value) {
			case science : scienceTotal += i.count;
				break;
			case card: throw new Error("Card generated in endTurn");
			case culture: cultureTotal += i.count;
				break;
			case food: throw new Error("food generated in endTurn");
			case military: throw new Error("military generated in endTurn");
			case redAction: government.add(i);
				break;
			case resource: throw new Error("resource generated in endTurn");
			case whiteAction: government.add(i);
				break;
			case worker: workerPool.add(i); // Is this possible?
				break;
			default:
				break;
			}
		}
		
		f.produce(blueChips,population);
		m.produce(blueChips);

		// Check pop count
		int pop = population.count + workerPool.size();
		for (Card c:playMat){
			pop += c.workers;
		}
		System.out.println("pop count for player " + playerNumber + " = " + pop);
	}

	private void performActions() {

		for (Item i:government){
			if (i.value == Item.itemTypes.whiteAction){
				for (int j=0; j<100; j++){
					if (buildWorker()) break;
					if (placeWorker()) break;
			}
		}
		
	}

}

	private boolean placeWorker() {

		if (workerPool.size() > 0){
			if (m.resources > 0){
				Card c = playMat.get(rand.nextInt(playMat.size()));
				if (c instanceof BuildingCard){
					BuildingCard b = (BuildingCard)c;
					if (b.costPerWorker <= m.resources &&
							(c instanceof ResourceCard || b.workers < workerLimit )){
						b.addWorker();
						m.resources -= b.costPerWorker;
						blueChips.count += b.costPerWorker;
						workerPool.remove(0);
						if (blueChips.count + m.resources + f.resources != 18)
							System.out.println("breakpoint missing bluchips after placeWorker for player " + playerNumber);
						return true;
					}
				}
			}
		}
		return false;
	}

@Override
	public String toString() {
		return "Player [workerPool=" + workerPool.size() + ", playerNumber=" + playerNumber
				+ ", m=" + m.resources + "|" + m.workers + ", f=" + f.resources + "|" + f.workers 
				+ ", l=" + l.workers + ", t=" + t.workers
				+ ", population=" + population.count + ", blueChips=" + blueChips.count
				+ ", scienceTotal=" + scienceTotal
				+ ", cultureTotal=" + cultureTotal + ", militaryTotal="
				+ militaryTotal + ", workerLimit=" + workerLimit
				+ ", playMat=" + playMat + "]";
	}

	//	@Override
	public String toStringOld() {
		return "Player [workerPool=" + workerPool.size() + ", m=" + m.resources + ", f=" + f.resources
				+ ", mineworkers=" + m.workers + ", farmWorkers=" + f.workers
				+ ", labworkers=" + l.workers + ", templeWorkers=" + t.workers
				+ ", scienceTotal=" + scienceTotal + ", cultureTotal="
				+ cultureTotal + ", militaryTotal=" + militaryTotal + "]";
	}

	private boolean buildWorker() {
		int buildCost = 2;
		if (population.count <= 4){
			buildCost = 7;
		} else if (population.count <= 8){
			buildCost = 5;
		} else if (population.count <= 12){
			buildCost = 4;
		} else if (population.count <= 16){
			buildCost = 3;
		}

		if (f.resources >= buildCost && population.count > 0){
			if (rand.nextBoolean()){
				workerPool.add(new Item(Item.itemTypes.worker,1));
				f.resources -= buildCost;
				blueChips.count += buildCost;
				population.count--;
				if (blueChips.count + m.resources + f.resources != 18)
					System.out.println("breakpoint missing bluchips after buildWorker for player " + playerNumber);
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}