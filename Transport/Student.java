//package collectionsample;
public class Student{
	private int id;
	private String name;
	private double cgpa;
    private static int count;
	public Student(){
        System.out.println("no arg called");
	}
	public Student(int id,String n,double c){
        this();
		this.id=id;
		this.name=n;
		this.cgpa=c;
		System.out.println("Student is created");
	}
    public static void sayHello(){
		System.out.println("Hello java");
	}
	public void setInfo(int i,String n,double c){
		id=i;
		name=n;
		cgpa=c;
	}
	public void setID(int i){	id=i;}
	public void setName(String n){	name=n;}
	public void setCGPA(double c){	cgpa=c;}
	
	public String getName(){return name;}
	public int getID(){return id;}
	public double getCGPA(){return cgpa;}
	
	public void print(){
            System.out.println("\nDetails of "+name);
            System.out.println("ID : "+id+", Name :"+name+" : cgpa : "+cgpa);
	}
	public String getDetails(){
		return "Name : "+name+" ID : "+id+", cgpa :"+cgpa;
	}
	public String toString(){
		return name+ ", with id : "+id;
	}
}