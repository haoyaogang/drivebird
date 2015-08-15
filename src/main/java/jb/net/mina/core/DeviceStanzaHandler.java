package jb.net.mina.core;

import jb.absx.F;
import jb.listener.Application;
import jb.net.mina.session.DeviceSessionManager;
import jb.pageModel.BirdEquip;
import jb.service.BirdEquipServiceI;
import jb.util.NotificationMesageUtil;

import org.androidpn.server.xmpp.auth.AuthToken;
import org.androidpn.server.xmpp.net.Connection;
import org.androidpn.server.xmpp.session.ClientSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xmpp.packet.JID;

public class DeviceStanzaHandler {
    private static final Log log = LogFactory.getLog(DeviceStanzaHandler.class);
    public static final String RESOURCE = "zwd-client";
	protected Connection connection;

    protected ClientSession session;

    protected String serverName;

    private boolean sessionCreated = false;
    
    private BirdEquipServiceI equipService = Application.getBirdEquipService();
    
    public DeviceStanzaHandler(String serverName,Connection connection){
    	this.serverName = serverName;
    	this.connection = connection;
    }
	public void close(){
		if(sessionCreated){
			String deviceNum = (String)session.getSessionData("deviceNum");
			if(F.empty(deviceNum))return;
	        BirdEquip be = new BirdEquip();
	        be.setId(deviceNum);
	        try{
	        	 equipService.logout(be);
	        }catch(Exception e){
                log.error(deviceNum+" 退出失败", e);
	        }	       
		}		
	}
	public void processDevice(String stanza){
        
//        boolean initialStream = stanza.startsWith("<stream:stream");
        boolean initialStream = stanza.contains("ZWD-35DP");
        if (!sessionCreated || initialStream) {
            if (!initialStream) {
                return; // Ignore <?xml version="1.0"?>
            }
            if (!sessionCreated) {
                sessionCreated = true;
                log.debug("#################  mina Device Server sessionCreated = true");
                session = DeviceSessionManager.getInstance().createClientSession(connection);
                try {
//                    XMPPPacketReader reader = new XMPPPacketReader();
//                    MXParser parser = reader.getXPPParser();
//                    parser.setInput(new StringReader(stanza));
//                    for (int eventType = parser.getEventType(); eventType != XmlPullParser.START_TAG;) {
//                        eventType = parser.next();
//                    }
//                    String deviceNum = parser.getAttributeValue(null, "from");
                    if(stanza.length()>= 41)
                    {
                        String dtutype = stanza.substring(0, 11);
                        String dtuname = stanza.substring(11, 20);
                        String deviceNum= stanza.substring(20, 32);
                        String pwd = stanza.substring(32, 41);
                        session.setAuthToken(new AuthToken(deviceNum),RESOURCE);
                        session.setSessionData("deviceNum", deviceNum);
                        //System.out.println(JID.unescapeNode(session.getUsername()));
                        BirdEquip be = new BirdEquip();
                        be.setId(deviceNum);
                        be.setName(dtuname);
                        be.setDtutype(dtutype);
                        be.setPwd(pwd);
                        try{
                        	 equipService.login(be);
	           	        }catch(Exception e){
	                         log.error(deviceNum+" 注册 失败", e);
	           	        }                     
                        //这里需要入库
                        log.debug("############### DTU devicenum = "+deviceNum);
                    }
                } catch (Exception e) {
                	log.error("#################  mina Device Server Error="+e.getMessage(),e);
                }
            }
            return;
        }

        // If end of stream was requested
        if (stanza.equals("</stream:stream>")) {
            session.close();
            return;
        }
        // Ignore <?xml version="1.0"?>
        if (stanza.startsWith("<?xml")) {
            return;
        }
        //to handle device message
//        if(stanza.startsWith("<message"))
       /* {
            String apiKey = "123456780";
            String title = "";
            String message = stanza;
            
            log.debug("#################  mina DTU broadcast ="+message);
            //nm.sendBroadcast(apiKey, title, message, "");
        }*/
        NotificationMesageUtil.sendBroadcast(stanza);
    }
	public static void sendMessage(String username,String message){
		ClientSession clientSession = DeviceSessionManager.getInstance().getSession(RESOURCE, username);
		if(clientSession!=null){
			clientSession.deliverRawText(message);
		}
	}
}
