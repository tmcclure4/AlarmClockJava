package alarmClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AlarmGUI {
	//set arrays for the combo box
	String[] hour = {"1","2","3","4","5","6","7","8","9","10","11","12"};
	String[] tenths = {"0","1","2","3","4","5"};
	String[] minutes = {"0","1","2","3","4","5","6","7","8","9"};
	String[] AMorPM = {"AM", "PM"};

	JComboBox comboBoxHour = new JComboBox(hour);
	JComboBox comboBoxTenths = new JComboBox(tenths);
	JComboBox comboBoxMinutes = new JComboBox(minutes);
	JComboBox comboBoxAMorPM = new JComboBox<>(AMorPM);
	
	JButton button = new JButton("Submit");
	JLabel label = new JLabel("TEST 1 2 3");
	
	
	//constructor
	public AlarmGUI(){
		frame();
	}
	
	
	/**
	 * This function initializes the frame to be used.
	 */
	public void frame(){
		//add a frame
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		//add combo box, button, and label to Panel
		panel.add(comboBoxHour);
		panel.add(comboBoxTenths);
		panel.add(comboBoxMinutes);
		panel.add(button);
		panel.add(label);
		
		frame.add(panel);//add JPanel to the JFrame	
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tempHour=comboBoxHour.getSelectedItem().toString();
				label.setText(tempHour);
				
			}
		});
	}

}


