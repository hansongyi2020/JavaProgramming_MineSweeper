package application;

public class MineMap {
	// package access
	int numMines;
	boolean[][] isMined = new boolean[GameBoardPanel.ROWS][GameBoardPanel.COLS];
	// default is false

	public MineMap() {//constructor
		super();
	}
	
	public void newMineMap(int numMines) {
		this.numMines = numMines;
		// Hardcoded for illustration and testing, assume numMines=10
		//Mines randomly display
		for (int row = 0; row < this.numMines; ++row) {
			isMined[(int)(Math.random()*10)][(int)(Math.random()*10)] = true; 
		} 
	}
}
