package services;

public class routes {
	private int id;
	private String to;
	private String from;
	private String trips;
	private String earnings;

	public routes(){
		this.id = 0;
		this.to = "";
		this.from = "";
		this.trips = "";
		this.earnings = "";
	}

	public routes(int id,String to, String from, String trips, String earnings){
		this.id = id;
		this.to = to;
		this.from = from;
		this.trips = trips;
		this.earnings = earnings;
	}


	public String getTo(){
		return to;
	}

	public int getId(){
		return id;
	}

	public String getFrom(){
		return from;
	}

	public String getTrips(){
		return trips;
	}

	public String getEarnings(){
		return earnings;
	}


	public void setId(int id){
		this.id = id;
	}

	public void setTo(String to){
		this.to = to;
	}


	public void setFrom(String from){
		this.from = from;
	}

	public void setTrips(String trips){
		this.trips = trips;
	}

	public void setEarnings(String earnings){
		this.earnings = earnings;
	}
}