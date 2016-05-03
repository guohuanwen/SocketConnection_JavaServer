package com.bcgtgjyb;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BwServer extends Thread {

	private ServerSocket mServerSocket;
	private Thread mWriteThread;

	/**
	 * @param args
	 */

	public BwServer() {
		// TODO Auto-generated method stub
		mWriteThread = new Thread();
		try {
			mServerSocket = new ServerSocket(12345);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {
		while (true) {
			try {
				Socket s = mServerSocket.accept();
				System.out.println("start1");
				byte[] b = read(s);
				System.out.println("start2");
				packet(b);
				System.out.println("start3");
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private class MyRunnable implements Runnable {

		public void run() {

		}

	}

	private byte[] read(Socket mSocket) throws IOException {
		System.out.println("read");
		DataInputStream in = new DataInputStream(mSocket.getInputStream());
		//类型
		int type = in.readInt(); 
		//body长度
        int bodyLength = in.readInt();  // read header  
        byte[] bodyBytes = new byte[bodyLength];  
        in.readFully(bodyBytes);  // read body  
		return bodyBytes;
	}

	private void packet(byte[] bytes) {
		System.out.println("packet" + bytes.length);	
		 
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
