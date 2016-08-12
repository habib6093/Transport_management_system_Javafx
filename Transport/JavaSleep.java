public class JavaSleep{
    public static void main(String s[]){
        Another a=new Another();
        a.start();
        /*Test tr=new Test();
        Thread t=new Thread(tr);
        t.start();*/
        System.out.println("main ends");
    }
}
class Another extends Thread{
    public void run(){
      int i=0;
      while(true){
         try{
            System.out.println("inside thread "+i++);
				//this.sleep(500);
            if(i>20)break;
        }
        catch(Exception ex){}
    }
}
}
class Test implements Runnable{
    public void run(){
        int j=0;
        while(true){
            System.out.println("hello from Test : "+j++);
            try{
                Thread.sleep(100);
            }
            catch(Exception ex){}
        }
    }
}

