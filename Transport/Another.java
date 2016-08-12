import javafx.scene.Scene;

public class Another extends Thread{
	private Scene scene;
	TicketController t;
	
	public Another(Scene scene){
		this.scene = scene;
		System.out.println(scene);

	}

	public void run(){
		int x=1;

		while(true){			
			//try{}catch(Exception e){};
			try{
				System.out.println(x);
				System.out.println(scene);
				t.checkTicket(scene);
				Thread.sleep(5000);
			} catch(Exception ex){}

		} 
	}

}