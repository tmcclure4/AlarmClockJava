package alarmClock;

import javax.swing.JFrame;

public class AlarmClockMain 
{
	private static AlarmGUI alarmGui = new AlarmGUI();
	private static HistoryGUI historyGui = new HistoryGUI();
	
	public static void main(String[] args){
		
		//get the corresponding objects to the other object so they can switch back and forth
		alarmGui.setHistoryGui(historyGui);
		historyGui.setAlarmGui(alarmGui);
		
	}

}
