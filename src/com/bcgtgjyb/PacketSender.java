package com.bcgtgjyb;



import com.google.protobuf.GeneratedMessage;

import java.nio.ByteBuffer;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

/**
 * Created by bigwen on 2016/5/15.
 */
public class PacketSender {
	
    public static void sendHeartbeat(IoSession tcpOperate) throws Exception{
        Notice.rs_util_heartbeat rq_util_heartbeat = Notice.rs_util_heartbeat.newBuilder()
                .setCode(1)
                .build();
        send(rq_util_heartbeat,tcpOperate);
    }

    public static void sendMessage(IoSession tcpOperate,String text) throws Exception {
    	Notice.rs_receiver_message rsMessage = Notice.rs_receiver_message.newBuilder()
    			.setRsText("service: "+text)
    			.build();
        send(rsMessage,tcpOperate);
    }
    
    private static void send(GeneratedMessage generatedMessage,IoSession tcpOperate){
    	tcpOperate.write(generatedMessage);
    }
}
