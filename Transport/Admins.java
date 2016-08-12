public class Admins {
	private String name;
	private String phone;
	private String address;

	public Admins(){
		this.name = ""; 
		this.phone = ""; 
		this.address = ""; 
	}

	public Admins(String name,String phone, String address ){
		this.name = name; 
		this.phone = phone; 
		this.address = address; 
	}


	public String getName(){
		return name;
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

	public void setPhone(String phone){
		this.phone = phone; 
	}

	public void setAddress(String address){
		this.address = address; 
	}
}
