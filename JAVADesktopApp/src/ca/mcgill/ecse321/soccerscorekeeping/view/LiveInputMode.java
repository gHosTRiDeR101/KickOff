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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;

public class LiveInputMode extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684378744229639537L;
	
	private Match match;
	
	private JPanel mainPanel;
	private JPanel topPanel;
	
	private JComboBox<String> teamList;
	private JComboBox<String> playerList;
	private JComboBox<String> infractionType;
	private JComboBox<String> isPenalty;
	private JComboBox<String> isGoal;
	
	private JLabel score;
	private JLabel errorLabel;
	private JLabel infraction;
	private JLabel shot;
	private JLabel teamName;
	private JLabel playerName;
	private JLabel type;
	private JLabel penalty;
	private JLabel goal;
	
	private JButton addInfraction;
	private JButton addShot;
	private JButton endMatch;
	
	private String select = " -Select- ";
	
	public LiveInputMode(Match m)
	{
		match=m;
		initializeComponents();
	}
	
	public void refreshData()
	{
		errorLabel.setText("");
		teamList.setSelectedItem(select);
		playerList.removeAllItems();
		playerList.addItem(select);
		playerList.setSelectedItem(select);
		infractionType.setSelectedItem(select);
		isPenalty.setSelectedItem(select);
		isGoal.setSelectedItem(select);
		score.setText(match.getTeam(0).getName()+" "+match.getGoals1()
				+"-"+match.getGoals2()+" "+match.getTeam(1).getName());
	}
	
	public void initializeComponents()
	{
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000,500);
		setResizable(true);
		setTitle("Live Input Mode");
		
		//Set Location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//Initialize Components
		mainPanel = new JPanel(new GridBagLayout());
		this.getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		topPanel = new JPanel(new GridBagLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		
		teamList = new JComboBox<String>();
		playerList = new JComboBox<String>();
		infractionType = new JComboBox<String>();
		isPenalty = new JComboBox<String>();
		isGoal = new JComboBox<String>();
		
		score = new JLabel(match.getTeam(0).getName()+" "+match.getGoals1()+
				"-"+match.getGoals2()+" "+match.getTeam(1).getName());
		score.setFont(new Font("Segoe UI Light",Font.BOLD,20));
		errorLabel = new JLabel("");
		infraction = new JLabel("INFRACTION");
		infraction.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		shot = new JLabel("SHOT");
		shot.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		teamName = new JLabel("Team Name: ");
		teamName.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		playerName = new JLabel("Player Name:");
		playerName.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		type = new JLabel("		Booking Type:");
		type.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		penalty = new JLabel("		Penalty Kick:");
		penalty.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		goal = new JLabel("Goal");
		goal.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		addInfraction = new JButton("Add Infraction");
		addShot = new JButton("Add Shot");
		endMatch = new JButton("End Match");
		addInfraction.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		addShot.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		endMatch.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		//Populate Combo Boxes
		teamList.addItem(select);
		teamList.addItem(match.getTeam(0).getName());
		teamList.addItem(match.getTeam(1).getName());
		
		playerList.addItem(select);
		playerList.setSelectedItem(select);
		
		infractionType.addItem(select);
		infractionType.addItem("YELLOW");
		infractionType.addItem("RED");
		
		isPenalty.addItem(select);
		isPenalty.addItem("True");
		isPenalty.addItem("False");
		
		isGoal.addItem(select);
		isGoal.addItem("True");
		isGoal.addItem("False");
		
		//Design the JFrame
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		gbc.gridx=0;gbc.gridy=0;
		mainPanel.add(errorLabel,gbc);
		
		gbc.gridx=0;gbc.gridy=2;
		mainPanel.add(teamName,gbc);
		gbc.gridx++;
		mainPanel.add(teamList,gbc);
		
		gbc.gridx=0;gbc.gridy=3;
		mainPanel.add(playerName,gbc);
		gbc.gridx++;
		mainPanel.add(playerList,gbc);
		
		gbc.gridx=6;gbc.gridy=1;
		mainPanel.add(infraction,gbc);

		gbc.gridx+=2;
		mainPanel.add(shot,gbc);
		
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor=GridBagConstraints.CENTER;
		gbc.gridx=5;gbc.gridy++;
		mainPanel.add(type,gbc);
		gbc.gridx++;
		mainPanel.add(infractionType,gbc);
		gbc.gridx++;
		mainPanel.add(goal,gbc);
		gbc.gridx++;
		mainPanel.add(isGoal,gbc);
		
		gbc.gridx=5;gbc.gridy++;
		mainPanel.add(penalty,gbc);
		gbc.gridx++;
		mainPanel.add(isPenalty,gbc);
		
		gbc.gridx=6;gbc.gridy++;
		mainPanel.add(addInfraction,gbc);
		gbc.gridx=8;
		mainPanel.add(addShot,gbc);
		
		gbc.gridx+=2;
		gbc.gridy+=3;
		mainPanel.add(endMatch,gbc);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx=4;c.gridy=0;
		c.anchor=GridBagConstraints.CENTER;
		mainPanel.add(score,c);
		score.setForeground(Color.BLUE);
		
		//Set Listeners
		teamList.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(!(((String)teamList.getSelectedItem()).equals(select)))
				{
					playerList.removeAllItems();
					playerList.addItem(select);
					playerList.setSelectedItem(select);
					errorLabel.setText("");
					
					if(((String)teamList.getSelectedItem()).equals(match.getTeam(0).getName()))
					{
						for(Player player : match.getTeam(0).getPlayers())
						{
							playerList.addItem(player.getName());
						}
					}
					else
					{
						for(Player player : match.getTeam(1).getPlayers())
						{
							playerList.addItem(player.getName());
						}
					}
				}
			}
			
		});
		
		addInfraction.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e) 
			{
				if(((String)infractionType.getSelectedItem()).equals(select) || ((String)isPenalty.getSelectedItem()).equals(select))
				{
					errorLabel.setText("Required Fields are Empty");
					errorLabel.setForeground(Color.RED);
				}
				else
				{
					String type = (String)infractionType.getSelectedItem();
					boolean penalty;
					if(((String)isPenalty.getSelectedItem()).equals("True"))
					{
						penalty=true;
					}
					else
					{
						penalty=false;
					}
					
					controller c = new controller();
					String err = c.createInfraction((String)playerList.getSelectedItem(), match, type, penalty);
					
					if(err==null)
					{
						refreshData();
					}
					else
					{
						errorLabel.setText(err);
						errorLabel.setForeground(Color.RED);
					}
				}
			}
			
		});
		
		addShot.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(((String)isGoal.getSelectedItem()).equals(select))
				{
					errorLabel.setText("Required Fields are Empty");
					errorLabel.setForeground(Color.RED);
				}
				else
				{
					boolean goal;
					if(((String)isGoal.getSelectedItem()).equals("True"))
					{
						goal=true;
					}
					else
					{
						goal=false;
					}
					
					controller c = new controller();
					String err = c.createShot((String)playerList.getSelectedItem(), match, goal);
					
					if(err==null)
					{
						refreshData();
					}
					else
					{
						errorLabel.setText(err);
						errorLabel.setForeground(Color.RED);
					}
				}
			}
			
		});
		
		endMatch.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				controller c = new controller();
				c.chooseWinner(match);
				close();
				new ModeSelectionPage().setVisible(true);
			}
			
		});
		
	}
	
	public void close()
	{
		this.setVisible(false);
	}

}
