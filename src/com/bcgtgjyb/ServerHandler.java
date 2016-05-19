package com.bcgtgjyb;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage;

/**
 * 
 *    HelloHandler 负责业务逻辑处理
 *
 *     功   能： TODO 
 *     类   名： HelloHandler.java
 *
 *  ver     変更日       角色    担当者     変更内容
 *     ──────────────────────────────────────────────
 *  V1.00   2013-2-21   模块    苏若年     初版
 *
 *     Copyright (c) 2013 dennisit corporation All Rights Reserved.
 *   
 *  Email:<a href="mailto:DennisIT@163.com">发送邮件</a>
 *
 */
public class ServerHandler extends IoHandlerAdapter{
    
    /**
     * 有新连接时触发
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("服务端, session open for " + session.getRemoteAddress());
    }
    
    /**
     * 连接关闭时触发
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("服务端, session closed from " + session.getRemoteAddress());
    }
    
    /**
     * 收到来自客户端的消息
     */
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String clientIP = session.getRemoteAddress().toString();
        System.out.println("服务端接收到来自IP:"+clientIP+"的消息:" +message);
        if (message instanceof Notice.rq_util_heartbeat){
        	System.out.println("rq_util_heartbeat");
        	PacketSender.sendHeartbeat(session);
        }
        if (message instanceof Notice.rq_send_message){
        	Notice.rq_send_message rq = (Notice.rq_send_message)message;
        	System.out.println("rq_send_message");
        	PacketSender.sendMessage(session, "server:"+rq.getRqText());
        }
    }
    
    /**
     * 当有异常发生时触发
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("服务端,发生异常" + cause.getMessage());
        session.close();
    }

    
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("服务端发送" + "hello clinet");
		
	}
    
    
}