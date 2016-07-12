package alarmClock;

public class AlarmClock {
	
	//private variables
	private String alarmName;
	private String dayAlarmGoesOff;
	private String alarmTime;
	private int alarmNumber;//stores the number of the alarm(i.e. 1,2,3...)
	
	public AlarmClock(String tempAlarmName, String alarmDate, String tempTime, int tempAlarmNumber){
		alarmName=tempAlarmName;
		dayAlarmGoesOff=alarmDate;
		alarmTime=tempTime;
		alarmNumber=tempAlarmNumber;
	}
	
	
	
	
	/**
	 * This returns the alarm name
	 * @return
	 */
	public String getAlarmName(){
		return alarmName;
	}
	
	/**
	 * This returns the days the alarm goes off
	 * @return
	 */
	public String getdayAlarmGoesOff(){
		return dayAlarmGoesOff;
	}
	
	/**
	 * This returns the time the alarm will go off
	 */
	public String getAlarmTime(){
		return alarmTime;
	}
	
	/**
	 * This returns the alarm number
	 */
	public int getAlarmNumber(){
		return alarmNumber;
	}
}
