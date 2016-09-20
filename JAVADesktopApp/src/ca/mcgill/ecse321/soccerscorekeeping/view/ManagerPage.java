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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import ca.mcgill.ecse321.soccerscorekeeping.admin.authentication;

public class ManagerPage extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1167738945720479651L;
	
	private JLabel enterPassword;
	private JLabel error;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JPanel mainPanel;
	private JPanel loginPanel;
	
	private JTextField userName;
	private JLabel enterUser;
	
	private JButton back;
	
	private int modeChoice;
	
	public ManagerPage(int modeSelect)
	{
		modeChoice=modeSelect;
		initializeComponents();
	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
	private void initializeComponents()
	{	
		//Initialize Components
		enterPassword = new JLabel("Password: ");
		enterPassword.setFont(new Font("Segoe UI Light", Font.PLAIN,15));
		error = new JLabel("");
		passwordField = new JPasswordField(20);
		loginButton = new JButton("Login");
		loginButton.setFont(new Font("Segoe UI Light", Font.PLAIN,15));
		
		enterUser = new JLabel("Username");
		enterUser.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		userName = new JTextField(20);
		
		back = new JButton("Back");
		back.setFont(new Font("Segoe UI Light", Font.PLAIN,15));
		
		mainPanel = new JPanel();
		this.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		loginPanel = new JPanel(new GridBagLayout());
		mainPanel.add(loginPanel);
		
		//Global Settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(true);
		setSize(600,300);
		
		if(modeChoice==0)
		{
			setTitle("Manager Login");
		}
		else
		{
			setTitle("Scorekeeper Login");
		}
		
		//Set Location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		//GridBagConstraints
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(20,20,20,20);
		c.gridx=1;
		c.gridy=0;
		
		loginPanel.add(error,c);
		
		c.gridx=0;
		c.gridy=4;
		loginPanel.add(enterUser,c);
		
		c.gridx++;
		loginPanel.add(userName,c);
		
		c.gridx=0;
		c.gridy++;
		loginPanel.add(enterPassword,c);
		
		c.gridx++;
		loginPanel.add(passwordField,c);
		
		c.gridx++;
		loginPanel.add(loginButton,c);
		
		c.gridy++;
		loginPanel.add(back,c);
		
		//Add Listener
		back.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ModeSelectionPage().setVisible(true);
			}
			
		});
		
		loginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{				
				authentication a = new authentication();
				if(modeChoice==0)
				{
					if(a.authenticate(userName.getText(),passwordField.getPassword()))
					{
						close();
						new ManagerTools().setVisible(true);
					}
					else
					{
					error.setForeground(Color.red);
					error.setText("The username/password is incorrect.");
					}
				}
				else
				{
					if(a.authenticateScoreKeeper(userName.getText(),passwordField.getPassword()))
					{
						close();
						new CreateMatchPage(modeChoice).setVisible(true);
					}
					else
					{
					error.setForeground(Color.red);
					error.setText("The username/password is incorrect.");
					}
				}
			}
			
		});
		
		
	}

}
