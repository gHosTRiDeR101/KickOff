package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse321.soccerscorekeeping.controller.controller;
import ca.mcgill.ecse321.soccerscorekeeping.model.Match;
import ca.mcgill.ecse321.soccerscorekeeping.model.Player;
import ca.mcgill.ecse321.soccerscorekeeping.model.Team;

public class BatchInputMode extends JFrame {

	/**
	 *
	 */

	private static final long serialVersionUID = -1358359784533408789L;

	private Match match;
	
	private String[] columnNames = {
			"Team",
			"Player",
			"Type",
			"Card Color",
			"Penalty Kick",
			"Goal"
	};
	private Object[][] tableData = {};

	private JPanel mainPanel;

	private JLabel pageTitle;
	private JLabel dataEntry;
	private JLabel teamName;
	private JLabel playerName;
	private JLabel cardColor;
	private JLabel penaltyKick;
	private JLabel shotOutcome;

	private JComboBox<String> teamList;
	private JComboBox<String> playerList;
	private JComboBox<String> colourList;
	private JComboBox<String> penaltyKickSelect;
	private JComboBox<String> shotOutcomeSelect;

	private JButton addInfraction;
	private JButton addShot;
	private JButton saveData;

	private JTable mainTable;

	private DefaultTableModel tableModel;

	private JScrollPane scrollPane;

	private String select_please = new String(" -Select- ");


	public BatchInputMode(Match m)
	{
		match=m;
		initializeComponents();
	}

	public void refreshData()
	{
		// Teams
		List<Team> teams = match.getTeams();
		Iterator<Team> itr = teams.iterator();

		teamList.removeAllItems();
		teamList.addItem(select_please);

		while (itr.hasNext()) {
			teamList.addItem(itr.next().getName());
		}

		teamList.setSelectedItem(select_please);

		// Players
		String teamText = (String) teamList.getPrototypeDisplayValue();
		Team selectedTeam = null;

		itr = teams.iterator();
		while(itr.hasNext()) {
			Team tempTeam = itr.next();
			if (tempTeam.getName().equals(teamText)) {
				selectedTeam = tempTeam;
			}
		}

		// Shots
		shotOutcomeSelect.removeAllItems();
		shotOutcomeSelect.addItem("Yes");
		shotOutcomeSelect.addItem("No");

		// Colours
		colourList.removeAllItems();
		colourList.addItem("Yellow");
		colourList.addItem("Red");

		// Penalty Kick
		penaltyKickSelect.removeAllItems();
		penaltyKickSelect.addItem("Yes");
		penaltyKickSelect.addItem("No");

	}

