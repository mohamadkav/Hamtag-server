package net.hamtag.server.api.request.types;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.handler.BaseRequestHandler;

public abstract class BaseRequest {
	private static ObjectMapper mapper;

	@JsonIgnore
	public abstract BaseRequestHandler getHandler();
	
	
	public static BaseRequest buildRequest(String requestJson , Class<? extends BaseRequest> requestClass){
		if(mapper == null)
			mapper = new ObjectMapper();
		BaseRequest request = null;
		try {
			request = mapper.readValue(requestJson, requestClass);
		}catch(Exception e){
			e.printStackTrace();
		}
		return request;
	}
	
	
	public Response handle(){
    	return null;
    }

}
