package net.hamtag.server.api.request.types.news;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class GetAllCategoriesRequest extends BaseDeviceRequest{
	public GetAllCategoriesRequest(String phoneNumber,String token){
		setPhoneNumber(phoneNumber);
		setToken(token);
	}
	@Override
	@Deprecated
	public BaseRequestHandler getHandler() {
		return null;
	}
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		List<String>names=new ArrayList<>();
		for(Category category:CategoryMgr.list()){
			names.add(category.getName());
		}
		return new HamtagResponse(names).getResponse(null);
	}
}
