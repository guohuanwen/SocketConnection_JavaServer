package com.bcgtgjyb;

import java.util.Collection;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.google.protobuf.GeneratedMessage;

/**
 * 
 * HelloHandler ����ҵ���߼�����
 * 
 * �� �ܣ� TODO �� ���� HelloHandler.java
 * 
 * ver ����� ��ɫ ������ ������� �������������������������������������������������������������������������������������������� V1.00
 * 2013-2-21 ģ�� ������ ����
 * 
 * Copyright (c) 2013 dennisit corporation All Rights Reserved.
 * 
 * Email:<a href="mailto:DennisIT@163.com">�����ʼ�</a>
 * 
 */
public class ServerHandler extends IoHandlerAdapter {

	/**
	 * ��������ʱ����
	 */
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("�����, session open for "
				+ session.getRemoteAddress());
	}

	/**
	 * ���ӹر�ʱ����
	 */
	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("�����, session closed from "
				+ session.getRemoteAddress());
	}

	/**
	 * �յ����Կͻ��˵���Ϣ
	 */
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		String clientIP = session.getRemoteAddress().toString();
		System.out.println("����˽��յ�����IP:" + clientIP + "����Ϣ:" + message);
		if (message instanceof Notice.rq_util_heartbeat) {
			System.out.println("rq_util_heartbeat");
			PacketSender.sendHeartbeat(session);
		}
		if (message instanceof Notice.chat_message) {
			// �õ����еĿͻ���Session
			Collection<IoSession> sessions = session.getService()
					.getManagedSessions().values();
			for (IoSession sess : sessions) {
				// sess.write(datetime + "\t" + content);
				if (sess.equals(session)) {
					System.out.println("ss yes");
				} else {
					System.out.println("ss no");
					Notice.chat_message chat = (Notice.chat_message) message;
					sess.write(chat);
				}
			}
		}
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