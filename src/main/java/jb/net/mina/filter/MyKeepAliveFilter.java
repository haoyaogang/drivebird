package jb.net.mina.filter;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

public class MyKeepAliveFilter extends KeepAliveFilter {

	public MyKeepAliveFilter(KeepAliveMessageFactory messageFactory) {
		super(messageFactory);
	}

	public MyKeepAliveFilter(){
		this(new KeepAliveMessageImpl());
		setForwardEvent(false);  
		setRequestInterval(30);  
        setRequestTimeout(10);  
        setRequestTimeoutHandler(new KeepAliveRequestTimeoutHandler() {    
            public void keepAliveRequestTimedOut(KeepAliveFilter filter, IoSession session) throws Exception {  
                System.out.println("连接已无响应...关闭session鸟。KeepAliveRequestTimeoutHandler.CLOSE 和我作用一样，默认就是这个");  
                session.close(true);  
            }  
              
        });  
	}
	static class KeepAliveMessageImpl implements KeepAliveMessageFactory {  
        private static final String KAMSG_REQ = "###$$$";  
        private static final String KAMSG_REP = "$$$###";  
          
        public Object getRequest(IoSession session) {  
            return KAMSG_REQ;  
        }  
  
        public Object getResponse(IoSession session, Object request) {  
            return KAMSG_REP;  
        }  
  
        public boolean isRequest(IoSession session, Object message) {  
            return KAMSG_REQ.equals(message);  
        }  
  
        public boolean isResponse(IoSession session, Object message) {  
            return KAMSG_REP.equals(message);  
        }  
    }  
}
