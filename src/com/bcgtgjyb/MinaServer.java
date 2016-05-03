package com.bcgtgjyb;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoServiceStatistics;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinaServer m = new MinaServer();
		m.init();
	}

	private void init() {
		try {
			IoAcceptor acceptor = new NioSocketAcceptor();
			ServerHandler handler = new ServerHandler();
			acceptor.setHandler(handler);
			// 指定protobuf的编码器和解码器  
	        acceptor.getFilterChain().addLast("codec",  
	                new ProtocolCodecFilter(new MinaProtobufEncoder(), new MinaProtobufDecoder()));  
			acceptor.bind(new InetSocketAddress(12345));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
