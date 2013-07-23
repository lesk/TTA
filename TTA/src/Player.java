import java.util.ArrayList;
import java.util.Random;


public class Player {
	
	private ArrayList<Card> inHand = new ArrayList<Card>();
	private ArrayList<Card> newActionCards = new ArrayList<Card>();
	
	private ArrayList<Item> government = new ArrayList<Item>();
	
	private ArrayList<Item> workerPool = new ArrayList<Item>();
	
	public ArrayList<Card> playMat = new ArrayList<Card>();
	public Mine[] playedMines = new Mine[4];
	public Farm[] playedFarms = new Farm[4];
	public Lab[] playedLabs = new Lab[4];
	public Temple[] playedTemples = new Temple[4];
	
	private Mine m = new Mine(1,2,"Bronze", 0);
	private Farm f = new Farm(1,2,"Agriculture", 0);
	private Lab l = new Lab(1,3,"Philosophy", 0);
	private Temple t = new Temple(1,1,3,"Religiom", 0);
	
	//debug
	private Farm irr = new Farm(2, 4, "Irrigation", 3);
	
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
		GovernmentCard currentGovernment = new GovernmentCard(4,2,"Despotism");
		playMat.add(currentGovernment);
		government.addAll(currentGovernment.endTurnGeneration());
		
		// Create mine and farm
		m.addWorker();
		m.addWorker();
		playMat.add(m);
		playedMines[0] = m;
		f.addWorker();
		f.addWorker();
		playMat.add(f);
		playedFarms[0] = f;

//		irr.addWorker(); ///debug
//		playMat.add(irr);   /// debug
//		playedFarms[1] = irr;  /// debug

		// Create spare worker
		workerPool.add(new Item(Item.itemTypes.worker,1));
		
		// Create Lab (with worker)
		l.addWorker();
		playMat.add(l);
		playedLabs[0] = l;
		
		// Create Temple (w/o worker)
		playMat.add(t);
		playedTemples[0] = t;

		// Create Infantry
		
		// Create resource and worker areas
		
		// Pick cards upto player number
		
		// End turn production
		doEndTurn();
		
	}
	
	public void doTurn() {
		
//		if (blueChips.count + m.resources + f.resources != 18)
//			System.out.println("breakpoint missing bluchips at start doTurn for player " + playerNumber);
		
		performActions();
		
//		if (blueChips.count + m.resources + f.resources != 18)
//			System.out.println("breakpoint missing bluchips after performActions for player " + playerNumber);

		doEndTurn();

//		if (blueChips.count + m.resources + f.resources != 18)
//			System.out.println("breakpoint missing bluchips after endturn for player " + playerNumber);

		System.out.println(this);
		System.out.println();
	}

	private void doEndTurn() {
		
		ArrayList<Item> income = new ArrayList<Item>();
		
		government = new ArrayList<Item>(); // remove any unused actions
		
		for (Card c:playMat){
			income.addAll(c.endTurnGeneration());
		}
		
		addIncome(income);

//		ArrayList<Mine> mines = new ArrayList<Mine>();
//		ArrayList<Farm> farms = new ArrayList<Farm>();
//		for (Card c:playMat){
//			if (c instanceof ResourceCard){
//				((ResourceCard)c).produce(blueChips,population);
//				if (c instanceof Mine)
//					mines.add((Mine)c);
//				if (c instanceof Farm)
//					farms.add((Farm)c);
//			}
//		}
		Farm.produce(playedFarms,blueChips,population);
		Farm.consume(playedFarms,blueChips,population);
		Mine.produce(playedMines,blueChips,population);
		Mine.consume(playedMines,blueChips,population);

		// Check pop count
		int pop = population.count + workerPool.size();
		int blue = blueChips.count;
		for (Card c:playMat){
			pop += c.workers;
			if (c instanceof ResourceCard){
				blue += ((ResourceCard)c).resources/((ResourceCard)c).resourcePerWorker;
			}
		}
		if (pop != 24)
			System.out.println("pop count for player " + playerNumber + " = " + pop);
		if (blue != 18)
			System.out.println("blue count for player " + playerNumber + " = " + blue);
		
		// Put new action cards into hand
		inHand.addAll(newActionCards);
		newActionCards.clear();
	}

	public void addIncome(ArrayList<Item> income) throws Error {
		for (Item i:income){
			switch (i.value) {
			case science : scienceTotal += i.count;
				break;
			case card: throw new Error("Card generated in endTurn");
			case culture: cultureTotal += i.count;
				break;
			case food: f.addResource(blueChips, i);
				break;
			case military: throw new Error("military generated in endTurn");
			case redAction: government.add(i);
				break;
			case resource: m.addResource(blueChips, i);
				break;
			case whiteAction: government.add(i);
				break;
			case worker: workerPool.add(i); // Is this possible?
				break;
			default:
				break;
			}
		}
	}

	private void performActions() {

		for (Item i:government){
			if (i.value == Item.itemTypes.whiteAction){
				for (int j=0; j<100; j++){
					if (buildWorker()) break;
					if (placeWorker(false, false)) break;
					if (takeCard()) break;
					if (playCard()) break;
			}
		}
		
	}

}

	private boolean playCard() {
		if (rand.nextInt(3) == 1 && inHand.size() > 0){
			Card c = inHand.get(rand.nextInt(inHand.size()));
//			System.out.print("card for player " + playerNumber + " " + c);
			if (c.playCard(this)) {
				inHand.remove(c);
				System.out.print("card for player " + playerNumber + " " + c);
				System.out.println(" played");
				return true;
			}
//			System.out.println(" NOT played");
		}
		return false;
	}

	private boolean takeCard() {
		if (rand.nextInt(3) == 1){
			Card c = CardRow.getCard(rand.nextInt(5));
			if (c != null){
				// Only choose single action card 
				// TODO: allow others if actions available
				if (c instanceof ActionCard)
					newActionCards.add(c);
				else
					inHand.add(c);
				return true;
			}
		}
		return false;
	}

	public boolean placeWorker(boolean urbanOnly, boolean resourceOnly) {

		if (workerPool.size() > 0){
			// TODO : get from higher level mines if possible
			if (m.resources > 0){
				Card c = playMat.get(rand.nextInt(playMat.size()));
				if (c instanceof BuildingCard){
					BuildingCard b = (BuildingCard)c;
					if (urbanOnly && c instanceof ResourceCard) return false;
					if (resourceOnly && !(c instanceof ResourceCard)) return false;
					if (b.costPerWorker <= m.resources &&
							(c instanceof ResourceCard || b.workers < workerLimit )){
						b.addWorker();
						m.resources -= b.costPerWorker;
						blueChips.count += b.costPerWorker;
						workerPool.remove(0);
						System.out.println("Added worker for player " + playerNumber + " to " + b);
//						if (blueChips.count + m.resources + f.resources != 18)
//							System.out.println("breakpoint missing bluchips after placeWorker for player " + playerNumber);
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

	public boolean buildWorker() {
		if (workerPool.size() > 1) return false;
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

		/////todo: need to get from higher level farms if possible????
		if (f.resources >= buildCost && population.count > 0){
			if (rand.nextBoolean()){
				workerPool.add(new Item(Item.itemTypes.worker,1));
				f.resources -= buildCost;
				blueChips.count += buildCost;
				population.count--;
//				if (blueChips.count + m.resources + f.resources != 18)
//					System.out.println("breakpoint missing bluchips after buildWorker for player " + playerNumber);
				System.out.println("Built worker for player " + playerNumber);
				return true;
			}
		}
		// TODO Auto-generated method stub
		return false;
	}
	
}
