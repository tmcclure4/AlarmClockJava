package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class HistoryGUI extends JFrame{


	private JPanel mainJPanel = new JPanel();
	private AlarmGUI alarmGui;
	
	private JPanel mainPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	private JTextField frameTitle = new JTextField("Alarm Clock History");
	private JButton backToAlarm = new JButton("Return to Clock Input");
	
	private Border borderRegion = BorderFactory.createLineBorder(Color.RED,1);

	
	public HistoryGUI(){
		
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		northPanel.setBorder(borderRegion);
		northPanel.setPreferredSize(new Dimension(350,50));
		//northPanel.add(backToAlarm);
		mainPanel.add(northPanel, BorderLayout.NORTH);
			
		southPanel.setBorder(borderRegion);
		southPanel.setPreferredSize(new Dimension(350,50));
		southPanel.add(backToAlarm);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		pack();
		
		backToAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				alarmGui.frame();
				
			}
		});
	}
	
	public void setAlarmGui(AlarmGUI tempAlarmGui){
		alarmGui=tempAlarmGui;
	}
	
	public void callHistoryGui(){
		setVisible(true);
	}
	
}

/**
 * mainFrame.setVisible(false);
		mainFrame.setSize(500, 500);
		mainFrame.setTitle(frameTitle.getText());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainJPanel.add(backToAlarm);
		mainFrame.add(mainJPanel);
 */



