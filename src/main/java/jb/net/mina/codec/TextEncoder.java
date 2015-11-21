package jb.net.mina.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class TextEncoder implements ProtocolEncoder {

	@Override
	public void dispose(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		if (message instanceof String) {
			byte[] bytes = ((String) message).getBytes("GBK");
			IoBuffer buffer = IoBuffer.allocate(bytes.length);
			buffer.put(bytes);
			//buffer.put((byte) 0x0);
			buffer.flip();
			out.write(buffer);
		}
	}
}
