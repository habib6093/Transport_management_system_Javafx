public class schedules {
	private String to;
	private String from;
	private String fare;
	private String name;
	private String time;

	public schedules(){
		this.to = "";
		this.from = "";
		this.fare = "";
		this.name = "";
		this.time = "";
	}

	public schedules(String to,String from, String name, String fare, String time){
		this.to = to;
		this.from = from;
		this.fare = fare;
		this.name = name;
		this.time = time;
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