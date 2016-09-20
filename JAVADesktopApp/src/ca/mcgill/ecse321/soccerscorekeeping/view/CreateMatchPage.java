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
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class CreateMatchPage extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5951700961496457563L;
	
	private JComboBox<String> team1;
	private JComboBox<String> team2;
	private JButton addMatchButton;
	private JLabel team1Label;
	private JLabel team2Label;
	private JPanel mainPanel;
	private JLabel error;
	private String select= " -Select- ";
	private String selectedTeam1;
	private String selectedTeam2;
	private JButton back;
	private int chooseInput;
	
	public CreateMatchPage(int selectInputMode)
	{
		chooseInput=selectInputMode;
		initializeComponents();
		refreshData();
	}
	
	public void refreshData()
	{
		selectedTeam1="";
		selectedTeam2="";
		error.setText("");
		team1.setSelectedItem(select);
		team2.setSelectedItem(select);
		
	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
	public void initializeComponents()
	{	
		//initialize
		team1 = new JComboBox<String>();
		team2 = new JComboBox<String>();
		addMatchButton = new JButton();
		team1Label = new JLabel("Team 1: ");
		team1Label.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		team2Label = new JLabel("Team 2: ");
		team2Label.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		error = new JLabel("");
		mainPanel = new JPanel(new GridBagLayout());
		back = new JButton("Back");
		back.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		addMatchButton.setText("Add Match");
		addMatchButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Create Match");
		setSize(400,300);
		
		//Set Location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//Layout
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.LINE_END;
		
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		c.gridx=0;
		c.gridy=0;
		
		mainPanel.add(error,c);
		
		c.gridy++;
		mainPanel.add(team1Label,c);
		
		c.gridy++;
		mainPanel.add(team2Label,c);
		
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx=1;
		c.gridy=1;
		
		mainPanel.add(team1,c);
		
		c.gridy++;
		mainPanel.add(team2,c);
		
		c.gridy++;
		mainPanel.add(addMatchButton,c);
		
		c.gridy++;
		c.gridx++;
		mainPanel.add(back,c);
		
		//Populate JComboBoxes
		Manager m = Manager.getInstance();
		Iterator<Team> i = m.getTeams().iterator();
		while(i.hasNext())
		{
			Team t =i.next();
			team1.addItem(t.getName());
			team2.addItem(t.getName());
		}
		team1.addItem(select);
		team2.addItem(select);
		
		//Add Action Listeners
		team1.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(((String)team1.getSelectedItem()).equals(select))
				{
					error.setForeground(Color.black);
					error.setText("Please select a team.");
				}
				else
				{
					error.setText("");
					selectedTeam1 = (String)team1.getSelectedItem();
				}
			}
			
		});
		
		team2.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(((String)team2.getSelectedItem()).equals(select))
				{
					error.setForeground(Color.black);
					error.setText("Please select a team.");
				}
				else
				{
					error.setText("");
					selectedTeam2 = (String)team2.getSelectedItem();
				}
			}
			
		});
		
		addMatchButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(selectedTeam1.equals(selectedTeam2))
				{
					error.setForeground(Color.red);
					error.setText("Both teams cannot be the same.");
				}
				else
				{
					error.setText("");
					controller c = new controller();
					Match m = c.createMatch(selectedTeam1,selectedTeam2);
					close();
					
					if(chooseInput==1)
					{
						new LiveInputMode(m).setVisible(true);
					}
					else
					{
						new BatchInputMode(m).setVisible(true);
					}
				}
			}
			
		});
		
		back.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new ModeSelectionPage().setVisible(true);
			}
			
		});
		
	}
}
