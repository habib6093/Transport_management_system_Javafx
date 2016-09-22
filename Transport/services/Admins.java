package services;

public class Admins {
	private int id;
	private String name;
	private String phone;
	private String address;

	public Admins(){
		this.name = ""; 
		this.id = 0; 
		this.phone = ""; 
		this.address = ""; 
	}

	public Admins(int id,String name,String phone, String address ){
		this.name = name; 
		this.id = id; 
		this.phone = phone; 
		this.address = address; 
	}


	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getPhone(){
		return phone;
	}

	public String getAddress(){
		return address;
	}


	public void setName(String name){
		this.name = name; 
	}

	public void setId(int id){
		this.id = id; 
	}

	public void setPhone(String phone){
		this.phone = phone; 
	}

	public void setAddress(String address){
		this.address = address; 
	}
}
