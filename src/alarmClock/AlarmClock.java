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
		//sortAlarm();
	}
	
	/**
	 * This is the storing algorithm that stores the clocks in order of earliest to latest.
	 * If an alarm has the same time, the alarm with the least days will be shown first
	 */
	/*public static void sortAlarm(){
		
		//for(AlarmClock tempAlarmListStorage:alarmListStorage){
		for(Iterator<AlarmClock> it=alarmListStorage.iterator();it.hasNext();){
			AlarmClock tempAlarmListStorage = it.next();
			//if the size is 0, then add the alarm clock
			if(mainAlarmList.size()==0){
				mainAlarmList.add(tempAlarmListStorage);
			}
			else{//there is at least one item in the alarm list
				int alarmListCounter=0;
				//for(AlarmClock tempAlarmClock:mainAlarmList){
				for(Iterator<AlarmClock> it2=mainAlarmList.iterator();it2.hasNext();){	
					AlarmClock tempAlarmClock = it2.next();
					//if the current military time is LESS THAN the time in the arraylist, then put this time in 
					//front of the military time in arraylist
					 if(tempAlarmClock.getMilitaryTime()>tempAlarmListStorage.getMilitaryTime()){
						mainAlarmList.add(alarmListCounter, tempAlarmListStorage);
						break;
					 }
					 else if(tempAlarmClock.getMilitaryTime()==tempAlarmListStorage.getMilitaryTime()){//the times are the same
						 String[] tempArrayDay = tempAlarmClock.getdayAlarmGoesOff().split(" ");
						 String[] tempArrayCurrent = tempAlarmListStorage.getdayAlarmGoesOff().split(" ");
						 
						 //whichever day has the most days active is put after the other one, if the same, put it after as well
						 if(tempArrayDay.length>tempArrayCurrent.length){//if the array is active for more days than current alarm 
							 mainAlarmList.add(alarmListCounter,tempAlarmListStorage);
							 break;
						 }
						 else if(mainAlarmList.size()==alarmListCounter+1){//if no more alarms in array list 
							 mainAlarmList.add(tempAlarmListStorage);
							 break;
						 }
						 //else{//the current array has more days active then the array, add it after
						//	 alarmList.add(alarmListCounter+1,this);
						 //}
						 //break;
					 }
					 else if(mainAlarmList.size()==alarmListCounter+1){//if no more alarms in array list
						 mainAlarmList.add(tempAlarmListStorage);
						 break;
					 }
					 alarmListCounter++;//increment to know the index of the current alarm in the array list
				}
			}
			for(AlarmClock tempPrint: mainAlarmList){//testing purposes
				System.out.println(Integer.toString(tempPrint.getMilitaryTime()) + " " +tempPrint.getAlarmName());
			}			
			
			//for(Iterator<AlarmClock> alarmsAdded = alarmListStorage.iterator();alarmsAdded.hasNext();){
				//AlarmClock alarmsToBeDeleted=alarmsAdded.next();
			//	alarmsAdded.remove();
			//}
			//System.out.println("\n");
		}
		
		for(Iterator<AlarmClock> it=alarmListStorage.iterator();it.hasNext();){
			AlarmClock tempThing = it.next();
			it.remove();
		}
		//int temp =alarmListStorage.size();
		//for (int i = 0; i < temp; i++)
	    //    alarmListStorage.remove(i);
	}*/
	/**
	 * This is the storing algorithm that stores the clocks in order of earliest to latest.
	 * If an alarm has the same time, the alarm with the least days will be shown first
	 */
	/*public void sortAlarm(){
		
		//if the size is 0, then add the alarm clock
		if(alarmList.size()==0){
			alarmList.add(this);
		}
		else{//there is at least one item in the alarm list
			int alarmListCounter=0;
			for(AlarmClock tempAlarmClock:alarmList){
				//if the current military time is LESS THAN the time in the arraylist, then put this time in 
				//front of the military time in arraylist
				 if(tempAlarmClock.getMilitaryTime()>militaryTime){
					alarmList.add(alarmListCounter, this);
					break;
				 }
				 else if(tempAlarmClock.getMilitaryTime()==militaryTime){//the times are the same
					 String[] tempArrayDay = tempAlarmClock.getdayAlarmGoesOff().split(" ");
					 String[] tempArrayCurrent = dayAlarmGoesOff.split(" ");
					 
					 //whichever day has the most days active is put after the other one, if the same, put it after as well
					 if(tempArrayDay.length>tempArrayCurrent.length){//if the array is active for more days than current alarm 
						 alarmList.add(alarmListCounter,this);
						 break;
					 }
					 else if(alarmList.size()==alarmListCounter+1){//if no more alarms in array list 
						 alarmList.add(this);
						 break;
					 }
					 //else{//the current array has more days active then the array, add it after
					//	 alarmList.add(alarmListCounter+1,this);
					 //}
					 //break;
				 }
				 else if(alarmList.size()==alarmListCounter+1){//if no more alarms in array list
					 alarmList.add(this);
					 break;
				 }
				 alarmListCounter++;//increment to know the index of the current alarm in the array list
			}
		}
		for(AlarmClock tempPrint: alarmList){//testing purposes
			System.out.println(Integer.toString(tempPrint.getMilitaryTime()) + " " +tempPrint.getAlarmName());
		}
		System.out.println("\n");
		
	}*/
	
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
	
	public void setToDeleteAlarm(){
		deleteAlarm=true;
	}
	
	public boolean getDeleteAlarm(){
		return deleteAlarm;
	}
	
	public String getURL(){
		return urlAlarm;
	}

}

