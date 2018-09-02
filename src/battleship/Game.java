package battleship;

import java.util.Random;
import java.util.Scanner;

public class Game {

	private Board aiboard;
	private Board pboard;
	private Random rand;
	private final boolean PLAYER = true;
	private final boolean AI = false;
	private boolean turn;
	private final int NUM_SHIPS = 5;
	private int playerSunk;
	private int aiSunk;
	private Scanner sc;
	
	public Game(Board aiboard, Board pboard) {
		this.aiboard = aiboard;
		this.pboard = pboard;
		this.rand = new Random();
		this.playerSunk = 0;
		this.aiSunk = 0;
		sc = new Scanner(System.in);
	}
	
	public void start() {
		turn = pickStart();
		while(!isOver()) {
			if(turn == PLAYER) {
				playerTurn();
			}
			if(turn == AI) {
								
			}
			turn = !turn;
		}
		sc.close();
	}
	
	private boolean pickStart() {
		return rand.nextBoolean();
	}
	
	private boolean isOver() {
		if((playerSunk == 5) || (aiSunk == 5)) return true;
		return false;
	}
	
	private void playerTurn() {
		int lat = -1;
		int lon = -1;
		System.out.println("Your turn!");
		System.out.println("Introduce the coordinates for your shot");
		boolean validInput = false;
		do {
			try {			 
				String input = sc.nextLine();
				char[] coords = input.toCharArray();
				lat = Character.getNumericValue(coords[0]);
				lon = Character.getNumericValue(coords[1]);
				if((lat >= 0) && (lat <= 9) && (lon >= 0) && (lon <= 9)) validInput = true;
				if(!validInput) System.out.println("Invalid input");
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}while(!validInput);
		switch(aiboard.marked(lat, lon)) {
		case 1:
			
			break;
		case 2:
			
			break;
		case 3:
			
			break;
		case 4:
		
			break;
		default:
		
			break;
		}
		
		
	}
}
