package jb.net.mina.handler;

import jb.net.mina.core.DeviceStanzaHandler;

import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.net.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    public void exceptionCaught(IoSession session, Throwable arg1)
            throws Exception {
        // TODO Auto-generated method stub

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
    public void messageSent(IoSession session, Object arg1) throws Exception {
        // TODO Auto-generated method stub

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
        // TODO Auto-generated method stub
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus arg1) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        log.debug("#################  mina Device Server sessionOpened..3.");
     // Create a new connection
        Connection connection = new Connection(session);
        session.setAttribute(CONNECTION, connection);
        session.setAttribute(STANZA_HANDLER, new DeviceStanzaHandler(serverName,
                connection));
    }

}
