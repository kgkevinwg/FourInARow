import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.*;

public class Main extends JFrame{
	
	final int CHKWINNINGMOVE = 1;
	final int CHKPROGRESSMOVE = 2;
	
	

	Vector<Integer> vecChipStatus;
	Tile[][] tiles= new Tile[10][10];
	
	JPanel gamePanel;
	JLabel topLabel;
	JPanel buttonPanel;
	JPanel tilePanel;
	static public JButton btnInsert1,btnInsert2,btnInsert3,btnInsert4,btnInsert5,btnInsert6,btnInsert7;
	
	JLabel playerTurn;
	int turnNumber = 1;
	ActionHelper actionHelper = new ActionHelper();
	
	boolean isFinished = false;
	static boolean buttonClicked = false;
	static int lastButtonClick = -1;
	
	public Main() {
		
		initializeMainComponent();
		startGame();

	}
	
	private void placeChip()
	{
		
		int val = vecChipStatus.get(lastButtonClick);
		System.out.println("val: "+val);
		if(val < 6)
		{
			System.out.println("x: "+lastButtonClick+"Y: "+val);
			tiles[lastButtonClick][val].setStatus(turnNumber);
			switch(turnNumber)
			{
			case 1:
				tiles[lastButtonClick][val].reDraw("rb.png");break;
			case 2:
				tiles[lastButtonClick][val].reDraw("bb.png");break;
			}
			
			
			val++;
			vecChipStatus.set(lastButtonClick, val);
			System.out.println("Chip status on column "+lastButtonClick+": "+val);
			
		}
		turnNumber++;
		if(turnNumber == 3)turnNumber = 1;
	}
	
	
	private void startGame() {
		// TODO Auto-generated method stub
		
		while(!isFinished)
		{
			System.out.println("TurnNumber: "+turnNumber);
			switch(turnNumber)
			{
			case 1: playerTurn.setText("Player 1's turn!");	playerTurn.setForeground(Color.red);break;
			case 2: playerTurn.setText("Player 2's turn!");	playerTurn.setForeground(Color.blue);break;

			
			}
			if(turnNumber ==1)
			{
				while(!buttonClicked) {	try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		}
			}
			
			if(turnNumber == 1)
			{
				buttonClicked = false;
				placeChip();
				System.out.println("player place chip, now turn number is "+turnNumber);
				
			}
			else
			{
				computerMove();
				turnNumber = 1;
			}
			
			
		}
		
		
		
	}


	boolean computerMoveFlag = false;
	private void computerMove() {
		// TODO Auto-generated method stub
		computerMoveFlag = false;
		System.out.println("computer is thinking...");
		
		outerloop:
		for(int a=0;a<6;a++)
		{
			for(int b=0;b<7;b++)
			{
				
				checkMove(2,b,a,1);
				if(computerMoveFlag)break outerloop;
			}
		}
		outerloop2:
		if(!computerMoveFlag)
		{
			System.out.println("Tyring to block openent");
			for(int a=0;a<6;a++)
			{
				for(int b=0;b<7;b++)
				{
					checkMove(1,b,a,1);
					if(computerMoveFlag)break outerloop2;
				}
			}
		}
		
		if(computerMoveFlag)System.out.println("Trying to make a Smart Move");
		
		outerloop3:
			if(!computerMoveFlag)
			{
				for(int a=0;a<6;a++)
				{
					for(int b=0;b<7;b++)
					{
						
						checkMove(2,b,a,2);
						if(computerMoveFlag)break outerloop3;
					}
				}
			}
			
			outerloop4:
			if(!computerMoveFlag)
			{
				System.out.println("Tyring to block openent");
				for(int a=0;a<6;a++)
				{
					for(int b=0;b<7;b++)
					{
						checkMove(1,b,a,2);
						if(computerMoveFlag)break outerloop4;
					}
				}
			}
		
		
		if(!computerMoveFlag)makeRandomMove();
		
		
		
	}
	
