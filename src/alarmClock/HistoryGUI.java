package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.*;

public class HistoryGUI extends JFrame{

	//the array lists for the clocks
	private static ArrayList<AlarmClock> alarmList;
	private static ArrayList<AlarmClock> mainAlarmList;
	

	//initialzes all jpanels
	private JPanel mainPanel = new JPanel();
	private JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	
	//deals with the south panel/buttons
	private JTextField frameTitle = new JTextField("Alarm Clock History");
	private JButton backToAlarm = new JButton("Return to Clock Input");
	private JPanel southPanel = new JPanel();
	
	private Border borderRegion = BorderFactory.createLineBorder(Color.RED,1);
	
	private ArrayList<JCheckBox> tempJBox = new ArrayList<JCheckBox>();//stores the check box values
	
	public HistoryGUI(ArrayList<AlarmClock> tempAlarmList, ArrayList<AlarmClock> tempMainAlarmList){
		
		super("Alarm Clock History");
		alarmList=tempAlarmList;
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
		
		//set up the alarm check box and the delete check box for every alarm 
		for(Iterator<AlarmClock> mainAlarmListIt = mainAlarmList.iterator();mainAlarmListIt.hasNext();){
			AlarmClock tempAlarmClock=mainAlarmListIt.next();
			JCheckBox isActiveCheckBox = new JCheckBox(tempAlarmClock.getFullAlarmInfo());
			JCheckBox deleteAlarmCheckBox = new JCheckBox("Delete");
			
			leftPanel.add(isActiveCheckBox);
			rightPanel.add(deleteAlarmCheckBox);
			
			if(tempAlarmClock.isAlarmActive()){//if alarm is active then set the check box
				isActiveCheckBox.setSelected(true);
			}
			
			//add the check boxes into an array list
			tempJBox.add(isActiveCheckBox);
			tempJBox.add(deleteAlarmCheckBox);
		}
		
		pack();
		setVisible(true);
		
		
		/**
		 * The action listener looks at the check boxes and does the following:
		 * 1. If the alarm check box is checked then it will set whether the alarm is active or not.
		 * 2. If the delete check box is checked then it will delete the alarm from the alarm list.
		 * 
		 * tempJBox-stores the check boxes so it can be iterated in the following way: 
		 * Even index (starting at 0) is the check box that decides whether the alarm is active or not.
		 * Odd index is the check box that decides whether the alarm needs to be deleted or not. 
		 */
		backToAlarm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int comboCounter=0;//keeps track on the index of the array list
				int JBoxCounter=0;//keeps the index of the main array list of alarm clock
				
				for(JCheckBox JBoxArray:tempJBox){
					if(comboCounter%2==0){//only look at even index's, whether the alarm is active or not
						mainAlarmList.get(JBoxCounter).setActiveOrNot(JBoxArray.isSelected());//SET THE ALARM CLOCK TO ACTIVE OR NOT
						JBoxCounter++;
					}
					comboCounter++;
				}
				
				JBoxCounter=0;//reset the index of the main alarm array list
				
				//This is not index based, so it the size of the tempJBox array list to 1.
				//Even numbers mean see whether the check box was selected to be deleted. 
				//When looking at the tempJBox array list, must subtract 1 to account for the index.
				//If the check box was set to be deleted, set the value in the alarm clock to know it must be deleted.
				for(int tempCounter = tempJBox.size();tempCounter>0;tempCounter--){//reversely transverse the combo box array list
					
					if(tempCounter%2==0){
						if(tempJBox.get(tempCounter-1).isSelected()){//if the user wants the alarm to be deleted
							mainAlarmList.get(tempCounter/2-1).setToDeleteAlarm();
							//System.out.println("Removed Alarm indexed at: " + Integer.toString(tempCounter/2-1));
						}
						
					}
				}
				
				new AlarmGUI(alarmList, false, false, mainAlarmList);
				setVisible(false);
				
			}
		});
	}
	
	
	
}

