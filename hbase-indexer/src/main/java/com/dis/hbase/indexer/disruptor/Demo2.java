package com.dis.hbase.indexer.disruptor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.WorkerPool;

/**
 * 使用WorkerPool 辅助创建消费者
 * @author Administrator
 *
 */
public class Demo2 {

	public static void main(String[] args) throws Exception{
		
		int BUFFER_SIZE=1024;
		int THREAD_NUMBERS=4;
		
		EventFactory<TradeTransaction> eventFactory=new EventFactory<TradeTransaction>(){

			public TradeTransaction newInstance() {
				 return new TradeTransaction(); 
			}
			
		};
		
		RingBuffer<TradeTransaction> ringBuffer=RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);
		
		SequenceBarrier sequenceBarrier=ringBuffer.newBarrier();
		
		ExecutorService executor=Executors.newFixedThreadPool(THREAD_NUMBERS);
		
		WorkHandler<TradeTransaction> workHandler=new TradeTransactionInDBHandler();
	
		WorkerPool<TradeTransaction> workerPool = new WorkerPool<TradeTransaction>(ringBuffer,sequenceBarrier,new IgnoreExceptionHandler(),workHandler);
		
		workerPool.start(executor);
		
		//下面这个产生8个数据，
		for(int i=0;i<8;i++){
			long seq=ringBuffer.next();
			ringBuffer.get(seq).setPrice(Math.random()*9999);
			ringBuffer.publish(seq);
		}
		Thread.sleep(1000);
		workerPool.halt();
		executor.shutdown();
		
		
		
		
	}

}
