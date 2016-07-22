package alarmClock;

import javax.swing.JFrame;
import java.util.*;

public class AlarmClockMain 
{
	private static ArrayList<AlarmClock> alarmList = new ArrayList<AlarmClock>();
	
	public static void main(String[] args){
		
		new AlarmGUI(alarmList, false,false);
		
		
	}
	

}
