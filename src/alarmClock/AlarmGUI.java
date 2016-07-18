package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.border.*;


//how to set the background to a certain color
//look at the border issue
public class AlarmGUI extends JFrame{
	
	//stores the list of alarms
	private ArrayList<AlarmClock> alarmList;// = new ArrayList<AlarmClock>();
		
	//private HistoryGUI historyGui;
	private JPanel mainPanel = new JPanel();
	
	//set up the north JPanel/name box
	private JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JLabel nameLabel = new JLabel("Enter the alarm name:");
	private JTextField alarmName = new JTextField("Alarm");
	
	//create center JPanel with combo box
	private String[] hour = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	private String[] tenths = {"0","1","2","3","4","5"};
	private String[] minutes = {"0","1","2","3","4","5","6","7","8","9"};
	private String[] AMorPM = {"AM", "PM"};
	private JComboBox comboBoxHour = new JComboBox(hour);
	private JComboBox comboBoxTenths = new JComboBox(tenths);
	private JComboBox comboBoxMinutes = new JComboBox(minutes);
	private JComboBox comboBoxAMorPM = new JComboBox<>(AMorPM);
	private JPanel middlePanel = new JPanel();
	private JLabel colonLabel = new JLabel(":");
	
	
	//create west JPanel and the check box's
	private JPanel westPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JCheckBox repeatNever = new JCheckBox("Never");
	private JCheckBox sunday = new JCheckBox("Every Sunday");
	private JCheckBox monday = new JCheckBox("Every Monday");
	private JCheckBox tuesday = new JCheckBox("Every Tuesday");
	private JCheckBox wednesday = new JCheckBox("Every Wednesday");
	private JCheckBox thursday = new JCheckBox("Every Thursday");
	private JCheckBox friday = new JCheckBox("Every Friday");
	private JCheckBox saturday = new JCheckBox("Every Saturday");
	private JCheckBox everyday = new JCheckBox("Everyday");
	private JLabel labelCheckBox = new JLabel("Repeat the Alarm:");
	
	//create south JPanel and the buttons associated with it
	private JPanel southPanel = new JPanel();
	private JButton submitButton = new JButton("Submit");
	private JButton historyButton = new JButton("Review Alarms");
	
	private Border borderContents = BorderFactory.createEmptyBorder(10,10,10,10);
	private Border borderRegion = BorderFactory.createLineBorder(Color.RED,1);
	private Color colorContents = Color.WHITE;
	private Font fontLabels = new Font(Font.DIALOG,Font.BOLD,16);
	
	//constructor
	public AlarmGUI(ArrayList<AlarmClock> tempAlarmList){
		super("Alarm Clock");//put text on the top of the gui
		alarmList = tempAlarmList;
		frame();//start the gui
	}

