package application;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameBoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;  // to prevent serial warning

	// Define named constants for the game properties
	public static final int ROWS = 10;      
	public static final int COLS = 10;

	// Define named constants for UI sizes
	public static final int CELL_SIZE = 60;  // Cell width and height, in pixels
	public static final int CANVAS_WIDTH  = CELL_SIZE * COLS; 
	public static final int CANVAS_HEIGHT = CELL_SIZE * ROWS;

	// Define properties (package-visible)
	/** The game board composes of ROWSxCOLS cells */
	static Cell cells[][] = new Cell[ROWS][COLS];
	
	/**numMines */
	static int numMine;

	public static void setlevel() {
	//Difficulty level selection dialog
		String[] buttons1 = {"Beginner", "Intermediate", "Advanced"};
		int num = JOptionPane.showOptionDialog(null, "Select Level(Beginner, Intermediate, Advanced)", "Select Level",
		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons1, "Beginner");
	
		if(num == 0) { //Beginner
			numMine = 5; 
			System.out.println("Level: Beginner");
		}else if(num == 1){//Intermediate
			numMine = 10;
			System.out.println("Level: Intermediate");
		}else {//Advanced
			numMine = 15;
			System.out.println("Level: Advanced");
		} 
	}
	
	public static void newGame() {
		// Get a new mine map
		int numMines = 0;
		CellMouseListener.countFlag = 3;
		setlevel();
		MineMap mineMap = new MineMap();
		mineMap.newMineMap(numMine);

		// Reset cells, mines, and flags
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				// Initialize each cell with/without mine
				cells[row][col].newGame(mineMap.isMined[row][col], numMines);
			} 
		}
		//Timer initialize
		View.main(null);
		View.count = 60;
		View.isGameOver =  false;// Announcement GameOver
	}
	
	private static int getSurroundingMines(int srcRow, int srcCol) { 
		int numMines = 0;
		for (int row = srcRow - 1; row <= srcRow + 1; row++) {
			for (int col = srcCol - 1; col <= srcCol + 1; col++) {
				// Need to ensure valid row and column numbers too
				if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
					if (cells[row][col].isMined) numMines++;
				}
			} 
		}
		return numMines;
	}
	
	public static void revealCell(int srcRow, int srcCol) { 
		int numMines = getSurroundingMines(srcRow, srcCol);
		cells[srcRow][srcCol].setText(numMines + "");
		cells[srcRow][srcCol].isRevealed = true;
		cells[srcRow][srcCol].paint(numMines); // based on isRevealed
		if (numMines == 0) {
			// Recursively reveal the 8 neighboring cells
			for (int row = srcRow - 1; row <= srcRow + 1; row++) {
				for (int col = srcCol - 1; col <= srcCol + 1; col++) {
					// Need to ensure valid row and column numbers too
					if (row >= 0 && row < ROWS && col >= 0 && col < COLS) {
						if (!cells[row][col].isRevealed) revealCell(row, col);
					}
				}
			}
		}
	}

	public static boolean checkPlayerWon(){ 
		boolean checkp_won = true;
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) { 
				if(!(cells[row][col].isMined)) { //if not mines
					if(!(cells[row][col].isRevealed)) checkp_won = false;
				}
			}
		}
		return checkp_won;
	}

}
