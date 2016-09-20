package ca.mcgill.ecse321.soccerscorekeeping.application;

import javax.swing.SwingUtilities;

import ca.mcgill.ecse321.soccerscorekeeping.persistence.PersistenceSoccerScoreKeeping;
import ca.mcgill.ecse321.soccerscorekeeping.view.ModeSelectionPage;

public class SoccerScoreKeeping 
{

	public static void main(String[] args) 
	{
		//Initialize the Database
		PersistenceSoccerScoreKeeping.loadSoccerScores();
		
		//Opens the main UI
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run() 
			{
				new ModeSelectionPage().setVisible(true);
			}
			
		});
	}

}
