package jb.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import jb.android.push.NotificationManager;

import org.androidpn.server.xmpp.XmppServer;
import org.androidpn.server.xmpp.session.ClientSession;
import org.androidpn.server.xmpp.session.SessionManager;

public class NotificationMesageUtil {
	public static boolean notifMessage(String openId,String message){
		try{
			NotificationManager notif = (NotificationManager) XmppServer
					.getInstance().getBean("notificationManager");
			Collection<ClientSession> sessions = SessionManager.getInstance()
					.getSessions();
			Set<ClientSession> usernames = new HashSet<ClientSession>();
			for (ClientSession cs : sessions) {
					if(cs.getUsername().equals(openId))
					usernames.add(cs);

			}
			notif.sendNotifcationToSession("1234567890", "test", "title", message,
					"uri",
					usernames.toArray(new ClientSession[usernames.size()]));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void sendBroadcast(String message){
		NotificationManager notif = (NotificationManager) XmppServer
				.getInstance().getBean("notificationManager");
		notif.sendBroadcast("1234567890", "title", message, "uri");		
	}
}