	/**
	 * Set the historyGui object to the one created by main so the two gui's
	 * will be synchronized
	 * @param tempHistGui-the HistoryGUI created by main
	 */
	/*public void setHistoryGui(HistoryGUI tempHistGui){
		historyGui=tempHistGui;
	}*/

	
	
	
	/**
	 * This function initializes the frame to be used.
	 */
	public void frame(){
		
		//add the main panel
		mainPanel.setLayout(new BorderLayout());
		//mainFrame.setBorder(borderContents);
		//mainFrame.setBackground(colorContents);
		setContentPane(mainPanel);
		
		//set up the north panel
		//northPanel.setBorder(borderRegion);
		northPanel.setPreferredSize(new Dimension(350,30));
		northPanel.add(nameLabel);
		northPanel.add(alarmName);
		alarmName.setColumns(19);//set length of text field 
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		
		//middle frame/select the time
		middlePanel.add(comboBoxHour);
		middlePanel.add(colonLabel);
		middlePanel.add(comboBoxTenths);
		middlePanel.add(comboBoxMinutes);
		middlePanel.add(comboBoxAMorPM);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
		
		//set the west JPanel/when the alarm wants to go off
		//westPanel.setBorder(borderRegion);
		westPanel.setPreferredSize(new Dimension(150,275));
		westPanel.add(labelCheckBox);
		westPanel.add(repeatNever);
		westPanel.add(everyday);
		westPanel.add(sunday);
		westPanel.add(monday);
		westPanel.add(tuesday);
		westPanel.add(wednesday);
		westPanel.add(thursday);
		westPanel.add(friday);
		westPanel.add(saturday);
		mainPanel.add(westPanel, BorderLayout.WEST);
		repeatNever.setSelected(true);//never is default for check box
		
		//set the south JPanel/buttons
		//southPanel.setBorder(borderRegion);
		southPanel.setPreferredSize(new Dimension(350,50));
		southPanel.add(submitButton);
		southPanel.add(historyButton);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		
		
		pack();//pack the panels together
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		/*************************************************************************************
		  This is the start of the list of action listeners for all buttons and combo box's
		*************************************************************************************/
		
		/**
		 * This action listener deals with the "Submit" button. When submit is pushed, the alarm will be stored
		 * and go off when the selected time is reached.
		 */
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//get the time the alarm will go off into a string
				AlarmClock newAlarmClock = new AlarmClock(alarmName.getText(),readCheckBox(),readComboBox(),alarmList);
				setVisible(false);
				new AlarmGUI(alarmList);
				//get the value's from the combo boxes into a string
				
				//AlarmInformation tempAlarm=new AlarmInformation(tempHour, tempTenth, tempMinute, tempAMorPM);
				//tempAlarm.PrintAlarmInfo(label);
				//labelCheckBox.setText(readCheckBox());
			}
		});
		
		
		/**
		 * This action listener deals with the "Review Alarm" button. When this button is pushed, a screen
		 * will pop up showing the current alarms set.
		 */
		historyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new HistoryGUI(alarmList);
				setVisible(false);
				//historyGui.callHistoryGui();
			}
		});		
		
		/**
		 * This action listener deals with the "Never" check box.
		 * When the "Never" check box is pushed, it will erase all the other check box's
		 * so the "Never" check box is the only selected from the list.
		 * It will not let the user unselect "Never" check box. The only way for "Never" to be 
		 * unselected is when another check box is pushed.
		 */
		repeatNever.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isComboBoxClicked(0)){//if "Never" IS selected and others are selected, 
					sunday.setSelected(false);//then change rest of combo box's to false and "Never" to true
					monday.setSelected(false);
					tuesday.setSelected(false);
					wednesday.setSelected(false);
					thursday.setSelected(false);
					friday.setSelected(false);
					saturday.setSelected(false);
					everyday.setSelected(false);
					repeatNever.setSelected(true);
				}
				else{//else "Never is already selected and trying to make it false, can't happen. Keep it true
					repeatNever.setSelected(true);
				}
				
			}
		});
		
		/**
		 * Sunday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		sunday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(1)){//if sunday is the only combo box clicked, leave it clicked
					sunday.setSelected(true);
				}
			}
		});
		
		/**
		 * Monday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		monday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(2)){//if monday is the only combo box clicked, leave it clicked
					monday.setSelected(true);
				}
			}
		});
		
		/**
		 * Tuesday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		tuesday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(3)){//if tuesday is the only combo box clicked, leave it clicked
					tuesday.setSelected(true);
				}
			}
		});
		
		/**
		 * Wednesday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		wednesday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(4)){//if wednesday is the only combo box clicked, leave it clicked
					wednesday.setSelected(true);
				}
			}
		});
		
		/**
		 * Thursday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		thursday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(5)){//if thursday is the only combo box clicked, leave it clicked
					thursday.setSelected(true);
				}
			}
		});
		
		/**
		 * Friday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		friday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(6)){//if friday is the only combo box clicked, leave it clicked
					friday.setSelected(true);
				}
			}
		});
		
		/**
		 * Saturday
		 * If a check box is selected that isn't "Never" or "Everyday and "Never" or "Everyday" is selected, then set 
		 * "Never" and "Everyday" to false. If the only check box that is selected is clicked to turn off, leave it on since there must always
		 * be one check box selected.
		 */
		saturday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(repeatNever.isSelected()||everyday.isSelected()){
					repeatNever.setSelected(false);
					everyday.setSelected(false);
				}
				if(!isComboBoxClicked(7)){//if saturday is the only combo box clicked, leave it clicked
					saturday.setSelected(true);
				}
			}
		});
		
		/**
		 * This action listener deals with the "Everyday" combo box.
		 * When the "Everyday" combo box is pushed, it will erase all the other combo box's
		 * so the "Everyday" combo box is the only selected from the list.
		 */
		everyday.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isComboBoxClicked(8)){//if "Everyday" IS selected and others are selected, 
					sunday.setSelected(false);//then change rest of combo box's to false and "Never" to true
					monday.setSelected(false);
					tuesday.setSelected(false);
					wednesday.setSelected(false);
					thursday.setSelected(false);
					friday.setSelected(false);
					saturday.setSelected(false);
					repeatNever.setSelected(false);
					everyday.setSelected(true);
				}
				else{//else "Never is already selected and trying to make it false, can't happen. Keep it true
					everyday.setSelected(true);
				}
				
			}
		});
	}
	
	
	/**
	 * This function sees if any of the check box's are selected other than a certain check box.
	 * @return true if one check box is selected other than the selected check box. Otherwise false is returned 
	 * (More than one check box selected) 
	 * whichDay- The check box that was just selected.
	 * 0="Never", 1="Sunday", 2="Monday", 3="Tuesday", 4="Wednesday",
	 * 5="Thursday", 6="Friday", 7="Saturday", 8="Everyday"
	 */
	private boolean isComboBoxClicked(int whichDay){
		boolean oneComboSelected=false;
		switch(whichDay){
		case 0://"Never" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 1://"Sunday" is selected
			if(repeatNever.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 2://"Monday" is selected
			if(sunday.isSelected()||repeatNever.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 3://"Tuesday" is selected
			if(sunday.isSelected()||monday.isSelected()||repeatNever.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 4://"Wednesday" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||repeatNever.isSelected()||
					thursday.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 5://"Thursday" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					repeatNever.isSelected()||friday.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 6://"Friday" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||repeatNever.isSelected()||saturday.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 7://"Saturday" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||repeatNever.isSelected()||everyday.isSelected())
				oneComboSelected=true;
			break;
		case 8://"Everyday" is selected
			if(sunday.isSelected()||monday.isSelected()||tuesday.isSelected()||wednesday.isSelected()||
					thursday.isSelected()||friday.isSelected()||repeatNever.isSelected()||saturday.isSelected())
				oneComboSelected=true;
			break;
		}
		
		return oneComboSelected;
		
	}
	
	
	/**
	 * This function gets the values from the combo boxes and combines them into one string.
	 * @returns the combined string which corresponds to the alarm time. (hh:mm AM/PM)
	 */
	public String readComboBox(){
		String tempHour=comboBoxHour.getSelectedItem().toString();
		String tempTenth=comboBoxTenths.getSelectedItem().toString();
		String tempMinute=comboBoxMinutes.getSelectedItem().toString();
		String tempAMorPM=comboBoxAMorPM.getSelectedItem().toString();
		return (tempHour + ":" + tempTenth + tempMinute + " " + tempAMorPM);
	}

	
	/**
	 * This function combines the value from the check box's into one string.
	 * @return The combined combo box values into a single string.
	 *
	 * This function see's if the check box is selected. If it is selected it adds
	 * the abbreviation to the string, else nothing will be added to the string.
	 */
	public String readCheckBox(){
		String tempString="";
		
		//"Repeat Never" and "Everyday" will be the only one's selected if they return true
		if(repeatNever.isSelected()){
			tempString = "N";
		}
		else if(everyday.isSelected()){
			tempString = "E";
		}
		else{
			if(sunday.isSelected()){
				tempString+="Su ";
			}
			if(monday.isSelected()){
				tempString+="M ";
			}
			if(tuesday.isSelected()){
				tempString+="Tu ";
			}
			if(wednesday.isSelected()){
				tempString+="W ";
			}
			if(thursday.isSelected()){
				tempString+="Th ";
			}
			if(friday.isSelected()){
				tempString+= "F ";
			}
			if(saturday.isSelected()){
				tempString+= "Sa";
			}
		}
		
		return tempString;
	}
}

/**
 * public void frame(){
		//add a frame
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(4,10,20,20));
		
		panel.add(label2);
		panel.add(alarmName);
		//add combo box, button, and label to Panel
		panel.add(comboBoxHour);
		panel.add(comboBoxTenths);
		panel.add(comboBoxMinutes);
		panel.add(comboBoxAMorPM);
		panel.add(button);
		panel.add(label);
		
		panel.add(sunday);
		panel.add(monday);
		panel.add(tuesday);
		panel.add(wednesday);
		panel.add(thursday);
		panel.add(friday);
		panel.add(saturday);
		
		frame.add(panel);//add JPanel to the JFrame	
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tempHour=comboBoxHour.getSelectedItem().toString();
				String tempTenth=comboBoxTenths.getSelectedItem().toString();
				String tempMinute=comboBoxMinutes.getSelectedItem().toString();
				String tempAMorPM=comboBoxAMorPM.getSelectedItem().toString();
				boolean tempThing=sunday.isSelected();
				//AlarmInformation tempAlarm=new AlarmInformation(tempHour, tempTenth, tempMinute, tempAMorPM);
				//tempAlarm.PrintAlarmInfo(label);
				//label.setText(String.valueOf(tempThing));
				label.setText(alarmName.getText());
			}
		});
	}
*/