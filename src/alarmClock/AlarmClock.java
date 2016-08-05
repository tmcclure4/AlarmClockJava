package alarmClock;

import java.util.*;

public class AlarmClock {
	
	//private variables
	private String alarmName;
	private String dayAlarmGoesOff;
	private String alarmTime;
	private boolean alarmActive = true;
	private String fullAlarmInfo;
	private int militaryTime;
	private String urlAlarm;
	private boolean deleteAlarm=false;
	
	//stores the list of alarms
	private static ArrayList<AlarmClock> alarmListStorage;// = new ArrayList<AlarmClock>();
	private static ArrayList<AlarmClock> mainAlarmList;
	
	//constructor 
	public AlarmClock(String tempAlarmName, String alarmDate, String tempTime, String tempURL, 
			ArrayList<AlarmClock> tempArrayList, ArrayList<AlarmClock> tempMainList){
		alarmName=tempAlarmName;
		dayAlarmGoesOff=alarmDate;
		alarmTime=tempTime;
		fullAlarmInfo=tempAlarmName+" " +tempTime+ " "+alarmDate;
		militaryTime = convertToMilitaryTime(tempTime);
		alarmListStorage=tempArrayList;
		mainAlarmList=tempMainList;
		urlAlarm=tempURL;
		alarmListStorage.add(this);
	}
	
	
	//It is often said that 12 a.m. Monday is midnight on Monday morning and 12 p.m. is midday. 
	/**
	 * This converts the given time (hh:mm AM/PM) into Military time(hhmm).
	 * h=hour, m=minute
	 */
	public int convertToMilitaryTime(String tempString){
		String[] tempArray = tempString.split(" ");
		int tempMilitaryTime = Integer.parseInt(tempArray[0].replace(":", ""));//remove the : in the time
		//deal with the 12:mm AM and 12:mm PM issue. 12:mm AM means its 00mm (near midnight) and 12 PM is 12mm(near midday).
		if(tempMilitaryTime%1200<=59){//when % with 1200, if it's 12mm, then the result will be <59(which is the max minutes)
			if (tempArray[1].equals("AM")){//its the early morning (00mm) military time 
				tempMilitaryTime%=1200;//get the time in minutes
			}
		}
		else if(tempArray[1].equals("PM")){//if the time is in "PM" add 12 hours to it, else do nothing
			tempMilitaryTime+=1200;
		}
		return tempMilitaryTime;
	}
	
	
	/**
	 * @return - the alarm time and the days the alarm goes off.
	 */
	public String getTimeDate(){
		return alarmTime + " " + dayAlarmGoesOff;
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
	 * This returns whether the alarm is active or not
	 */
	public boolean isAlarmActive(){
		return alarmActive;
	}
	
	
	/**
	 * This returns the full alarm name. Alarm name + Alarm time + when it'll go off
	 */
	public String getFullAlarmInfo(){
		return fullAlarmInfo;
	}
	
	
	/**
	 * This returns the military time of the alarm
	 */
	public int getMilitaryTime(){
		return militaryTime;
	}
	
	
	/**
	 * This sets the variable whether the alarm is active or not
	 */
	public void setActiveOrNot(Boolean tempActive){
		alarmActive=tempActive;
	}
	
	
	/**
	 * Sets the boolean value to delete the alarm
	 */
	public void setToDeleteAlarm(){
		deleteAlarm=true;
	}
	
	
	/**
	 * @return - boolean value whether the alarm should be deleted
	 */
	public boolean getDeleteAlarm(){
		return deleteAlarm;
	}
	
	
	/**
	 * 
	 * @return - the URL that the user wants to play when the alarm goes off
	 */
	public String getURL(){
		return urlAlarm;
	}

}

