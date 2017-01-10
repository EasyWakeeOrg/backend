package com.easywakee.entities;
//A class with attributes hour and minutes to handle the time
public class Time {
	private int hour;
	private int minute;
	
	public Time(){}
	
	public Time(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	//Substract a number of min to this time and rthis time to the new obtained Time
	public void substract(int min){
		int nbHours = min % 60;//nb of hours in min
		int nbMin = min - 60*nbHours;//nb of minutes in min
		
		this.hour = this.hour - nbHours % 24;
		this.minute = 59 - nbMin % 60;
		if(nbMin > this.minute){//si besoin on passe à l'heure precedente
			this.hour = this.hour - 1 % 24;
		}
	}
}
