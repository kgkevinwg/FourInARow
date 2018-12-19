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

import javax.swing.*;

public class Main extends JFrame{
	

	Vector<Integer> vecChipStatus;
	Tile[][] tiles= new Tile[10][10];
	
	JPanel gamePanel;
	JLabel topLabel;
	JPanel buttonPanel;
	JPanel tilePanel;
	static public JButton btnInsert1,btnInsert2,btnInsert3,btnInsert4,btnInsert5,btnInsert6,btnInsert7;
	
	JLabel playerTurn;
	
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
		if(val < 7)
		{
			
			
			val++;
			vecChipStatus.set(lastButtonClick, val);
			
		}
	}
	
	
	private void startGame() {
		// TODO Auto-generated method stub
		
		while(!isFinished)
		{
			while(!buttonClicked) {}
			placeChip();
			
			
		}
		
		
	}


	private void initializeTiles()
	{

		
		for(int a = 0;a<6;a++)
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
