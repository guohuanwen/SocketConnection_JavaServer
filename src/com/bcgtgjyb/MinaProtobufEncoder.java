package com.bcgtgjyb;

import java.nio.ByteBuffer;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.util.byteaccess.BufferByteArray;

import com.google.protobuf.GeneratedMessage;

public class MinaProtobufEncoder extends ProtocolEncoderAdapter {

	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		if (message instanceof Notice.rs_util_heartbeat) {
			System.out.println("rs_util_heartbeat");
			sendOut((GeneratedMessage) message, 1, out);
		}
		if (message instanceof Notice.chat_message) {
			System.out.println("rs_receiver_message");
			sendOut((GeneratedMessage) message, 2, out);
		}
	}

	public void sendOut(GeneratedMessage generatedMessage, int type,
			ProtocolEncoderOutput tcpOperate) {
		byte[] bytes = generatedMessage.toByteArray();
		int length = bytes.length;
		IoBuffer  byteBuffer = IoBuffer.allocate(8 + length);
		byteBuffer.putInt(type);
		byteBuffer.putInt(length);
		byteBuffer.put(bytes);
		byteBuffer.flip();
		tcpOperate.write(byteBuffer);
	}
}