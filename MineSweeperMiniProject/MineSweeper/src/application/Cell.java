package application;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Cell extends JButton{
	private static final long serialVersionUID = 1L; // to prevent serial warning

	// Define named constants for JButton's colors and fonts
	// to be chosen based on cell's state
	public static final Color BG_NOT_REVEALED = Color.GRAY;
	public static final Color FG_NOT_REVEALED = Color.RED; // flag, mines
	public static final Color BG_REVEALED = Color.DARK_GRAY;
	public static final Color FG_REVEALED_1 = Color.GREEN;//mines 1
	public static final Color FG_REVEALED_2 = Color.YELLOW;//mines 2
	public static final Color FG_REVEALED_3 = Color.WHITE;//mines 3
	public static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);

	int row, col;
	/** Already revealed? */
	boolean isRevealed;
	/** Is a mine? */
	boolean isMined;
	/** Is Flagged by player? */
	boolean isFlagged;

	public Cell(int row, int col) {//constructor
		super(); // JTextField
		this.row = row;
		this.col = col;// Set JButton's default display properties
		super.setFont(FONT_NUMBERS);
	}
	
	public void newGame(boolean isMined, int numMines) {
		this.isRevealed = false; // default
		this.isFlagged = false; // default
		this.isMined = isMined; // given
		super.setEnabled(true); // enable button
		super.setText(""); // display blank
		paint(numMines); //paint() call
	}
	
	public void paint(int numMines) {
		if(isRevealed) {//isRevealed
			  super.setBackground(BG_REVEALED);
			  if(numMines == 1) {
			         super.setForeground(FG_REVEALED_2);
			  } else if(numMines == 2) {
			         super.setForeground(FG_REVEALED_2);
			 } else if(numMines == 0) {
			         super.setText("");
			 } else {
			         super.setForeground(FG_REVEALED_3);
			 }
		} else {//not Reveal
			   super.setBackground(BG_NOT_REVEALED);
			   super.setForeground(FG_NOT_REVEALED);
		}	 
	}
}
