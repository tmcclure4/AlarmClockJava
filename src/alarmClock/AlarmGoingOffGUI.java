package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Desktop;

public class AlarmGoingOffGUI extends JFrame{

	private JPanel mainPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	private JButton finishButton = new JButton("Close Window");
	private JLabel alarmInfo;
	
	
	AlarmGoingOffGUI(String alarmName, String alarmTime, String tempAlarmURL) throws IOException, URISyntaxException{
		super("Alarm is going off");
		String tempAlarmInfo = alarmName + " " + alarmTime;
		setUpGUI(tempAlarmInfo,tempAlarmURL);
		
	}
	
	public void setUpGUI(String alarmInfoLabel, String alarmURL) throws IOException, URISyntaxException{
		alarmInfo = new JLabel(alarmInfoLabel);
		
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		southPanel.setPreferredSize(new Dimension(200,100));
		southPanel.add(finishButton);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		northPanel.setPreferredSize(new Dimension(200,100));
		northPanel.add(alarmInfo);
		mainPanel.add(northPanel, BorderLayout.NORTH);

		pack();
		setVisible(true);
		Desktop d = Desktop.getDesktop();
		try{
			d.browse(new URI(alarmURL));
		}
		catch(IOException e){
			d.browse(new URI("https://www.youtube.com/watch?v=6GUm5g8SG4o"));
		}
		
//		d.browse(new URI("https://www.youtube.com/watch?v=6GUm5g8SG4o"));

		
		
		finishButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				setVisible(false);				
			}
		});
	}
	
}
