package jb.util;

import jb.listener.Application;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;

public abstract class OpenfireClientUtil {
	private static RestApiClient restApiClient;
	private static final String SV130 = "SV130";
	private static final String SV131 = "SV131";
	private static final String SV132 = "SV132";
	
	public static RestApiClient getRestApiClient(){
		if(restApiClient == null){
			synchronized (OpenfireClientUtil.class) {
				if(restApiClient == null){
					 String token = Application.getString(SV130);
					 AuthenticationToken authenticationToken = new AuthenticationToken(token);
					 String ip = Application.getString(SV131);
					 String port = Application.getString(SV132);
					 restApiClient = new RestApiClient(ip, Integer.parseInt(port), authenticationToken);
				}				
			}
		}		 
		return restApiClient;
	}
	
	public static void clear(){
		if(restApiClient == null)
			return;
		synchronized (OpenfireClientUtil.class) {
			if(restApiClient != null)
				restApiClient = null;
		}
	}

}
