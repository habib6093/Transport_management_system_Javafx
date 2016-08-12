public class routes {
	private String to;
	private String from;
	private String trips;
	private String earnings;

	public routes(){
		this.to = "";
		this.from = "";
		this.trips = "";
		this.earnings = "";
	}

	public routes(String to, String from, String trips, String earnings){
		this.to = to;
		this.from = from;
		this.trips = trips;
		this.earnings = earnings;
	}


	public String getTo(){
		return to;
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