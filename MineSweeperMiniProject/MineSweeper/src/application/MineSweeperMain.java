package application;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MineSweeperMain extends JFrame{
    private static final long serialVersionUID = 1L;  // to prevent serial warning 

    View timer = new View(false); //create View Object
    //create GameBoardPanel Object
    GameBoardPanel board = new GameBoardPanel(); 
    JButton btnNewGame = new JButton("New Game");//create JButton
   
    // Constructor to set up all the UI and game components
    public MineSweeperMain() {
	    Container cp = this.getContentPane();           // JFrame's content-pane
	    cp.setLayout(new BorderLayout()); // in 10x10 GridLayout
	      
	    cp.add(timer, BorderLayout.NORTH);  // Timer dispose 
	    cp.add(board, BorderLayout.CENTER);  // Grid dispose 
	
	    cp.add(btnNewGame, BorderLayout.SOUTH); // newGame Button dispose 
	    btnNewGame.addActionListener(new ActionListener() { // newGame Button action
	        @Override 
	        public void actionPerformed(ActionEvent e) {
	           GameBoardPanel.newGame();
	        }
	    });
	
	    GameBoardPanel.newGame();
		pack();  // Pack the UI components, instead of setSize()
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // handle window-close button
		setTitle("Mineswepper");
		setVisible(true);   // show it         
	}

	public static void main(String[] args) {
	      new MineSweeperMain();
	}

}