	public void initializeComponents()
	{
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setResizable(false);
		setTitle("Batch Input Mode");

		// Objects
		pageTitle = new JLabel("Match: " + match.getName());
		pageTitle.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		dataEntry = new JLabel("Data Entry Form");
		dataEntry.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		teamName = new JLabel("Team");
		teamName.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		playerName = new JLabel("Player");
		playerName.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		cardColor = new JLabel("Card Colour");
		cardColor.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		penaltyKick = new JLabel("Penalty Kick");
		penaltyKick.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		shotOutcome = new JLabel("Goal");
		shotOutcome.setFont(new Font("Segoe UI Light",Font.PLAIN,15));

		addInfraction = new JButton("Add Infraction");
		addInfraction.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		addShot = new JButton("Add Shot");
		addShot.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		saveData = new JButton("Save Data");
		saveData.setFont(new Font("Segoe UI Light",Font.PLAIN,15));

		teamList = new JComboBox<String>();
		teamList.setPrototypeDisplayValue(" <select team> ");
		playerList = new JComboBox<String>();
		playerList.setPrototypeDisplayValue("<select player>");
		playerList.setSelectedItem(select_please);
		colourList = new JComboBox<String>();
		colourList.setPrototypeDisplayValue("yellow    ");
		penaltyKickSelect = new JComboBox<String>();
		penaltyKickSelect.setPrototypeDisplayValue("yellow    ");
		shotOutcomeSelect = new JComboBox<String>();
		shotOutcomeSelect.setPrototypeDisplayValue("goal     ");

		tableModel = new DefaultTableModel(tableData, columnNames);
		mainTable = new JTable(tableModel);
		mainTable.setFillsViewportHeight(true);
		mainTable.setEnabled(false);

		mainPanel = new JPanel(new GridBagLayout());
		getContentPane().add(mainPanel, BorderLayout.NORTH);

		scrollPane = new JScrollPane(mainTable);
		scrollPane.setPreferredSize(new Dimension(800,425));

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(8, 8, 8, 8);

		c.gridx = 0; c.gridy = 0;
		mainPanel.add(pageTitle, c);

		c.gridy++;
		mainPanel.add(scrollPane, c);


		JPanel control = new JPanel(new GridBagLayout());
		getContentPane().add(control, BorderLayout.CENTER);

		c.gridx = 0; c.gridy = 0;
		control.add(teamName, c);

		c.gridx++;
		control.add(teamList, c);

		c.gridx++;
		control.add(cardColor, c);

		c.gridx++;
		control.add(colourList, c);


		c.gridx = 0; c.gridy = 1;
		control.add(playerName, c);

		c.gridx++;
		control.add(playerList, c);

		c.gridx++;
		control.add(penaltyKick, c);

		c.gridx++;
		control.add(penaltyKickSelect, c);

		//add the button
		c.gridy++;
		control.add(addInfraction, c);
		c.gridy--;

		c.gridx++;
		control.add(shotOutcome, c);

		c.gridx++;
		control.add(shotOutcomeSelect, c);

		//add the buttons
		c.gridy++;
		control.add(addShot, c);
		c.gridy++;
		control.add(saveData, c);
		c.gridy--;
		c.gridy--;


		refreshData();

		//Set Listeners
		teamList.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{

				playerList.removeAllItems();

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
				// Remove the select option from the "menu"
				teamList.removeItem(select_please);
			}

		});

		addInfraction.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Verify for empty player field
				if (teamList.getSelectedItem().equals("select")) {
					JOptionPane.showMessageDialog(getParent(), "ERROR: Select a team");

				}

				else {
					String team = (String) teamList.getSelectedItem();
					String player = (String) playerList.getSelectedItem();
					String infractionType = (String) colourList.getSelectedItem();
					String penaltyKick = (String) penaltyKickSelect.getSelectedItem();

					Object[][] tableDataTemp = tableData.clone();
					tableData = new Object[tableDataTemp.length + 1][6];

					for (int i = 0; i < tableDataTemp.length; i++) {
						tableData[i] = tableDataTemp[i];
					}

					// Add the new item
					String[] newItem = {
							team,
							player,
							"Infraction",
							infractionType,
							penaltyKick,
							""
					};
					tableData[tableData.length - 1] = newItem;

					tableModel = new DefaultTableModel(tableData, columnNames);
					mainTable.setModel(tableModel);
				}
			}

		});

		addShot.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Verify for empty player field
				if (teamList.getSelectedItem().equals("select")) {
					JOptionPane.showMessageDialog(getParent(), "ERROR: Select a team");

				}
				else {

					String team = (String) teamList.getSelectedItem();
					String player = (String) playerList.getSelectedItem();
					String shotType = (String) shotOutcomeSelect.getSelectedItem().toString();

					Object[][] tableDataTemp = tableData.clone();
					tableData = new Object[tableDataTemp.length + 1][6];

					for (int i = 0; i < tableDataTemp.length; i++) {
						tableData[i] = tableDataTemp[i];
					}

					// Add the new item
					String[] newItem = {
							team,
							player,
							"Shot on Goal",
							"",
							"",
							shotType
					};

					tableData[tableData.length - 1] = newItem;

					tableModel = new DefaultTableModel(tableData, columnNames);
					mainTable.setModel(tableModel);
				}
			}

		});

		saveData.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				for (int i = 0; i < tableData.length; i++) {
					// Parse the data
					// Infraction
					controller c = new controller();
					if (tableData[i][2].equals("Infraction")) {
						boolean penalty = false;

						if (tableData[i][4].equals("Yes")) {
							penalty = true;
						}

						// DEBUG
						// JOptionPane.showMessageDialog(getParent(), "Infraction added");

						c.createInfraction((String) tableData[i][1], match, (String) tableData[i][3], penalty);
					}

					// Shot
					else {
						boolean goal = false;

						if (tableData[i][5].equals("Yes")) {
							goal = true;
						}

						// DEBUG
						// JOptionPane.showMessageDialog(getParent(), "Shot added");

						c.createShot(tableData[i][1].toString(), match, goal);

					}

				}
				controller c = new controller();
				c.chooseWinner(match);
				JOptionPane.showMessageDialog(getParent(), "Data saved successfuly.");

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