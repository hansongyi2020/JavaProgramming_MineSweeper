package application;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View extends JPanel{	
	private static final long serialVersionUID = 1L;

	// count variable
	static int count = 60;
	
	int width = 75, height = 75;
	int x = 260, y = 15;
	private static JLabel lbl;
	static boolean isGameOver = false;
	
	public View(boolean isGameOver) { //create View label
	   View.isGameOver= isGameOver;
		lbl = new JLabel();
		lbl.setOpaque(true);
	    lbl.setBounds(x, y, width, height);
	    lbl.setForeground(Color.BLUE);
	    lbl.setText(count + "");
	    lbl.setFont(new Font("맑은고딕", Font.PLAIN, 50));
	    lbl.setHorizontalAlignment(JLabel.CENTER);
		
	   super.setLayout(null);
	   super.add(lbl);
	   super.setPreferredSize(new Dimension(100, 100));
	}
	
	public static void main(String[] args){
		Timer timer=new Timer();
		TimerTask task=new TimerTask(){	
			@Override
			public void run() { //Auto-generated method stub
				if(count >= 0){ 
					System.out.println("[카운트다운 : "+count+"]");
					lbl.setText(count + "");
					count--;			
				}else{ //time over
					timer.cancel(); 
					System.out.println("[카운트다운 : 종료]");
					//dialog
					String[] buttons4 = {"New Game", "exit"};
					int result = JOptionPane.showOptionDialog(null, "Time Over!", "Game Result", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons4, "Game Over!");

					System.out.println("Game Result: " + result);
					if(result==0){
						GameBoardPanel.newGame();	
					}else {System.exit(0);}
				}
				if(isGameOver) {//if GameOver
					timer.cancel(); //then, Stop timer
				}	
			}
		};			
	}
}