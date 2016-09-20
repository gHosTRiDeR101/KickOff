package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.admin.managerTools;
import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class ManagerTools extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -95309694738439034L;
	
	private JLabel errorLabel;
	private JLabel addTeamLabel;
	private JLabel addPlayerLabel;
	private JLabel deleteTeamLabel;
	private JLabel deletePlayerLabel;
	private JLabel emptyLabel1, emptyLabel2, emptyLabel3;
	
	private JLabel teamNameLabel;
	private JLabel playerNameLabel;
	private JLabel selectTeamLabel1,selectTeamLabel2,selectTeamLabel3;
	private JLabel selectPlayerLabel;
	
	private JTextField teamName;
	private JTextField playerName;
	private JButton addTeam;
	private JButton addPlayer;
	private JButton deleteTeam;
	private JButton deletePlayer;
	
	private JComboBox<String> listOfTeams_addPlayer;
	private JComboBox<String> listOfTeams_deleteTeam;
	private JComboBox<String> listOfTeams_deletePlayer;
	
	private JComboBox<String> listOfPlayers;
	private String addPlayer_choice;
	private String deleteTeam_choice;
	private String deletePlayerTeam_choice;
	private String deletePlayer_Player_choice;
	
	private JButton back;
	
	boolean updatePlayerList;
	
	private JPanel mainPanel;

	private managerTools mt;

	
	private String select = " -Select- ";  
	
	public ManagerTools()
	{
		initializeComponents();
		refreshData();
		mt = new managerTools();
	}
	
	public void initializeComponents()
	{
		//init comps
		errorLabel = new JLabel();
		addTeamLabel = new JLabel("ADD TEAM: ");
		addTeamLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,20));
		addPlayerLabel = new JLabel("ADD PLAYER: ");
		addPlayerLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,20));
		deleteTeamLabel = new JLabel("DELETE TEAM: ");
		deleteTeamLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,20));
		deletePlayerLabel = new JLabel("DELETE PLAYER: ");
		deletePlayerLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,20));
		emptyLabel1 = emptyLabel2 = emptyLabel3 = new JLabel("");
		
		back = new JButton("Back");
		back.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		teamNameLabel = new JLabel("Team Name: ");
		teamNameLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		playerNameLabel = new JLabel("Player Name: ");
		playerNameLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		selectTeamLabel1 = selectTeamLabel2 = selectTeamLabel3 = new JLabel("Select Team: ");
		selectTeamLabel1.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		selectPlayerLabel = new JLabel("Select Player: ");
		selectPlayerLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		teamName = new JTextField("",20);
		playerName = new JTextField("",20);
		
		addTeam = new JButton("Add Team");
		addTeam.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		addPlayer = new JButton("Add Player");
		addPlayer.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		deleteTeam = new JButton("Delete Team");
		deleteTeam.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		deletePlayer = new JButton("Delete Player");
		deletePlayer.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		listOfTeams_addPlayer = new JComboBox<String>();
		listOfTeams_addPlayer.setSelectedItem(select);
		listOfTeams_deleteTeam = new JComboBox<String>();
		listOfTeams_deleteTeam.setSelectedItem(select);
		listOfTeams_deletePlayer = new JComboBox<String>();
		listOfTeams_deletePlayer.setSelectedItem(select);
		
		listOfPlayers = new JComboBox<String>();
		listOfPlayers.setSelectedItem(select);
		
		mainPanel = new JPanel(new GridBagLayout());
		
		//Populate JComboBox
		Manager m = Manager.getInstance();
		Iterator<Team> i = m.getTeams().iterator();
		listOfTeams_addPlayer.addItem(select);
		listOfTeams_deleteTeam.addItem(select);
		listOfTeams_deletePlayer.addItem(select);
		listOfPlayers.addItem(select);
		while(i.hasNext())
		{
			Team t = i.next();
			listOfTeams_addPlayer.addItem(t.getName());
			listOfTeams_deleteTeam.addItem(t.getName());
			listOfTeams_deletePlayer.addItem(t.getName());

		}

		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(1000,600);
		setTitle("Manager Tools");
		updatePlayerList = false; 
		
		//Set location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//Design the JFrame
		this.getContentPane().add(mainPanel,BorderLayout.CENTER);
		GridBagConstraints c = new GridBagConstraints();
		c.insets= new Insets(10,10,10,10);
		
		c.gridx=0;
		c.gridy=0;
		mainPanel.add(errorLabel,c);
		
		c.gridy++;
		mainPanel.add(addTeamLabel,c);
		
		c.gridy++;
		mainPanel.add(teamNameLabel, c);
		
		c.gridx++;
		mainPanel.add(teamName,c);
		
		c.gridx++;
		mainPanel.add(addTeam,c);
		
		c.gridy++;
		c.gridx=0;
		
		mainPanel.add(deleteTeamLabel,c);
		
		c.gridy++;
		mainPanel.add(selectTeamLabel1,c);
		
		c.gridx++;
		mainPanel.add(listOfTeams_deleteTeam,c);
		
		c.gridx++;
		mainPanel.add(deleteTeam,c);

		
		c.gridy++;
		c.gridx=0;
		
		mainPanel.add(addPlayerLabel,c);
		
		c.gridy++;
		mainPanel.add(playerNameLabel,c);
		
		c.gridx++;
		mainPanel.add(playerName,c);
		c.gridy++;
		c.gridx=0;
		mainPanel.add(selectTeamLabel2,c);
		
		c.gridx++;
		mainPanel.add(listOfTeams_addPlayer,c);
		
		c.gridx++;
		mainPanel.add(addPlayer,c);
		
		c.gridy++;
		c.gridx=0;		
		
		mainPanel.add(deletePlayerLabel,c);
		
		c.gridy++;
		mainPanel.add(selectTeamLabel3,c);
		
		c.gridx++;
		mainPanel.add(listOfTeams_deletePlayer,c);

		c.gridy++;
		c.gridx = 0;
		mainPanel.add(selectPlayerLabel,c);
		
		c.gridx++;
		mainPanel.add(listOfPlayers,c);
		
		c.gridx++;
		mainPanel.add(deletePlayer,c);
		
		c.gridy++;
		c.gridx++;
		mainPanel.add(back,c);
		
		//Add listeners
		addTeam.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
