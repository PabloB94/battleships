package battleship;

public class Main {
	
	public static void main(String[] args){
		Board aiboard = new Board();
		Board pboard = new Board();

		aiboard.autofill();
		System.out.println("  0 1 2 3 4 5 6 7 8 9");
		for(int i = 0; i < 10; i++) {
			String line = new String("" + i);
			for(int j = 0; j < 10; j++) {
				if((aiboard.marked(i, j) == 1)) {
					line = line + " X";
				}else {
					line = line + "  ";
				}
			}
			System.out.println(line);
		}
		Game game = new Game(aiboard, pboard);
		game.start();
		
	}
	
}
