package ca.mcgill.ecse321.soccerscorekeeping.application;



import ca.mcgill.ecse321.soccerscorekeeping.persistence.PersistenceSoccerScoreKeeping;
//import ca.mcgill.ecse321.soccerscorekeeping.view.ModeSelectionPage;
import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class SoccerScoreKeeping 
{

	public static void main(String[] args) 
	{

		
		int Mode = Integer.parseInt(args[0]);
		PersistenceSoccerScoreKeeping.loadSoccerScores();


		if (Mode == 0)
			Analysis_Mode(args);
		else
			Input_Mode(args);
		

    }
	
	public static void Analysis_Mode(String[] args) {
		
		if (args.length < 4) {
			System.exit(0);
		}
		
		int rankingNumber = Integer.parseInt(args[1]);
		if (rankingNumber == -1) {
			//batch input and live input modes output players so live and  
		} else if (rankingNumber == -2) {
			
		} 
		boolean typeOfRanking = false;
		boolean rankingStrategy = false;
		if (Integer.parseInt(args[2]) ==  1) {
			typeOfRanking = true;
		}
		if (Integer.parseInt(args[3]) == 1) {
			rankingStrategy = true;
		}
		
		controller c = new controller();
		String output = ""; 
		
		if (typeOfRanking) {		//league analysis mode
			if (rankingStrategy) {			//rank by goals
				Team[] temp = c.topTeams(rankingNumber,"Goals");
				for(int i=0;i<temp.length;i++)	{
					output += temp[i].getName()+"	"+temp[i].shotsTaken()+"	"+temp[i].goalsScored()+"\n";
				}
			}

			else if (!rankingStrategy) {		//rank by penalties
				Team[] temp = c.topTeams(rankingNumber,"Infractions");
				for(int i=0;i<temp.length;i++)	{
					output += temp[i].getName()+"	"+temp[i].totalInfractions()+"	"+temp[i].yellowCards()+"	"+temp[i].redCards()+"	"+temp[i].penaltyKicks()+"\n";
				}
				
			}
		}
		else 	{		//player analysis mode
			if (rankingStrategy) {			//rank by goals
				Player[] temp = c.topPlayers(rankingNumber,"Goals");
				for(int i=0;i<temp.length;i++)	{
					output += temp[i].getTeam().getName()+"	"+temp[i].getName()+"	"+temp[i].getShots().size()+"	"+temp[i].goalsScored()+"\n";
				}
			}

			else if (!rankingStrategy) {		//rank by penalties
				Player[] temp = c.topPlayers(rankingNumber,"Infractions");
				for(int i=0;i<temp.length;i++)	{
					output += temp[i].getTeam().getName()+"	"+temp[i].getName()+"	"+temp[i].getInfractions().size()+"	"+temp[i].yellowCards()+"	"+temp[i].redCards()+"	"+temp[i].penaltyKicks()+"\n";
				}
				
			}
		
		}
		System.out.print(output);
		
	}
	
	public static void Input_Mode(String[] args) {
		

		controller c = new controller();
		String output = ""; 

		int MatchOperation = Integer.parseInt(args[1]);
		
		if (MatchOperation == 2) {	
			if (args.length < 5) {
				System.exit(0);
			}
			String team1 = args[2];
			String team2 = args[3];
			String name = args[4];
			//start match
			if (name == "") {
				name = team1 + " v " + team2;
			} 
			c.createMatch(name, team1, team2);
			output += "Match Created\n";
		} else {
			if (args.length < 7) {
				if (MatchOperation == 1 && args.length > 4) {
					String mName = args[4];
					Match match = c.findMatch(mName);
					c.chooseWinner(match);
					output += "End Match\n";
				} 
				System.exit(0);
				
			} 
			
			String mName = args[4];
			Match match = c.findMatch(mName);
			String teamName = args[2];
			String playerName = args[3];
			int type = Integer.parseInt(args[5]);
			int selection = Integer.parseInt(args[6]);
			
			Team team = c.findTeam(teamName);
			Player player = c.findPlayer(team, playerName); 

			switch (type) {
			
				case 0:
					int penalty = Integer.parseInt(args[7]);
					boolean isPenalty = (penalty > 0)? true : false; 
					String penaltyType = (selection == 0)? "Yellow" : "Red";
					c.createInfraction(player, match, penaltyType,  isPenalty);
					output += "Infraction Created\n";
					break;
				case 1:
					boolean goal = false; 
					if (selection == 0) {
						goal = true; 	
					}
					c.createShot(player,match, goal);
					output += "Add Shot\n";
					break;
			}
			if (MatchOperation == 1) {
				c.chooseWinner(match);
				output += "End Match\n";
			}
		}
		System.out.print(output);
		
	}

}
