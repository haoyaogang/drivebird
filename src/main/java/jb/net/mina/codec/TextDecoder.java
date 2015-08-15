package jb.net.mina.codec;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.androidpn.server.xmpp.net.XmppIoHandler;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.jivesoftware.openfire.nio.XMLLightweightParser;

public class TextDecoder extends CumulativeProtocolDecoder {

    
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
            ProtocolDecoderOutput out) throws Exception {
        //Filter heart udp
        InputStream is = in.asInputStream();
        is.mark(0);
        int fb = is.read();
        if(fb == 0xfe )
        {
            System.out.println("#####Mina  Filter heart udp ");
            return true;
        }
        //messages
        is.reset();
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(  
                new InputStreamReader(is,"GB2312"));  
        String data = "";  
        while ((data = br.readLine()) != null) {  
            sb.append(data);  
        }  
        String result = sb.toString(); 
        System.out.println("#####mina  codecstr = "+result);
        out.write(result);
        return true;
    }

}
