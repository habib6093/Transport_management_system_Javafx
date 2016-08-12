//package collectionsample;

import java.util.ArrayList;
public class CollectionStudent {
    public static void main(String[] args) {
        // TODO code application logic 
        Student s1=new Student(1,"rahim",4.0);
        Student s2=new Student(2,"musa",3.0);
        ArrayList<Student> ar=new ArrayList<Student>();
        ar.add(s1);
        ar.add(s2);
        System.out.println(ar.size());
        System.out.println(ar);
        System.out.println(ar.indexOf(s2));
        //ar.remove(s1);
        //ar.remove(0);
		//String s4="test";
        //System.out.println(s4);
        Student s;
        for(int i=0;i<ar.size();i++){
            s=ar.get(i);
            System.out.println(s);
        }
    }
    
}
