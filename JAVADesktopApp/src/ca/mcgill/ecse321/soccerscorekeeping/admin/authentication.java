package ca.mcgill.ecse321.soccerscorekeeping.admin;

import java.util.HashMap;

/**
 * <h1>Authentication</h1>
 * This class provides methods for validating
 * credentials.
 * @author Owais
 *
 */
public class authentication 
{
	//Attributes
	private static HashMap<String,String> userNames;
	
	/**
	 * Constructor
	 */
	public authentication()
	{
		userNames = new HashMap<String,String>();
		userNames.put("owais","git");
		userNames.put("npurdie","git");
		userNames.put("jers","git");
		userNames.put("nevmx","git");
		userNames.put("mcintosh","bluejays");
		userNames.put("walter","white");
	}
	
	/**
	 * This method authenticates the manager.
	 * @param username Manager's input username
	 * @param password Manager's input password
	 * @return true if successful, otherwise false.
	 */
	public boolean authenticate(String username,char[] password)
	{
		if(userNames.get(username)==null)
		{
			return false;
		}
		
		for(int i=0;i<password.length;i++)
		{
			if(!(userNames.get(username).charAt(i)==password[i]))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This method authenticates the scorekeepers.
	 * @param username scorekeeper's input username
	 * @param password scorekeeper's input password
	 * @return true if successful, otherwise false.
	 */
	public boolean authenticateScoreKeeper(String username,char[] password)
	{
		if(userNames.get(username)==null)
		{
			return false;
		}
		
		for(int i=0;i<password.length;i++)
		{
			if(!(userNames.get(username).charAt(i)==password[i]))
			{
				return false;
			}
		}
		return true;
	}
}
