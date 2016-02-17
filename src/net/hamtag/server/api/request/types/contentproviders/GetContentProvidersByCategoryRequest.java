package net.hamtag.server.api.request.types.contentproviders;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.device.ProviderInfoDTO;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;

public class GetContentProvidersByCategoryRequest extends BaseDeviceRequest{
	private enum Error{
		ID_NOT_PROVIDED,
		CATEGORY_NOT_FOUND
	}
	String categoryid;
	public GetContentProvidersByCategoryRequest(String phoneNumber,String token,String categoryid){
		this.categoryid=categoryid;
		setPhoneNumber(phoneNumber);
		setToken(token);
	}
	@Override
	public Response handle(){
		Response response=auth();
		if(response!=null)
			return response;
		if(categoryid==null||categoryid.trim().isEmpty())
			return new HamtagResponse(Error.ID_NOT_PROVIDED).getResponse(Response.Status.BAD_REQUEST);
		Category category=CategoryMgr.getInstance().get(Category.class, Long.parseLong(categoryid));
		if(category==null)
			return new HamtagResponse(Error.CATEGORY_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		List<ProviderInfoDTO>dtos=new ArrayList<>();
		for(ContentProvider cp:category.getContentProviders()){
			ProviderInfoDTO dto=new ProviderInfoDTO();
			dto.setId(cp.getId());
			dto.setName(cp.getName());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
