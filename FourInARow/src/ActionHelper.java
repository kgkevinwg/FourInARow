import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHelper implements ActionListener{

	public ActionHelper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == Main.btnInsert1)
		{	
			Main.lastButtonClick = 0;
		}
		else if(e.getSource() == Main.btnInsert2)
		{
			Main.lastButtonClick = 1;
		}
		else if(e.getSource() == Main.btnInsert3)
		{
			Main.lastButtonClick = 2;
		}
		else if(e.getSource() == Main.btnInsert4)
		{
			Main.lastButtonClick = 3;
		}
		else if(e.getSource() == Main.btnInsert5)
		{
			Main.lastButtonClick = 4;
		}
		else if(e.getSource() == Main.btnInsert6)
		{
			Main.lastButtonClick = 5;
		}
		else if(e.getSource() == Main.btnInsert7)
		{
			Main.lastButtonClick = 6;
		}
		Main.buttonClicked = true;
		
	}

}
