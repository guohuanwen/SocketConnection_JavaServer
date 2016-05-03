package com.bcgtgjyb;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MinaProtobufEncoder extends ProtocolEncoderAdapter {  
  
    
    public void encode(IoSession session, Object message,  
            ProtocolEncoderOutput out) throws Exception {  
    	Notice.rq_game_changeDirection r = (Notice.rq_game_changeDirection)message;
        byte[] bytes = r.toByteArray(); // Student对象转为protobuf字节码  
        int type = 1;
        int length = bytes.length;  
        IoBuffer buffer = IoBuffer.allocate(length + 8);  
        buffer.putInt(type);
        buffer.putInt(length); // write header  
        buffer.put(bytes); // write body  
        buffer.flip();  
        out.write(buffer);  
    }  
} 