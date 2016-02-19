package com.dis.hbase.indexer.disruptor;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionVasConsumer  implements EventHandler<TradeTransaction>  {

	public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		//do something
		
		
	}

	

}
