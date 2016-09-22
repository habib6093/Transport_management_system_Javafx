package services;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountRoute {
	
	public static int getTotalRoute(int id){
		DataAccess da = new DataAccess(); 
		ResultSet rs ;
		int x = 0;

		String q = "SELECT count(routes.route_id) as id FROM `daily_schedule` join schedule on daily_schedule.schedule_id = schedule.schedule_id join routes on schedule.route_id = routes.route_id where schedule.active = 1 and routes.route_id ="+id+" group by routes.route_id";

		try {
			rs = da.getData(q);
			rs.next();
			x = rs.getInt("id");
			da.close();
		}catch(Exception ex){

		}

		return x;
	}

	public static int getTotalEarning(int id){
		DataAccess da = new DataAccess();
		ResultSet rs, rm;

		String q = "SELECT fare , daily_schedule_id as id FROM `daily_schedule` join schedule on daily_schedule.schedule_id = schedule.schedule_id join routes on schedule.route_id = routes.route_id and routes.route_id ="+id+" where schedule.active = 1";
		int total = 0;
		try {
			rs = da.getData(q);

			while (rs.next()) {
				int x = rs.getInt("id");
				int fare = rs.getInt("fare");

				String w = "select count(*) as num from daily_ticket where daily_schedule_id = ";
				rm = da.getData(w+x);
				rm.next();
				int number = rm.getInt("num");

				total += number * fare;				
			}
		} catch(Exception ex){}

		return total;

	}


}

