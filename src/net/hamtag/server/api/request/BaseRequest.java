package net.hamtag.server.api.request;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.response.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;


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
