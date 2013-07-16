
public class Game {

	public static void main(String[] args){
				
		// Create initial card row and decks
		CardRow cr = new CardRow(4);

		// Create players
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		// while cards available
		for (int i=1; i< 9; i++){
			System.out.println("Turn # " + i);
			player1.doTurn();
			CardRow.refill(); 
			player2.doTurn();
			CardRow.refill(); 
			player3.doTurn();
			CardRow.refill(); 
			player4.doTurn();
			if (CardRow.refill()) break;
		}

		// empty event deck
		// TODO : last age plus event scoring

		System.out.println("Player1 = " + player1);
		System.out.println("Player2 = " + player2);
		System.out.println("Player3 = " + player3);
		System.out.println("Player4 = " + player4);
	}
}