//				managerTools mt = new managerTools();
				String err = mt.createTeam(teamName.getText());
				if(err==null)
				{
					listOfTeams_addPlayer.addItem(teamName.getText());
					listOfTeams_deleteTeam.addItem(teamName.getText());
					listOfTeams_deletePlayer.addItem(teamName.getText());
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText(err);
				}
			}
			
		});
		
		deleteTeam.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
//				managerTools mt = new managerTools();
				String err = mt.deleteTeam(deleteTeam_choice);
				if(err==null)
				{
					listOfTeams_deleteTeam.removeItem(deleteTeam_choice);
					listOfTeams_addPlayer.removeItem(deleteTeam_choice);
					listOfTeams_deletePlayer.removeItem(deleteTeam_choice);
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText(err);
				}
			}
			
		});
		
		addPlayer.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
//				managerTools mt = new managerTools();
				String a = mt.createPlayer(playerName.getText(),addPlayer_choice);
				if(a==null)
				{
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText(a);
				}
				
			}
			
		});
		
		deletePlayer.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e) 
			{
//				managerTools mt = new managerTools();
				String err = mt.deletePlayer(deletePlayer_Player_choice, deletePlayerTeam_choice);
				if(err==null)
				{
					listOfPlayers.removeItem(deletePlayer_Player_choice);
					refreshData();
				}
				else
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText(err);
				}
			}

			
		});
		
		listOfTeams_addPlayer.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				if(((String)listOfTeams_addPlayer.getSelectedItem()).equals(select))
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Please select a team.");
				}
				else
				{
					errorLabel.setText("");
					addPlayer_choice = (String)listOfTeams_addPlayer.getSelectedItem();
				}
			}
			
		});
		
		listOfTeams_deleteTeam.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{	
				if(((String)listOfTeams_deleteTeam.getSelectedItem()).equals(select))
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Please select a team.");
				}
				else
				{
					errorLabel.setText("");
					deleteTeam_choice = (String)listOfTeams_deleteTeam.getSelectedItem();
				}
			}
			
		});

		listOfTeams_deletePlayer.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				if(((String)listOfTeams_deletePlayer.getSelectedItem()).equals(select))
				{
					errorLabel.setForeground(Color.red);
					errorLabel.setText("Please select a team.");
				}
				else
				{
					errorLabel.setText("");
					deletePlayerTeam_choice = (String)listOfTeams_deletePlayer.getSelectedItem();
					
					listOfPlayers.removeAllItems();

					Team t = m.getTeam(deletePlayerTeam_choice);
					if (t.getName() != null) {
						
						Iterator<Player> i = t.getPlayers().iterator();
						while(i.hasNext())
						{
							Player p = i.next();
							if (p.getName() != null && p.getName() != "")
								listOfPlayers.addItem(p.getName());

						}
						
					}
					listOfPlayers.addItem(select);
					listOfPlayers.setSelectedItem(select);
					updatePlayerList = true; 
				}
			}
			
		});
		
		listOfPlayers.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				if (updatePlayerList) {	
					updatePlayerList = false;
					errorLabel.setText("");
					deletePlayer_Player_choice = (String)listOfPlayers.getSelectedItem();
				}
			}
			
		}); 
		
		back.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ModeSelectionPage().setVisible(true);
			}
			
		});
		
		
	}
	
	public void close()
	{
		this.setVisible(false);
	}
	
	public void refreshData()
	{
		teamName.setText("");
		playerName.setText("");
		listOfTeams_addPlayer.setSelectedItem(select);
		listOfTeams_deleteTeam.setSelectedItem(select);
		listOfTeams_deletePlayer.setSelectedItem(select);
		listOfPlayers.setSelectedItem(select);
		errorLabel.setText("");
		addPlayer_choice = "";
		deleteTeam_choice = "";
		
	}
}
