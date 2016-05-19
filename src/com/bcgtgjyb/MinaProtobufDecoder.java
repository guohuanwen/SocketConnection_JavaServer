package com.bcgtgjyb;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MinaProtobufDecoder extends CumulativeProtocolDecoder {

	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		int type = -1;
		// ���û�н�����Header���֣�4�ֽڣ���ֱ�ӷ���false
		if (in.remaining() < 8) {
			return false;
		} else {
			// ��ǿ�ʼλ�ã����һ����Ϣû��������򷵻ص����λ��
			in.mark();
			// ����
			type = in.getInt();
			// ��ȡheader���֣���ȡbody����
			int bodyLength = in.getInt();

			// ���bodyû�н���������ֱ�ӷ���false
			if (in.remaining() < bodyLength) {
				in.reset(); // IoBuffer position�ص�ԭ����ǵĵط�
				return false;
			} else {
				byte[] bodyBytes = new byte[bodyLength];
				in.get(bodyBytes); // ��ȡbody����
				if (type == 0) {
					Notice.rq_util_heartbeat rHeartbeat = Notice.rq_util_heartbeat
							.parseFrom(bodyBytes);
					out.write(rHeartbeat);
				} else if (type == 2) {
					Notice.rq_send_message rMessage = Notice.rq_send_message
							.parseFrom(bodyBytes);
					out.write(rMessage);
				}
				return true;
			}
		}

	}
}