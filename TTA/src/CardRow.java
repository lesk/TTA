import java.util.ArrayList;
import java.util.Random;


public class CardRow {

	public Card[] oneAction = new Card[5];
	public Card[] twoAction = new Card[4];
	public Card[] threeAction = new Card[4];
	
	private ArrayList<Card> Age1 = new ArrayList<Card>();
	private int numPlayers;
	
	private static int[] ProdCards = {2,2,3};
	private static Random rand = new Random();
	
	public void initAge1(){
		ArrayList<Item> WorkofArt = new ArrayList<Item>();
		WorkofArt.add(new Item(Item.itemTypes.culture,5));
		Age1.add(new ActionCard(WorkofArt));
		ArrayList<Item> RichLand = new ArrayList<Item>();
		RichLand.add(new Item(Item.itemTypes.resource,2)); // for new mine or farm
		Age1.add(new ActionCard(RichLand));
		ArrayList<Item> RevolutionaryIdea = new ArrayList<Item>();
		RevolutionaryIdea.add(new Item(Item.itemTypes.science,2));
		Age1.add(new ActionCard(RevolutionaryIdea));
		ArrayList<Item> Patriotism = new ArrayList<Item>();
		Patriotism.add(new Item(Item.itemTypes.resource,2)); //  for military
		Age1.add(new ActionCard(Patriotism));
		ArrayList<Item> MineralDeposits = new ArrayList<Item>();
		MineralDeposits.add(new Item(Item.itemTypes.resource,2));
		Age1.add(new ActionCard(MineralDeposits));
		Age1.add(new ActionCard(MineralDeposits));
		ArrayList<Item> IdealBuildingSite = new ArrayList<Item>();
		IdealBuildingSite.add(new Item(Item.itemTypes.resource,2)); // for new building
		Age1.add(new ActionCard(IdealBuildingSite));
		ArrayList<Item> Frugality = new ArrayList<Item>();
		Frugality.add(new Item(Item.itemTypes.food,2)); // after building pop
		Age1.add(new ActionCard(Frugality));
		ArrayList<Item> EngineeringGenius = new ArrayList<Item>();
		EngineeringGenius.add(new Item(Item.itemTypes.resource,3)); // one stage of wonder
		Age1.add(new ActionCard(EngineeringGenius));
		ArrayList<Item> EfficientUpgrade = new ArrayList<Item>();
		EfficientUpgrade.add(new Item(Item.itemTypes.resource,2)); // for upgrade
		Age1.add(new ActionCard(EfficientUpgrade));
		Age1.add(new ActionCard(EfficientUpgrade));
		ArrayList<Item> Breakthrough = new ArrayList<Item>();
		Breakthrough.add(new Item(Item.itemTypes.science,2));  // after tech
		Age1.add(new ActionCard(Breakthrough));
		ArrayList<Item> BountifulHarvest = new ArrayList<Item>();
		BountifulHarvest.add(new Item(Item.itemTypes.food,2));
		Age1.add(new ActionCard(BountifulHarvest));
				
		for (int i=0; i<ProdCards[numPlayers-2]; i++){
			Age1.add(new Farm(4, 2));
			Age1.add(new Mine(5, 2));
		}
		Age1.add(new Temple(1, 2, 5));
		if (numPlayers>2){
			Age1.add(new Temple(1, 2, 5));
		}
		Age1.add(new Lab(2, 5));
		Age1.add(new Lab(2, 5));
		if (numPlayers==4){
			Age1.add(new Lab(2, 5));
		}

		// Arenas, Library, Theater
		ActionCard DummyCard = new ActionCard(new ArrayList<Item>());
		Age1.add(DummyCard);
		if (numPlayers>2){
			Age1.add(DummyCard);
		}
		Age1.add(DummyCard);
		Age1.add(DummyCard);
		Age1.add(DummyCard);
		Age1.add(DummyCard);
		// Swordsmen, Knights
		Age1.add(DummyCard);
		Age1.add(DummyCard);
		if (numPlayers==4){
			Age1.add(DummyCard);
		}
		Age1.add(DummyCard);
		Age1.add(DummyCard);
		if (numPlayers==4){
			Age1.add(DummyCard);
		}
		// Wonders
		for (int i=0; i<4; i++){
			Age1.add(DummyCard);
		}
		// Leaders
		for (int i=0; i<6; i++){
			Age1.add(DummyCard);
		}
		// Special
		for (int i=0; i<4; i++){
			Age1.add(DummyCard);
		}
		if (numPlayers>2){
			Age1.add(DummyCard);
			Age1.add(DummyCard);
		}
		
		Age1.add(new GovernmentCard(4, 3)); // Theocracy has other benefits
		Age1.add(new GovernmentCard(5, 3)); // Monarchy
		if (numPlayers>2){
			Age1.add(new GovernmentCard(5, 3));
		}
	}
	
	public void refill(){
		oneAction[0] = null;
		if (numPlayers < 4) oneAction[1] = null;
		if (numPlayers < 3) oneAction[2] = null;

		int nextFree = findFree(0);
		
		for (int i=0; i<5; i++){
			if (oneAction[i] == null){
				oneAction[i] = getCard(nextFree);
				nextFree = findFree(nextFree);
			}
		}
		for (int i=0; i<4; i++){
			if (twoAction[i] == null){
				twoAction[i] = getCard(nextFree);
				nextFree = findFree(nextFree);
			}
		}
		for (int i=0; i<4; i++){
			if (threeAction[i] == null){
				threeAction[i] = getCard(nextFree);
				nextFree = findFree(nextFree);
			}
		}
	}

	private Card getCard(int i) {
		// retrieve card at given index and set to null
		// if out of range get from deck
		Card result = null;
		if (i < 5){
			result = oneAction[i];
			oneAction[i] = null;
			return result;
		}
		if (i < 9){
			result = twoAction[i-5];
			twoAction[i-5] = null;
			return result;
		}
		if (i < 13){
			result = threeAction[i-9];
			threeAction[i-9] = null;
			return result;
		}
		
		// TODO : use appropriate age deck and check for empty
		result = Age1.remove(rand.nextInt(Age1.size()));
		return result;
	}

	private int findFree(int i)  {
		// finds next free index in card row, starting at given index,
		// 99 if none left
		while (i < 5){
			if (oneAction[i] != null) return i;
			i++;
		}
		while (i < 9){
			if (twoAction[i-5] != null) return i;
			i++;
		}
		while (i < 13){
			if (threeAction[i-9] != null) return i;
			i++;
		}
		return 99;
	}

	public CardRow(int numPlayers) {
		super();
		this.numPlayers = numPlayers;

		initAge1(); // TODO and other ages
		
		refill();
	}
	
	
}
