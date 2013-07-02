
public class Game {

	public static void main(String[] args){
		
		// Create initial card row
		// Create initial event deck
		// Create initial military deck
		
		// Create players
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		Player player3 = new Player(3);
		Player player4 = new Player(4);
		
		CardRow cr = new CardRow(4);

		// while cards available
		for (int i=0; i< 9; i++){
			player1.doTurn();
			cr.refill(); 
			player2.doTurn();
			cr.refill(); 
			player3.doTurn();
			cr.refill(); 
			player4.doTurn();
			cr.refill(); 
		}

		player1.doTurn();

		// empty event deck

		System.out.println("Player1 = " + player1);
		System.out.println("Player2 = " + player2);
		System.out.println("Player3 = " + player3);
		System.out.println("Player4 = " + player4);
	}
}
