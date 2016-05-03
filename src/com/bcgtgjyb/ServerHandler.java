package com.bcgtgjyb;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * 
 *    HelloHandler ����ҵ���߼�����
 *
 *     ��   �ܣ� TODO 
 *     ��   ���� HelloHandler.java
 *
 *  ver     �����       ��ɫ    ������     �������
 *     ��������������������������������������������������������������������������������������������
 *  V1.00   2013-2-21   ģ��    ������     ����
 *
 *     Copyright (c) 2013 dennisit corporation All Rights Reserved.
 *   
 *  Email:<a href="mailto:DennisIT@163.com">�����ʼ�</a>
 *
 */
public class ServerHandler extends IoHandlerAdapter{
    
    /**
     * ��������ʱ����
     */
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        System.out.println("�����, session open for " + session.getRemoteAddress());
    }
    
    /**
     * ���ӹر�ʱ����
     */
    @Override
    public void sessionClosed(IoSession session) throws Exception {
        System.out.println("�����, session closed from " + session.getRemoteAddress());
    }
    
    /**
     * �յ����Կͻ��˵���Ϣ
     */
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        String clientIP = session.getRemoteAddress().toString();
        System.out.println("����˽��յ�����IP:"+clientIP+"����Ϣ:"+message );
        Notice.rq_game_changeDirection m = (Notice.rq_game_changeDirection)message;
        if (m.getDirection() == 0){
        	Notice.rq_game_changeDirection r = Notice.rq_game_changeDirection.newBuilder().setDirection(0).setUid("heart beat").build();
    		session.write(r);
        }
        Notice.rq_game_changeDirection r = Notice.rq_game_changeDirection.newBuilder().setDirection(1).setUid("hello client").build();
		session.write(r);
    }
    
    /**
     * �����쳣����ʱ����
     */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        System.out.println("�����,�����쳣" + cause.getMessage());
        session.close();
    }

    
	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("����˷���" + "hello clinet");
		
	}
    
    
}