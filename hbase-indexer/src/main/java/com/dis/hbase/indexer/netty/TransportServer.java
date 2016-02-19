package com.dis.hbase.indexer.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

import java.io.Closeable;
import java.io.IOException;

public class TransportServer implements Closeable {

	private ServerBootstrap bootstrap;
	private ChannelFuture channelFuture;
	
	public TransportServer(TransportContext context,int portToBind){
		
	}
	
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	

}
