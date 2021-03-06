package com.dis.hbase.indexer.disruptor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.lmax.disruptor.BatchEventProcessor;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.YieldingWaitStrategy;

/**
 * 生产者消费者
 * @author Administrator
 *
 */
public class Demo1 {

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		int BUFFER_SIZE=1024;
		int THREAD_NUMBERS=4;
		
		
		final RingBuffer<TradeTransaction> ringBuffer=RingBuffer.createSingleProducer(new EventFactory<TradeTransaction>(){

			public TradeTransaction newInstance() {
				return new TradeTransaction();
			}
			
		},BUFFER_SIZE,new YieldingWaitStrategy());
		
		//创建线程池
		ExecutorService executors=Executors.newFixedThreadPool(THREAD_NUMBERS);
		
		//创建SequenceBarrier  
        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier(); 
        
        //创建消息处理器
        BatchEventProcessor<TradeTransaction> transProcessor=new BatchEventProcessor<TradeTransaction>(ringBuffer, sequenceBarrier, new TradeTransactionInDBHandler());

        //这一步的目的是让RingBuffer根据消费者的状态 如果只有一个消费者的情况可以省略
        ringBuffer.addGatingSequences(transProcessor.getSequence());
        
        //把消息处理器提交到线程池
        executors.submit(transProcessor);
        //如果存太多咯消费者 那么重复执行上面3行代码 把TradeTransactionInDBHandler换成其它消费者类
        
        Future<?> future = executors.submit(new Callable<Void>(){

			public Void call() throws Exception {
				 long seq;  
	                for(int i=0;i<1000;i++){  
	                    seq=ringBuffer.next();//占个坑 --ringBuffer一个可用区块  
	                      
	                    ringBuffer.get(seq).setPrice(Math.random()*9999);//给这个区块放入 数据  如果此处不理解，想想RingBuffer的结构图  
	                      
	                    ringBuffer.publish(seq);//发布这个区块的数据使handler(consumer)可见  
	                }  
	                return null;
			}
        	
        });
        
        future.get();//等待生产者结束
        Thread.sleep(1000);//等1秒，等消费都处理完成
        transProcessor.halt();//通知事件(或者说消息)处理器 可以结束了
        executors.shutdown();//终止线程
        
		
		
	}

}
