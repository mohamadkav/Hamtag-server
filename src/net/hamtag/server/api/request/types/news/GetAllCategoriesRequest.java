package net.hamtag.server.api.request.types.news;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.category.CategoryDTO;
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
		List<CategoryDTO>dtos=new ArrayList<>();
		List<Category>allCategories=CategoryMgr.list();
		for(Category category:allCategories){
			CategoryDTO dto=new CategoryDTO();
			dto.setId(category.getId());
			dto.setImage((Object)category.getImage());
			dto.setName(category.getName());
			dto.setTranslation(category.getTranslation());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
