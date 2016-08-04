package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.*;

public class HistoryGUI extends JFrame{

	private static ArrayList<AlarmClock> alarmList;
	private static ArrayList<AlarmClock> mainAlarmList;
	
	private JPanel mainJPanel = new JPanel();
	private AlarmGUI alarmGui;
	
	private JPanel mainPanel = new JPanel();
	
	private JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

	
	private JPanel southPanel = new JPanel();
	private JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	private JTextField frameTitle = new JTextField("Alarm Clock History");
	private JButton backToAlarm = new JButton("Return to Clock Input");
	private Border borderRegion = BorderFactory.createLineBorder(Color.RED,1);
	
	private ArrayList<JCheckBox> tempJBox = new ArrayList<JCheckBox>();//stores the check box values
	
	public HistoryGUI(ArrayList<AlarmClock> tempAlarmList, ArrayList<AlarmClock> tempMainAlarmList){
		
		super("Alarm Clock History");
		alarmList=tempAlarmList;//get the alarm list to the gui
		mainAlarmList=tempMainAlarmList;
		mainPanel.setLayout(new BorderLayout());
		setContentPane(mainPanel);
		
		//leftPanel.setBorder(borderRegion);
		leftPanel.setPreferredSize(new Dimension(225,500)); // 300 width, 500 height
		mainPanel.add(leftPanel, BorderLayout.WEST);
		
		//rightPanel.setBorder(borderRegion);
		rightPanel.setPreferredSize(new Dimension(75,500));
		mainPanel.add(rightPanel, BorderLayout.EAST);
		
		//southPanel.setBorder(borderRegion);
		southPanel.setPreferredSize(new Dimension(300,50));
		southPanel.add(backToAlarm);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		//int counter=0;
		//for(AlarmClock tempAlarmClock: mainAlarmList){
		for(Iterator<AlarmClock> it3 = mainAlarmList.iterator();it3.hasNext();){
			AlarmClock tempAlarmClock=it3.next();
			JCheckBox isActiveCheckBox = new JCheckBox(tempAlarmClock.getFullAlarmInfo());
			JCheckBox deleteAlarmCheckBox = new JCheckBox("Delete");
			leftPanel.add(isActiveCheckBox);
			rightPanel.add(deleteAlarmCheckBox);
			if(tempAlarmClock.isAlarmActive()){
				isActiveCheckBox.setSelected(true);
			}
			tempJBox.add(isActiveCheckBox);
			tempJBox.add(deleteAlarmCheckBox);
		}
		
		pack();
		setVisible(true);
		
		backToAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int comboCounter=0;
				int JBoxCounter=0;
				for(JCheckBox JBoxArray:tempJBox){//for loop for whether alarm is active or not
					if(comboCounter%2==0){
						mainAlarmList.get(JBoxCounter).setActiveOrNot(JBoxArray.isSelected());//SET THE ALARM CLOCK TO ACTIVE OR NOT
						JBoxCounter++;
					//	System.out.println(String.valueOf(JBoxArray.isSelected()));
					}
					comboCounter++;
				}
				JBoxCounter=0;//reset the counter
				for(int tempCounter = tempJBox.size();tempCounter>0;tempCounter--){//reverse transverse the combo box array list
					if(tempCounter%2==0){
						if(tempJBox.get(tempCounter-1).isSelected()){//if the user wants the alarm to be deleted
							mainAlarmList.get(tempCounter/2-1).setToDeleteAlarm();
							//mainAlarmList.remove(tempCounter/2-1);//remove the selected alarm
							System.out.println("Removed Alarm indexed at: " + Integer.toString(tempCounter/2-1));
							//System.out.println(String.valueOf(tempJBox.get(tempCounter-1).isSelected()));
						}
						
					}
				}
				
				new AlarmGUI(alarmList, false, false, mainAlarmList);
				setVisible(false);
				
			}
		});
	}
	
	
	
}

