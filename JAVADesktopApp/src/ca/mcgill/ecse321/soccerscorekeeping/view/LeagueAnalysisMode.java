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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class LeagueAnalysisMode extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177448829322908181L;
	
	private JPanel mainPanel;
	
	private JLabel errorLabel;
	private JLabel sortPlayers;
	private JLabel topNumber;
	
	private JComboBox<String> choiceSort;
	private JComboBox<Integer> rankingNumber;
	
	private JButton viewRankings;
	private JButton back;
		
	private JTextArea textArea;
	
	private String select = " -Select- ";
	
	public LeagueAnalysisMode()
	{
		initializeComponents();
	}
	
	public void close()
	{
		this.setVisible(false);
	}
	
	public void initializeComponents()
	{
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("League Analysis Mode");
		setResizable(true);
		setSize(800,600);
		
		//Set location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//Init Components
		mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel,BorderLayout.NORTH);
		
		errorLabel = new JLabel("");
		sortPlayers = new JLabel("Sort League By: ");
		sortPlayers.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		topNumber = new JLabel("View Top Teams: ");
		topNumber.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		choiceSort = new JComboBox<String>();
		choiceSort.addItem(select);
		choiceSort.addItem("Points");
		choiceSort.addItem("Infractions");
		choiceSort.addItem("Goals");
		choiceSort.setSelectedItem(select);
		
		rankingNumber = new JComboBox<Integer>();
		rankingNumber.addItem(3);
		rankingNumber.addItem(5);
		rankingNumber.addItem(10);

		
		viewRankings = new JButton("View Rankings");
		viewRankings.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		back = new JButton("Back");
		back.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		textArea = new JTextArea();
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(600,400));
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx=0;c.gridy=0;
		mainPanel.add(errorLabel,c);
		
		c.gridy++;
		mainPanel.add(sortPlayers,c);
		c.gridx++;
		mainPanel.add(choiceSort,c);
		
		c.gridx++;
		mainPanel.add(topNumber,c);
		
		c.gridx++;
		mainPanel.add(rankingNumber,c);
		
		c.gridx++;
		mainPanel.add(viewRankings,c);
		
		JPanel centre = new JPanel(new GridBagLayout());
		getContentPane().add(centre,BorderLayout.CENTER);
		c.gridx=0;c.gridy=0;
		centre.add(scrollPane,c);
		
		c.gridx++;c.gridy++;
		centre.add(back,c);
		
		//Action Listeners
		back.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ModeSelectionPage().setVisible(true);
			}
			
		});
		
		viewRankings.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(((String)choiceSort.getSelectedItem()).equals(select))
				{
					errorLabel.setText("Please select a criteria to sort players.");
					errorLabel.setForeground(Color.RED);
				}
				else
				{
					errorLabel.setText("");
					controller c = new controller();
					
					if(((String)choiceSort.getSelectedItem()).equals("Goals"))
					{
						Team[] temp = c.topTeams(((Integer)rankingNumber.getSelectedItem()).intValue(),"Goals");
						String output = "TEAM	SHOTS	GOALS \n";
						for(int i=0;i<temp.length;i++)
						{
							output+=temp[i].getName()+"	"+temp[i].shotsTaken()+"	"+temp[i].goalsScored()+"\n";
						}
						textArea.setText(output);
					}
					else if(((String)choiceSort.getSelectedItem()).equals("Infractions"))
					{
						Team[] temp = c.topTeams(((Integer)rankingNumber.getSelectedItem()).intValue(),"Infractions");
						String output = "TEAM	INFRACTIONS	YELLOW	RED	PENALTY KICKS \n";
						for(int i=0;i<temp.length;i++)
						{
							output+=temp[i].getName()+"	"+temp[i].totalInfractions()+"	"+temp[i].yellowCards()+"	"+temp[i].redCards()+"	"+temp[i].penaltyKicks()+"\n";
						}
						textArea.setText(output);
					}
					else
					{
						Team[] temp = c.topTeams(((Integer)rankingNumber.getSelectedItem()).intValue(),"Points");
						String output = "TEAM	POINTS\n";
						for(int i=0;i<temp.length;i++)
						{
							output+=temp[i].getName()+"	"+temp[i].getPoints()+"\n";
						}
						textArea.setText(output);
					}
				}
			}
		});	
	}
}