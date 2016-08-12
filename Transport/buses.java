public class buses {
	private String name;
	private String id;
	private String trips;
	private String earning;


	public buses(){
		this.name = "";
		this.id = "";
		this.trips = "";
		this.earning = "";
	}

	public buses(String name, String id,String trips,String earning){
		this.name = name;
		this.id = id;
		this.trips = trips;
		this.earning = earning;
	}


	public String getName(){
		return name;
	} 

	public String getId(){
		return id;
	} 
	
	public String getTrips(){
		return trips;
	} 

	public String getEarning(){
		return earning;
	} 

	public void setName(String name){
		this.name = name;
	}

	public void setId(String id){
		this.id = id;
	} 

	public void setTrips(String trips){
		this.trips = trips;
	}  


	public void setEarning(String earning){
		this.earning = earning;
	} 
}