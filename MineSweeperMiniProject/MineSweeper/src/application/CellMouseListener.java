package application;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class CellMouseListener extends MouseAdapter {
	static int countFlag = 3;
	@Override
	public void mouseClicked(MouseEvent e) {
		Cell sourceCell = (Cell)e.getSource();
	          
	    System.out.println("You clicked on (" + sourceCell.row + "," + sourceCell.col + ")");

	    // Left-click to reveal a cell;
	    if (e.getButton() == MouseEvent.BUTTON1) {
	    	if (sourceCell.isMined) { //if hit a mine
	    		System.out.println("Game Over");
	    		View. isGameOver = true;
	    		sourceCell.setText("*");
	    		//dialog
	    		String[] buttons2 = {"New Game", "exit"};
	    		int result =JOptionPane.showOptionDialog(null, "Game Over!", "Game Result", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons2, "New Game");
	    		System.out.println("Game Result: " + result);
	    		if(result==0){ //if new game
	    			GameBoardPanel.newGame();
	    		}else {System.exit(0);} ///if exit
	    	}else { //if not hit a mine
	    		GameBoardPanel.revealCell(sourceCell.row, sourceCell.col);
	    		if(GameBoardPanel.checkPlayerWon()) { //if turned all cell
	    			System.out.println("Player Won!!"); 
	    			//dialog
	    			String[] buttons3 = {"New Game", "exit"};
	    			int result = JOptionPane.showOptionDialog(null, "Player Won!!", "Game Result", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, buttons3, "Player Won!!");
	    			System.out.println("Game Result: " + result);
	    			if(result==0){
	    				GameBoardPanel.newGame();
	    		 	}else {System.exit(0);}
	    		}
	    	}
	    }else if(e.getButton() == MouseEvent.BUTTON3) { 
	    	//Right-click to plant/remove the flag.
	    	// If this cell is flagged, remove the flag
	    	// else plant a flag. 
	    	while(countFlag >= 0) {//if have a flag
	    		if(sourceCell.isFlagged) { //if flag 
	    			System.out.println("Remove a flag");
	    			System.out.println("remain flag: " + countFlag);
	    			sourceCell.setText("");
	    			sourceCell.isFlagged = false;
	    	        break;
	    		} else if (!(sourceCell.isFlagged)&countFlag>0) { //if not flag 	
	    			System.out.println("Plant a flag"); 
	    			sourceCell.setText(">");
	    			sourceCell.isFlagged = true;
	    			//check isMined
	    			if(GameBoardPanel.cells[sourceCell.row][sourceCell.col].isMined) {
	    				 GameBoardPanel.revealCell(sourceCell.row, sourceCell.col);
	    	             System.out.println("You find a flag");
	    			}
	    			countFlag--;
	    			System.out.println("remain flag: " + countFlag);
	    			break;
	    		}
	    	    countFlag--;
	    	    break;
	    	}
	    	if(countFlag<0){// if no flag
	    		System.out.println("Flag missing!"); 
	    		JOptionPane.showMessageDialog(null, "Flag missing!");
	    	}
	    }
	}
}
