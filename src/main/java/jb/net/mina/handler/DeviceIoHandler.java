package jb.net.mina.handler;

import jb.net.mina.core.DeviceStanzaHandler;

import org.androidpn.server.util.Config;
import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.net.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
public class DeviceIoHandler extends IoHandlerAdapter {
    private static final Log log = LogFactory.getLog(DeviceIoHandler.class);
    private static final String STANZA_HANDLER = "STANZA_HANDLER";
    private static final String CONNECTION = "CONNECTION";
    private String serverName;
    
    /**
     * Constructor. Set the server name from server instance. 
     */
    protected DeviceIoHandler() {
        serverName = XmppServer.getInstance().getServerName();
    }
    
    @Override
    public void exceptionCaught(IoSession session, Throwable e)
            throws Exception {
       e.printStackTrace();

    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        // TODO Auto-generated method stub
     // Get the stanza handler
        log.debug("#################  mina Device Server messageReceived:"+(String)message);
        DeviceStanzaHandler handler = (DeviceStanzaHandler) session
                .getAttribute(STANZA_HANDLER);
        
     // The stanza handler processes the message
        try {
            handler.processDevice((String)message);
        } catch (Exception e) {
            log.debug("#################  mina Device Server close for error:"+e.getMessage());
            Connection connection = (Connection) session
                    .getAttribute(CONNECTION);
            connection.close();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
    	
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        Connection connection = (Connection) session.getAttribute(CONNECTION);
        connection.close();
        DeviceStanzaHandler handler = (DeviceStanzaHandler) session
                .getAttribute(STANZA_HANDLER);
        handler.close();
        log.debug("#################  mina Device Server sessionClosed...");
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
    	session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);  
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    	// 如果IoSession闲置，则关闭连接  
    	 if (status == IdleStatus.BOTH_IDLE){  
    		 
    		 // sendHeartBeat(session);
    	 }  
    }

    private void sendHeartBeat(IoSession session) throws Exception{    	
		try {			
			String heartbeat = "heartbeat";
			IoBuffer buffer = IoBuffer.allocate(heartbeat.length());
	        buffer.setAutoExpand(true);
            buffer.put(heartbeat.getBytes("UTF-8"));
            buffer.flip();
            session.write(buffer);
			session.setAttribute("sendHbTimes", null);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			Integer time = (Integer) session.getAttribute("sendHbTimes");
			if (time == null) {
				time = 1;
			} else {
				time++;
			}
			if (time < 4) {
				session.setAttribute("sendHbTimes", time);
				sendHeartBeat(session);
			}else{
				sessionClosed(session);
			}
		} 	
    }
    
    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        log.debug("#################  mina Device Server sessionOpened..3.");
     // Create a new connection
        Connection connection = new Connection(session){
            private IoSession ioSession;
            public Connection setSession(IoSession ioSession){
            	this.ioSession = ioSession;
            	return this;
            }
        	public void deliverRawText(String text) {
                deliverRawText(text, true);
            }

            private void deliverRawText(String text, boolean asynchronous) {
                log.debug("SENT: " + text);
                if (!isClosed()) {
                    IoBuffer buffer = IoBuffer.allocate(text.length());
                    buffer.setAutoExpand(true);

                    boolean errorDelivering = false;
                    try {
                        buffer.put(text.getBytes("GBK"));
                        buffer.flip();
                        if (asynchronous) {
                            ioSession.write(buffer);
                        } else {
                            // Send stanza and wait for ACK
                            boolean ok = ioSession.write(buffer).awaitUninterruptibly(
                                    Config.getInt("connection.ack.timeout", 2000));
                            if (!ok) {
                                log.warn("No ACK was received when sending stanza to: "
                                        + this.toString());
                            }
                        }
                    } catch (Exception e) {
                    	e.printStackTrace();
                        log.debug("Connection: Error delivering raw text" + "\n"
                                + this.toString(), e);
                        errorDelivering = true;
                    }
                    // Close the connection if delivering text fails
                    if (errorDelivering && asynchronous) {
                        close();
                    }
                }
            }
        }.setSession(session);
        session.setAttribute(CONNECTION, connection);
        session.setAttribute(STANZA_HANDLER, new DeviceStanzaHandler(serverName,
                connection));
    }

}
