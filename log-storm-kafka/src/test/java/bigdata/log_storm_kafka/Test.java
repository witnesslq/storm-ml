package bigdata.log_storm_kafka;

import com.disruptive.util.cache.CacheClearThread;

public class Test {
	
	public static void main(String[] args){
		new Thread(new CacheClearThread()).start();
	}

}