	private void checkMove(int turn,int x, int y,int mode)
	{
		int antiTurn = 0;
		switch(turn)
		{
		case 1: antiTurn =2;break;
		case 2: antiTurn = 1;break;
		}
		
		switch(mode)
		{
		case 1:
			System.out.println("Checking for Left Diagonal move on X: "+x+" Y: "+y);
			checkLeftDiagonal(turn,antiTurn,x,y,CHKWINNINGMOVE);
			System.out.println("Checking for Straight move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkStraight(turn,antiTurn,x,y,CHKWINNINGMOVE);
			System.out.println("Checking for Right Diagonal move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkRightDiagonal(turn,antiTurn,x,y,CHKWINNINGMOVE);
			System.out.println("Checking for Horizontal Left move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkHorizontalLeft(turn,antiTurn,x,y,CHKWINNINGMOVE);
			System.out.println("Checking for Horizontal Right move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkHorizontalRight(turn,antiTurn,x,y,CHKWINNINGMOVE);
			System.out.println("Checking for Left Diagonal move on X: "+x+" Y: "+y);
			break;
		case 2:
			checkLeftDiagonal(turn,antiTurn,x,y,CHKPROGRESSMOVE);
			System.out.println("Checking for Straight move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkStraight(turn,antiTurn,x,y,CHKPROGRESSMOVE);
			System.out.println("Checking for Right Diagonal move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkRightDiagonal(turn,antiTurn,x,y,CHKPROGRESSMOVE);
			System.out.println("Checking for Horziontal Left move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkHorizontalLeft(turn,antiTurn,x,y,CHKPROGRESSMOVE);
			System.out.println("Checking for Horizontal Right move on X: "+x+" Y: "+y);
			if(!computerMoveFlag)checkHorizontalRight(turn,antiTurn,x,y,CHKPROGRESSMOVE);
			break;
			
		}
		
		
		
		
		
		
	}
	
	private void makeRandomMove()
	{
		System.out.println("Computer is tired, making random move");
		int randX = ThreadLocalRandom.current().nextInt(0,6);
		makeWinningMove(2,randX,vecChipStatus.get(randX),false);
	}
	
	private void makeWinningMove(int turn,int x, int y, boolean isWinning)
	{
		int val = vecChipStatus.get(x);
		System.out.println("Computer is placing chip on X: "+x+"Y: "+y);
		tiles[x][val].setStatus(turn);
		tiles[x][val].reDraw("bb.png");
		computerMoveFlag = true;
		
		val++;
		vecChipStatus.set(x, val);
		System.out.println("Chip status on column "+x+": "+val);
		
		return;
	}
	
	private void checkLeftDiagonal(int turn,int antiTurn,int x, int y,int mode)
	{
		
		if(x>=2 && y<=2)
		{
			if(mode == CHKWINNINGMOVE)
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x-1][y+1].getStatus() == turn && tiles[x-2][y+2].getStatus() == turn && tiles[x-3][y+3].getStatus() != antiTurn && vecChipStatus.get(x-3) <6)
				{
					if(tiles[x-3][y+2].getStatus() != 0)makeWinningMove(2,x-3,y+3,true);
				}
			}
			else
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x-1][y+1].getStatus() == turn && tiles[x-2][y+2].getStatus() != antiTurn && tiles[x-2][y+2].getStatus() != turn)
				{
					if(tiles[x-2][y+1].getStatus() !=0)makeWinningMove(2,x-2,y+2,false);
				}
			}
			
		}
		
	}
	
	private void checkStraight(int turn,int antiTurn,int x, int y,int mode)
	{
		if(y<=2)
		{
			if(mode == CHKWINNINGMOVE)
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x][y+1].getStatus() == turn && tiles[x][y+2].getStatus() == turn && tiles[x][y+3].getStatus() != antiTurn && vecChipStatus.get(x) <6)
				{
					makeWinningMove(2,x,y+3,true);
				}
			}
			else
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x][y+1].getStatus() == turn && tiles[x][y+2].getStatus() != antiTurn && tiles[x][y+2].getStatus() != turn)
				{
					makeWinningMove(2,x,y+2,false);
				}
			}
			
		}
	}
	
	private void checkRightDiagonal(int turn,int antiTurn,int x, int y,int mode)
	{
		if( x <=3 &&y<=3)
		{
			if(mode == CHKWINNINGMOVE)
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x+1][y+1].getStatus() == turn && tiles[x+2][y+2].getStatus() == turn && tiles[x+3][y+3].getStatus() != antiTurn && vecChipStatus.get(x+3) <6)
				{
					if(tiles[x+3][y+2].getStatus() != 0)makeWinningMove(2,x+3,y+3,true);
				}
			}
			else
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x+1][y+1].getStatus() == turn && tiles[x+2][y+2].getStatus() != antiTurn && tiles[x+2][y+2].getStatus() != turn)
				{
					if(tiles[x+2][y+1].getStatus() !=0)makeWinningMove(2,x+2,y+2,false);
				}
			}
			
		}
	}
	
	private void checkHorizontalLeft(int turn,int antiTurn,int x, int y, int mode)
	{
		if( x >=3)
		{
			if(mode == CHKWINNINGMOVE)
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x-1][y].getStatus() == turn && tiles[x-2][y].getStatus() == turn && tiles[x-3][y].getStatus() != antiTurn && vecChipStatus.get(x-3) <6)
				{
					makeWinningMove(2,x-3,y,true);
				}
			}
			else
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x-1][y].getStatus() == turn && tiles[x-2][y].getStatus() != antiTurn && tiles[x-2][y].getStatus() != turn)
				{
					makeWinningMove(2,x-2,y,false);
				}
			}
			
		}
	}
	
	private void checkHorizontalRight(int turn,int antiTurn,int x, int y, int mode)
	{
		if( x<=2)
		{
			if(mode == CHKWINNINGMOVE)
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x+1][y].getStatus() == turn && tiles[x+2][y].getStatus() == turn && tiles[x+3][y].getStatus() != antiTurn && vecChipStatus.get(x+3) <6)
				{
					makeWinningMove(2,x+3,y,true);
				}
			}
			else
			{
				if(tiles[x][y].getStatus() == turn &&  tiles[x+1][y].getStatus() == turn && tiles[x+2][y].getStatus() != antiTurn && tiles[x+2][y].getStatus() != turn)
				{
					makeWinningMove(2,x+2,y,false);
				}
			}
			
		}
	}
	
	

	private void initializeTiles()
	{

		//Vector<Tile>vecTile = new Vector<>();
		
		for(int a = 5;a>=0;a--)
		{
			for(int b = 0;b<7;b++)
			{
//				Tile tile = new Tile(b,a);
//				vecTile.add(tile);
//				tile = vecTile.lastElement();
//				tile.setSize(tilePanel.getWidth()/7, tilePanel.getHeight()/6);
//				tilePanel.add(tile);
				
				
				Tile tile = new Tile(b,a);
				tile.setSize(tilePanel.getWidth()/7, tilePanel.getHeight()/6);
				tiles[b][a] = tile;
				tilePanel.add(tiles[b][a]);
				
				
			
				
				
			}
		}
		
		vecChipStatus = new Vector<>();
		for(int a=0;a<7;a++)
		{
			vecChipStatus.add(0);
		}
		
	}
	private void initializeMainComponent() {
		// TODO Auto-generated method stub
		
		gamePanel = new JPanel();
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		tilePanel = new JPanel();
		tilePanel.setLayout(new GridLayout(6,7));
		btnInsert1 = new JButton("Insert 1");
		btnInsert2 = new JButton("Insert 2 ");
		btnInsert3 = new JButton("Insert 3");
		btnInsert4 = new JButton("Insert 4");
		btnInsert5 = new JButton("Insert 5");
		btnInsert6 = new JButton("Insert 6");
		btnInsert7 = new JButton("Insert 7");
		btnInsert1.addActionListener(actionHelper);
		btnInsert2.addActionListener(actionHelper);
		btnInsert3.addActionListener(actionHelper);
		btnInsert4.addActionListener(actionHelper);
		btnInsert5.addActionListener(actionHelper);
		btnInsert6.addActionListener(actionHelper);
		btnInsert7.addActionListener(actionHelper);
		buttonPanel.add(btnInsert1);		
		buttonPanel.add(btnInsert2);		
		buttonPanel.add(btnInsert3);		
		buttonPanel.add(btnInsert4);
		buttonPanel.add(btnInsert5);
		buttonPanel.add(btnInsert6);
		buttonPanel.add(btnInsert7);
		
		playerTurn = new JLabel("Player 1's turn!");
		playerTurn.setForeground(Color.red);
		
		
		initializeTiles();
		
		
		
		
		
		gamePanel.setLayout(new BorderLayout());
		BorderLayout layout = (BorderLayout) gamePanel.getLayout();
		//gamePanel.remove(layout.getLayoutComponent(BorderLayout.SOUTH));
		gamePanel.validate();
		
		
		
		gamePanel.add(buttonPanel,BorderLayout.NORTH);
		gamePanel.add(tilePanel, BorderLayout.CENTER);
		
		
		
		topLabel = new JLabel("Welcome to four in a row", SwingConstants.CENTER);
		topLabel.setFont(new Font(topLabel.getFont().getName(), topLabel.getFont().getStyle(), 20));
		
		
		
		
		this.add(topLabel,BorderLayout.NORTH);
		this.add(gamePanel,BorderLayout.CENTER);
		this.add(playerTurn,BorderLayout.WEST);
		setVisible(true);
		setSize(new Dimension(750,500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
	}

	public static void main(String[] args) {
		new Main();
	}


	

}
