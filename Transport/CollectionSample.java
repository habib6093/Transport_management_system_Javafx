package collectionsample;

import java.util.ArrayList;
public class CollectionSample {
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList ar=new ArrayList();
        ar.add("bob");
        ar.add("alex");
        ar.add("marley");
        System.out.println(ar.size());
        System.out.println(ar);
        System.out.println(ar.indexOf("alex"));
        ar.remove("alex");
        String s;
        int i;
        for(i=0;i<ar.size();i++){
            s=(String)ar.get(i);
            System.out.println(s.toUpperCase());
        }
        System.out.println(i);
    }
}
