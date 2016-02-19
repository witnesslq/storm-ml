package com.dis.hbase.indexer.disruptor;

import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {

	public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {
		// TODO Auto-generated method stub
		
		//do send jms message
		
	}

	
}
