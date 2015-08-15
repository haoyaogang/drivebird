package jb.net.mina.codec;

import java.io.InputStream;

import org.androidpn.server.xmpp.net.XmppIoHandler;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.jivesoftware.openfire.nio.XMLLightweightParser;

public class TextEncoder implements ProtocolEncoder {

    @Override
    public void dispose(IoSession arg0) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void encode(IoSession arg0, Object arg1, ProtocolEncoderOutput arg2)
            throws Exception {
        // TODO Auto-generated method stub
        
    }

    

}
