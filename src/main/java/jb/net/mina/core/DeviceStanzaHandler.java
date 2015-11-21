package jb.net.mina.core;

import java.util.HashMap;
import java.util.Map;

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

import com.alibaba.fastjson.JSON;

public class DeviceStanzaHandler {
    private static final Log log = LogFactory.getLog(DeviceStanzaHandler.class);
    public static final String RESOURCE = "zwd-client";
    /**
     * 音量控制
     */
    public static final String VOICE_CONTROL = "D2F4C1BF";
    public static final String DEVICE_NUM = "deviceNum";
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
                        dtutype = dtutype.trim();
                        dtuname = dtuname.trim();
                        deviceNum = deviceNum.trim();
                        pwd = pwd.trim();
                        session.setAuthToken(new AuthToken(deviceNum),RESOURCE);
                        session.setSessionData(DEVICE_NUM, deviceNum);
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
                        //发送获取音量信息
                        //session.deliverRawText(VOICE_CONTROL);
                        //这里需要入库
                        log.debug("############### DTU devicenum = "+deviceNum);
                    }
                } catch (Exception e) {
                	log.error("#################  mina Device Server Error="+e.getMessage(),e);
                }
            }
            return;
        }

        if (session != null && stanza.indexOf(VOICE_CONTROL) > -1) {
            String deviceNum = (String) session.getSessionData(DEVICE_NUM);
            if (!F.empty(deviceNum)) {
                BirdEquip be = new BirdEquip();
                be.setId(deviceNum);
                be.setVoice(stanza.trim());
                try{
                    equipService.edit(be);
                }catch(Exception e){
                    log.error(deviceNum+" 音量入库失败", e);
                }
            }
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
        if(!F.empty(stanza)&&session!=null){
	        Map<String,String> message = new HashMap<String,String>();
	        message.put(DEVICE_NUM, (String) session.getSessionData(DEVICE_NUM));
	        message.put("body", stanza);
	        NotificationMesageUtil.sendBroadcast(JSON.toJSONString(message));
        }
    }
	public static void sendMessage(String username,String message){
		ClientSession clientSession = DeviceSessionManager.getInstance().getSession(RESOURCE, username);
		if(clientSession!=null){
			clientSession.deliverRawText(message);
            //修改音量设置，动态获取下最新音量
            if(message.indexOf(VOICE_CONTROL)>-1){
                clientSession.deliverRawText(VOICE_CONTROL);
            }

        }
	}

}
