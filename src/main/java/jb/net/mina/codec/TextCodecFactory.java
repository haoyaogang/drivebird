package jb.net.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;

public class TextCodecFactory implements ProtocolCodecFactory {
    private TextDecoder textDecoder;
    private TextEncoder textEncoder;
    public TextCodecFactory()
    {
        textDecoder = new TextDecoder();
        textEncoder = new TextEncoder();
    }
    @Override
    public ProtocolDecoder getDecoder(IoSession session) {
        return textDecoder;
    }
    @Override
    public ProtocolEncoder getEncoder(IoSession arg0) throws Exception {
        return textEncoder;
    }
}
