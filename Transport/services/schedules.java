
/*
custom schedule type data for the schedule table
setter, getter, default and custom constructor for the class
*/  
package services;

public class schedules {
	private int route_id;
	private int bus_id;
	private int schedule_id;

	private String to;
	private String from;
	private String fare;
	private String name;
	private String time;

	public schedules(){
		this.route_id = 0;
		this.bus_id = 0;
		this.schedule_id = 0;

		this.to = "";
		this.from = "";
		this.fare = "";
		this.name = "";
		this.time = "";
	}

	public schedules(int route_id,int bus_id,int schedule_id,String to,String from, String name, String fare, String time){
		this.bus_id = bus_id;
		this.route_id = route_id;
		this.schedule_id = schedule_id;

		this.to = to;
		this.from = from;
		this.fare = fare;
		this.name = name;
		this.time = time;
	}

	public int getRouteId(){
		return route_id;
	}

	public int getBusId(){
		return bus_id;
	}

	public int getScheduleId(){
		return schedule_id;
	}

	public String getTo(){
		return to;
	}

	public String getFrom(){
		return from;
	}

	public String getName(){
		return name;
	}

	public String getFare(){
		return fare;
	}

	public String getTime(){
		return time;
	}




	public void setRouteId(int route_id){
		this.route_id = route_id;
	}

	public void setScheduleId(int schedule_id){
		this.schedule_id = schedule_id;
	}

	public void setBusId(int bus_id){
		this.bus_id = bus_id;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setTo(String to){
		this.to = to;
	}

	public void setFrom(String from){
		this.from = from;
	}

	public void setFare(String fare){
		this.fare = fare;
	}

	public void setTime(String time){
		this.time = time;
	}
}