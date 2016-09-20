package ca.mcgill.ecse321.soccerscorekeeping.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.event.WindowEvent;
import java.io.*;

import ca.mcgill.ecse321.soccerscorekeeping.model.Manager;
import ca.mcgill.ecse321.soccerscorekeeping.persistence.PersistenceSoccerScoreKeeping;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class ModeSelectionPage extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6358380156573929337L;
	
	private JButton managerLoginButton;
	private JButton liveInputModeButton;
	private JButton batchInputModeButton;
	private JButton playerAnalysisButton;
	private JButton leagueAnalysisButton;
	private JLabel chooseModeLabel;
	private JPanel mainPanel;
	private JPanel panelButtons;

	private JLabel appName;

	/*
	 * HTTP CLIENT VARIABLES
	 */

	private CloseableHttpClient httpclient;
	final private String uploadUrl = "http://cs.mcgill.ca/~jselwy/XMLUpload.php";
	final private String downloadUrl = "http://cs.mcgill.ca/~jselwy/soccer.xml";
	

	
	public ModeSelectionPage()
	{
		initializeComponents();
	}

	private void initMenu() {
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);

		JMenuItem downloadAction = new JMenuItem("Download Scores");
		JMenuItem uploadAction = new JMenuItem("Upload Scores");
		JMenuItem exitAction = new JMenuItem("Exit");

		fileMenu.add(downloadAction);
		fileMenu.add(uploadAction);
		fileMenu.add(exitAction);

		downloadAction.addActionListener(actionEvent2 -> {
			downloadScores();
		});

		uploadAction.addActionListener(actionEvent1 -> {
			uploadScores();
		});

		exitAction.addActionListener(actionEvent -> {
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		});

	}
	
	private void initializeComponents()
	{		
		// Init menu
		initMenu();

		//Initialize components
		managerLoginButton = new JButton();
		liveInputModeButton = new JButton();
		batchInputModeButton = new JButton();
		playerAnalysisButton = new JButton();
		leagueAnalysisButton = new JButton();
		chooseModeLabel = new JLabel();
		appName = new JLabel("KickOff!!");
		appName.setFont(new Font("Segoe UI Light",Font.BOLD,25));
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel);
		
		panelButtons = new JPanel(new GridBagLayout());
		mainPanel.add(panelButtons, BorderLayout.WEST);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(10,10,10,10);
		
		
		c.gridx=0;
		c.gridy=0;
		
		panelButtons.add(appName,c);
		
		c.gridy++;
		panelButtons.add(chooseModeLabel,c);
		c.gridy++;
		
		panelButtons.add(managerLoginButton,c);
		c.gridy++;
		
		panelButtons.add(liveInputModeButton,c);
		c.gridy++;
		
		panelButtons.add(batchInputModeButton,c);
		c.gridy++;
		
		panelButtons.add(playerAnalysisButton,c);
		c.gridy++;
		
		panelButtons.add(leagueAnalysisButton,c);
		c.gridy++;
		
		//Global settings
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("KickOff");
		setSize(500,500);
		
		//Set Location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		//Set text
		chooseModeLabel.setText("Please Select: ");
		managerLoginButton.setText("Login as Manager");
		liveInputModeButton.setText("Live Input");
		batchInputModeButton.setText("Batch Input");
		playerAnalysisButton.setText("Player Analysis");
		leagueAnalysisButton.setText("League Analysis");
		
		chooseModeLabel.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		managerLoginButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		liveInputModeButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		batchInputModeButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		playerAnalysisButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		leagueAnalysisButton.setFont(new Font("Segoe UI Light",Font.PLAIN,15));
		
		//Add Listeners
		managerLoginButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ManagerPage(0).setVisible(true);
			}
			
		});
		
		liveInputModeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ManagerPage(1).setVisible(true);
				//new CreateMatchPage().setVisible(true);
			}
			
		});
		
		batchInputModeButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new ManagerPage(2).setVisible(true);
				//new CreateMatchPage().setVisible(true);
			}
			
		});
		
		playerAnalysisButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new PlayerAnalysisMode().setVisible(true);
			}
			
		});
		
		leagueAnalysisButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				close();
				new LeagueAnalysisMode().setVisible(true);
			}
			
		});
		
		
	}

	private void downloadScores() {
		try {
			String xml = Request.Get(downloadUrl).execute().returnContent().toString();

			FileWriter fw = new FileWriter(new File("soccerscores.xml"));
			fw.write(xml);
			fw.close();

			Manager m = Manager.getInstance();
			m.delete();

			PersistenceSoccerScoreKeeping.loadSoccerScores();

			JOptionPane.showMessageDialog(this, "Scores Downloaded");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Download failed. Please check your internet connection.");
		}
	}

	private void uploadScores() {
		httpclient = HttpClientBuilder.create().build();
		HttpPost httppost = new HttpPost(uploadUrl);

		try {
			FileBody bin = new FileBody(new File("soccerscores.xml"));

			MultipartEntityBuilder reqEntity = MultipartEntityBuilder.create();
			reqEntity.addPart("upload", bin);

			HttpEntity entity = reqEntity.build();
			httppost.setEntity(entity);

			System.out.println("Requesting : " + httppost.getRequestLine());
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			String responseBody = httpclient.execute(httppost, responseHandler);

			System.out.println("responseBody : " + responseBody);
			httpclient.close();

			JOptionPane.showMessageDialog(this, "Scores Uploaded");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Upload failed. Please check your internet connection.");
		}

	}
	
	private void close()
	{
		this.setVisible(false);
	}
	
}
