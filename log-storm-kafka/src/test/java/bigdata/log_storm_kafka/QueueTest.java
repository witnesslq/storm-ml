package bigdata.log_storm_kafka;

import com.disruptive.model.InterfaceTimeOut;
import com.disruptive.util.queue.InterfaceTimeOutQueue;

public class QueueTest {
	
	public static void main(String[] args) throws InterruptedException{
		InterfaceTimeOutQueue.init();
		
		for(int i=0;i<1000000;i++){
			InterfaceTimeOut xx=new InterfaceTimeOut();
			xx.setId(i);
			System.out.println("create:"+i);
			InterfaceTimeOutQueue.offer(xx);
		}
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		Thread.sleep(10000);
		for(int i=1000000;i<2000000;i++){
			InterfaceTimeOut xx=new InterfaceTimeOut();
			xx.setId(i);
			System.out.println("create22:"+i);
			InterfaceTimeOutQueue.offer(xx);
		}
		
	}

}
