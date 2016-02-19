package com.dis.hbase.indexer.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;

public class Server {
	
	public static void main(String[] args) throws Exception{
		//这是主要的服务启动器
		ServerBootstrap serverBootstrap=new ServerBootstrap();
		// 设置线程池
		//BOSS线程
		EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
		//WORK线程池：这样的申明方式,主要是为了说明Netty的线程组怎么工作的
		ThreadFactory threadFactory=new DefaultThreadFactory("work thread pool");
		//CPU个数
		int processorsNmber = Runtime.getRuntime().availableProcessors();
		EventLoopGroup workLoopGroup = new NioEventLoopGroup(processorsNmber);
		//指定Netty的Boss线程和work线程
		serverBootstrap.group(bossLoopGroup,workLoopGroup);
		//
		
		//设置服务的通道类型
		serverBootstrap.channel(NioServerSocketChannel.class);
		
		//设置处理器
		//Netty的特色就在这一串 "通道水管 "中的 “处理器”
		serverBootstrap.childHandler(new ChannelInitializer<NioSocketChannel>(){

			@Override
			protected void initChannel(NioSocketChannel ch) throws Exception {
				ch.pipeline().addLast(new ByteArrayEncoder());
				ch.pipeline().addLast(new TCPServerHandler());
				ch.pipeline().addLast(new ByteArrayDecoder());
				
			}
		});
		
		//设置netty服务器绑定的IP和端口 
		serverBootstrap.option(ChannelOption.SO_BACKLOG,128 );
		serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
		serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 83));
		//还可以监听多个端口 
		//serverBootstrap.bind(new InetSocketAddress("0.0.0.0", 84));
		
		
	}
	
}
