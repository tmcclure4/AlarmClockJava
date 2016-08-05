package alarmClock;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class AlarmClockMain 
{
	
	private static ArrayList<AlarmClock> mainAlarmList = new ArrayList<AlarmClock>();//the main storage for the alarms
	private static ArrayList<AlarmClock> alarmListStorage = new ArrayList<AlarmClock>();//storage for list
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws IOException, URISyntaxException{
	
		//start the program by showing the main GUI interface
		new AlarmGUI(alarmListStorage, false,false,mainAlarmList);
		
		try {
			//main loop to see whether the alarm should be set off
			while(true){
				Date testing = new Date();
				for(Iterator<AlarmClock> alarmIterator = mainAlarmList.iterator(); alarmIterator.hasNext();){

					AlarmClock currentAlarm = alarmIterator.next();
					
					//if same day and alarm is active
					if(sameDay(testing.getDay(), currentAlarm.getdayAlarmGoesOff())&& currentAlarm.isAlarmActive()){
						
						//timeValue stores certain values. See the method for their descriptions
						int timeValue=sameTime(testing.getHours(),testing.getMinutes(),currentAlarm.getMilitaryTime());
						
						if(timeValue==0){//currentTime<AlarmTime, the alarm hasn't passed yet, stop the loop
							//System.out.println("Alarm has NOT passed yet");
							break;
						}
						else if(timeValue==1){//the times are the same, set the alarm off
							//set off alarm
							new AlarmGoingOffGUI(currentAlarm.getAlarmName(), currentAlarm.getAlarmTime(), currentAlarm.getURL());
							if(currentAlarm.getdayAlarmGoesOff().equals("N")){
								currentAlarm.setActiveOrNot(false);
							}
						}
						//else currentTime>alarmTime, alarm has gone off already, keep loop going
						
					}
					
					//System.out.println(Integer.toString(testing.getHours())+Integer.toString(testing.getMinutes()));
					
				}
				
				//wait a total of 60 seconds then see whether a new alarm should go off
				//waits 100ms so the program can add and delete alarms that the user wants at a decent speed
				for(int minuteCounter=0;minuteCounter<600;minuteCounter++){//.1 second loop, total of 1 minute (60 seconds)
				
					for(Iterator<AlarmClock> deleteIterator = mainAlarmList.iterator(); deleteIterator.hasNext();){//delete the alarms
						AlarmClock alarmClockDelete=deleteIterator.next();
						if(alarmClockDelete.getDeleteAlarm()){
							deleteIterator.remove();
						}
					}
					
					if(!alarmListStorage.isEmpty()){//add all the alarms the user wants in the buffer array list
						sortAlarm();
					}
					
					Thread.sleep(100);//.1 seconds or 100ms (n*1000)=n seconds
				}
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method see's whether or not the alarm should go off on the current day. 
	 * @param currentDay-describes the current day, 0-6, Sunday-Saturday
	 * @param alarmDate-has the days the alarm is set to go off
	 * @return-true if same day, false if not the same day
	 */
	private static boolean sameDay(int currentDay, String alarmDate){
		boolean daysAreTheSame=false;
		
		switch(currentDay){
			case 0: if(alarmDate.indexOf("Su")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}
				break;
			case 1: if(alarmDate.indexOf("M")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}	
				break;
			case 2: if(alarmDate.indexOf("Tu")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}
				break;
			case 3: if(alarmDate.indexOf("W")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}	
				break;
			case 4: if(alarmDate.indexOf("Th")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}
				break;
			case 5: if(alarmDate.indexOf("F")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}
				break;
			case 6: if(alarmDate.indexOf("Sa")!= -1 || alarmDate.equals("N") || alarmDate.equals("E")){
				daysAreTheSame=true;
				}
				break;
			
		}
		
		return daysAreTheSame;
	}
	
/**
 * This function sees whether the alarm has passed, the alarm should go off, or if the alarm time has not been reached.
 * @param timeHours-is the current hour
 * @param timeMinutes-is the current minute
 * @param alarmTime-the time the alarm is to go off
 * @return 0-the alarm time has not been reached, stop main loop
 * 		   1-the alarm time is happening right now, set off alarm
 * 		   2-the alarm has gone off already, keep main loop going
 */
	public static int sameTime(int timeHours, int timeMinutes, int alarmTime){
		
		int currentTime=(timeHours*100)+timeMinutes;//get into military time
		int returnValue;
		
		if(currentTime<alarmTime){//the alarm time hasn't passed yet, stop
			returnValue=0;
		}
		else if(currentTime==alarmTime){//times are the same, set alarm off
			returnValue=1;
		}
		else{//currentTime>alarmTime, alarm has gone off already, keep going
			returnValue=2;
		}
		return returnValue;
	}
	
	
	/**
	 * This is the storing algorithm that stores the clocks in order of earliest time to latest time.
	 * If an alarm has the same time, the alarm with the least days will be shown first
	 */
	public static void sortAlarm(){
		
		for(Iterator<AlarmClock> it=alarmListStorage.iterator();it.hasNext();){
			AlarmClock tempAlarmListStorage = it.next();
		
			//if the size is 0, then add the alarm clock
			if(mainAlarmList.size()==0){
				mainAlarmList.add(tempAlarmListStorage);
			}
			else{//there is at least one item in the alarm list
				int alarmListCounter=0;
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
					 }
					 else if(mainAlarmList.size()==alarmListCounter+1){//if no more alarms in array list
						 mainAlarmList.add(tempAlarmListStorage);
						 break;
					 }
					 alarmListCounter++;//increment to know the index of the current alarm in the array list
				}
			}
			
		}
		
		//remove all of the alarms in the alarm list storage after adding them all to the main array list
		for(Iterator<AlarmClock> it=alarmListStorage.iterator();it.hasNext();){
			AlarmClock tempThing = it.next();
			it.remove();
		}
	}
}

