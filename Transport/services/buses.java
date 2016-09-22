package services;

public class buses {
	private String name;
	private String reg;
	private int id;
	private String trips;
	private String earning;


	public buses(){
		this.name = "";
		this.reg = "";
		this.id = 0;
		this.trips = "";
		this.earning = "";
	}

	public buses(int id, String name,String reg,String trips,String earning){
		this.name = name;
		this.reg = reg;
		this.id = id;
		this.trips = trips;
		this.earning = earning;
	}


	public String getName(){
		return name;
	} 


	public String getReg(){
		return reg;
	}

	public int getId(){
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

	public void setId(int id){
		this.id = id;
	} 

	public void setReg(String reg){
		this.reg = reg;
	} 

	public void setTrips(String trips){
		this.trips = trips;
	}  


	public void setEarning(String earning){
		this.earning = earning;
	} 
}