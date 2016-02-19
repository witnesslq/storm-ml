package com.dis.hbase.indexer.disruptor;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction>,WorkHandler<TradeTransaction> {

	public void onEvent(TradeTransaction event) throws Exception {
		event.setId(UUID.randomUUID().toString());
		System.out.println(event.getId());
		
	}

	public void onEvent(TradeTransaction event, long sequence,  
            boolean endOfBatch) throws Exception {
		this.onEvent(event);
		
	}

	

}
