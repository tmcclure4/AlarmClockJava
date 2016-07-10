package alarmClock;

import javax.swing.*;

public class AlarmInformation {

	private String AlarmHour="";
	private String AlarmTenth="";
	private String AlarmMinute="";
	private String AlarmAMorPM ="";
	
	public AlarmInformation(String tempHour, String tempTenth, String tempMinute, String tempAMorPM){
		AlarmHour=tempHour;
		AlarmTenth=tempTenth;
		AlarmMinute=tempMinute;
		AlarmAMorPM=tempAMorPM;
	}
	
	public void PrintAlarmInfo(JLabel tempLabel){
		String tempString=AlarmHour+":"+AlarmTenth+AlarmMinute+ " " + AlarmAMorPM;
		tempLabel.setText(tempString);
	}
}
