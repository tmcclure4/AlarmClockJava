package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;

public class HistoryGUI extends JFrame{

	private ArrayList<AlarmClock> alarmList;
	
	private JPanel mainJPanel = new JPanel();
	private AlarmGUI alarmGui;
	
	private JPanel mainPanel = new JPanel();
	private JPanel northPanel = new JPanel();
	private JPanel southPanel = new JPanel();
	
	private JCheckBox test;
	private JCheckBox test4 = new JCheckBox("Alarm Name");
	private JCheckBox test3 = new JCheckBox("Alarm Name");
	private JCheckBox test2 = new JCheckBox("Alarm Name");
	private JCheckBox test1 = new JCheckBox("Alarm Name");
	
	
	private JTextField frameTitle = new JTextField("Alarm Clock History");
	private JButton backToAlarm = new JButton("Return to Clock Input");
	private Border borderRegion = BorderFactory.createLineBorder(Color.RED,1);

	
	public HistoryGUI(ArrayList<AlarmClock> tempAlarmList){
		
		alarmList=tempAlarmList;//get the alarm list to the gui
		
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		northPanel.setBorder(borderRegion);
		northPanel.setPreferredSize(new Dimension(200,600));
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
			
		southPanel.setBorder(borderRegion);
		southPanel.setPreferredSize(new Dimension(200,50));
		southPanel.add(backToAlarm);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		if(alarmList.size()!=0){
			JCheckBox tempCheckBox = new JCheckBox(alarmList.get(0).getAlarmName());
			northPanel.add(tempCheckBox);
		}
		
		pack();
		setVisible(true);
		
		backToAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AlarmGUI(alarmList);
				setVisible(false);
				
			}
		});
	}
	
	/*public void setAlarmGui(AlarmGUI tempAlarmGui){
		alarmGui=tempAlarmGui;
	}*/
	
	
}

/**
 * mainFrame.setVisible(false);
		mainFrame.setSize(500, 500);
		mainFrame.setTitle(frameTitle.getText());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainJPanel.add(backToAlarm);
		mainFrame.add(mainJPanel);
 */



