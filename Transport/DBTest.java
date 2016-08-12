//package dbtest;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DBTest{
    public static void main(String[] args)throws SQLException{
        DataAccess da=new DataAccess();
        ResultSet rs=null;
        ResultSet seats=null;

        String q=new String("SELECT * FROM daily_schedule join schedule on schedule.schedule_id = daily_schedule.schedule_id join routes on schedule.route_id = routes.route_id join buses on schedule.bus_id = buses.bus_id where daily_schedule.active = 1 and daily_schedule.date = ");
        
        String nm="SELECT daily_schedule_id, COUNT( * ) AS tickets FROM  `daily_ticket` WHERE active =1 GROUP BY  `daily_schedule_id`";


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(q+dateFormat.format(date));
        String star = "'";
        rs=da.getData(q+star+dateFormat.format(date)+star);
        seats = da.getData(nm); 
        

        while(rs.next()){
            int id = rs.getInt("daily_schedule_id");
            String destination = rs.getString("destination");
            String departs = rs.getString("departs");
            int fare = rs.getInt("fare");
            String time = rs.getString("time");
            int seat = 0;

            while(seats.next()){
                if(seats.getInt("daily_schedule_id") == id){
                    seat = 40 - seats.getInt("daily_schedule_id");
                    break;
                } else if(seat == 0){
                    seat = 40;
                }
            }

            /*if(n.equals(nm)){
                System.out.println("found");
            }*/
            //String l = rs.getString("location");
            System.out.println(id+" "+destination+" "+departs+" "+fare+" "+time+" "+seat);
        }
        da.close();    



        // TODO code application logic here
        /*DataAccess da=new DataAccess();
        ResultSet rs=null;
        String q="select * from routes";
        rs=da.getData(q);*/
        //String nm="abc";

        /*while(rs.next()){
            int id = rs.getInt("route_id");
            String n = rs.getString("destination");
            if(n.equals(nm)){
                System.out.println("found");
            }
            //String l = rs.getString("location");
            System.out.println(id+" : "+n);
            //System.out.println("ID of "+n+" is : " + id);
        }
        da.close();*/

        

        
        /*String q="insert into mytable values(5,'another','raj')";
        int c=da.updateDB(q);
        da.close();*/
		/*int id=2;
        String q="delete from mytable where id="+id;
        System.out.println(q);
        int c=da.updateDB(q);*/
        /*String q="update mytable set name='bob2' where id=5";
        int c=da.updateDB(q);
        da.close();*/
        //System.out.println(rs.toString());
    }
}