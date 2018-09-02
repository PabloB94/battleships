package battleship;
import java.util.Random;
import java.util.ArrayList;

public class Board {
	private int[][] board;
	private final int NUM_SHIPS = 5;
	private final int HEIGHT = 10;
	private final int WIDTH = 10;
	private final boolean VERTICAL = false;
	private final boolean HORIZONTAL = true;

	public Board() {
		board = new int[HEIGHT][WIDTH];
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				board[i][j] = 0;
			}
		}
	}

	//0 is water
	//1 is boat
	//2 is hit boat
	//3 is hit water
	//4 means out of bounds
	
	public int marked(int lat, int lon) {
		if(!(lat < HEIGHT) || lat < 0) return 4;
		if(!(lon < WIDTH) || lon < 0) return 4;
		return this.board[lat][lon];
	}

	public void autofill() {
		Board auxb = new Board();
		Random rand = new Random();
		for (int i = 0; i < NUM_SHIPS;) {
			int lat = rand.nextInt(10);
			int lon = rand.nextInt(10);
			if(auxb.marked(lat, lon) == 0) {
				ArrayList<Coordinates> ship = auxb.placeBoat(i, lat, lon);
				if(ship != null) {
					for(int j = 0; j < ship.size(); j++) {
						this.board[ship.get(j).getLat()][ship.get(j).getLon()] = 1;
					}					i++;
				}
			}		
		}

	}
	private ArrayList<Coordinates> placeBoat(int ship, int lat, int lon) {
		int length;
		switch(ship) {
		case 0:
			length = 5;
			break;
		case 1:
			length = 4;
			break;
		case 2:
			length = 3;
			break;
		case 3:
			length = 3;
			break;
		case 4:
			length = 2;
			break;
		default:
			length = 0;
		}
		Random rand = new Random();
		boolean direction = rand.nextBoolean();
		ArrayList<Coordinates> shipCoord = null;
		if (direction == VERTICAL) {
			shipCoord = placeVertically(length, lat, lon);
			if (shipCoord == null) placeHorizontally(length, lat, lon);
		}
		if (direction == HORIZONTAL) {
			shipCoord = placeHorizontally(length, lat, lon);
			if (shipCoord == null) placeVertically(length, lat, lon);
		}
		try {
			for(int i = 0; i < length; i++) {
				ArrayList<Coordinates> surrounding = getSurrounding(shipCoord.get(i));
				for(int j = 0; j < surrounding.size(); j++) {
					this.board[surrounding.get(j).getLat()][surrounding.get(j).getLon()] = 1;
				}
			}		
		}catch(Exception e){
			return null;
		}		
		return shipCoord;
	}

	private ArrayList<Coordinates> placeVertically(int length, int lat, int lon){
		ArrayList<Coordinates> shipCoord = new ArrayList<Coordinates>();
		boolean occupiedUp = false;
		int j = 1;
		int control = 0;
		for(int i = 0; i + j < length + 1;) {
			control = i + j;
			if((this.marked(lat+i, lon) == 0) && !occupiedUp) {
				shipCoord.add(new Coordinates(lat+i, lon));
				i++;
			}else {
				occupiedUp = true;
			}
			if((this.marked(lat-j, lon) == 0) && occupiedUp) {
				shipCoord.add(new Coordinates(lat-j, lon));
				j++;
			}
			if(control == i + j) {
				shipCoord.clear();
				return null;
			}
		}
		return shipCoord;
	}
	
	private ArrayList<Coordinates> placeHorizontally(int length, int lat, int lon){
		ArrayList<Coordinates> shipCoord = new ArrayList<Coordinates>();
		boolean occupiedRight = false;
		int j = 1;
		int control = 0;
		for(int i = 0; i + j < length + 1;) {
			control = i + j;
			if((this.marked(lat, lon+i) == 0) && !occupiedRight) {
				shipCoord.add(new Coordinates(lat, lon+i));
				i++;
			}else {
				occupiedRight = true;
			}
			if((this.marked(lat, lon-j) == 0) && occupiedRight) {
				shipCoord.add(new Coordinates(lat, lon-j));
				j++;
			}
			if(control == i + j) {
				shipCoord.clear();

				return null;
			}
		}
		return shipCoord;
	}
	
	private ArrayList<Coordinates> getSurrounding(Coordinates coord){
		ArrayList<Coordinates> surrounding = new ArrayList<Coordinates>();
		int lat;
		int lon;
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				lat = coord.getLat() + i;
				lon = coord.getLon() + j;
				if((lat < HEIGHT) && (lat >= 0) && (lon < WIDTH) && (lon >= 0)) surrounding.add(new Coordinates(lat, lon));	
			}
		}
		return surrounding;
	}

}
