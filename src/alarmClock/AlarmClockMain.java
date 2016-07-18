package alarmClock;

import javax.swing.JFrame;
import java.util.*;

public class AlarmClockMain 
{
	private static ArrayList<AlarmClock> alarmList = new ArrayList<AlarmClock>();
	//private static AlarmGUI alarmGui;
	//private static HistoryGUI historyGui;
	
	public static void main(String[] args){
		/*AlarmClock temp = new AlarmClock("Hello21", "whats up", "1:00 AM", alarmList);
		AlarmClock temp1 = new AlarmClock("Hello31", "whats up yea", "1:00 AM",alarmList);
		AlarmClock temp2 = new AlarmClock("Hello11", "whats", "1:00 AM",alarmList);
		AlarmClock temp3 = new AlarmClock("Hello41", "whats up he he", "1:00 AM",alarmList);
		AlarmClock temp4 = new AlarmClock("Hello51", "whats up he he he", "1:00 AM",alarmList);
		AlarmClock temp5 = new AlarmClock("Hello22", "whats up", "1:00 AM",alarmList);
		AlarmClock temp6 = new AlarmClock("Hello32", "whats up he", "1:00 AM",alarmList);
		AlarmClock temp7 = new AlarmClock("Hello12", "whats", "1:00 AM",alarmList);
		*/
		
		//get the corresponding objects to the other object so they can switch back and forth
		new AlarmGUI(alarmList);
		//historyGui = new HistoryGUI(alarmList);
		
		//alarmGui.setHistoryGui(historyGui);
		//historyGui.setAlarmGui(alarmGui);
		
	}
	

}
