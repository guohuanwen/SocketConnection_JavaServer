package com.bcgtgjyb;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BwServer b = new BwServer();
		b.start();
	}
	
	

	private byte[] read(Socket mSocket) throws IOException {
		InputStream in = mSocket.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] dataBuffer = new byte[8 * 1024];
		while ((nRead = in.read(dataBuffer, 0, dataBuffer.length)) != -1) {
			buffer.write(dataBuffer, 0, dataBuffer.length);
		}
		buffer.flush();
		return buffer.toByteArray();
	}

	private void packet(byte[] bytes) {
		try {
			Notice.rq_game_changeDirection rq_game_changeDirection = Notice.rq_game_changeDirection
					.parseFrom(bytes);
			String uid = rq_game_changeDirection.getUid();
			System.out.println("packet" + uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
