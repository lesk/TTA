
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

		// while cards available
		for (int i=0; i< 50; i++){
			// refill card row
			player1.doTurn();
			// refill card row
			player2.doTurn();
			// refill card row
			player3.doTurn();
			// refill card row
			player4.doTurn();
		}

		player1.doTurn();

		// empty event deck

		System.out.println("Player1 = " + player1);
		System.out.println("Player2 = " + player2);
		System.out.println("Player3 = " + player3);
		System.out.println("Player4 = " + player4);
	}
}
