package net.hamtag.server.api.request.types.categories.panel;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class AddNewCategoryRequest extends BaseWebPanelRequest{
	private enum Error{
		NAME_ALREADY_EXISTS
	}
	String categoryName,translation;
	public AddNewCategoryRequest(String categoryName,String translation){
		this.categoryName=categoryName;
		this.translation=translation;
	}
	Set<String>neededRoles=new HashSet<>();
	@Override
	public Response handle(){
		neededRoles.add("ROLE_ADMIN");
		neededRoles.add("ROLE_WRITER");
		
		Response response=auth(neededRoles);
		if(response!=null)
			return response;
		if(CategoryMgr.getCategoryByName(categoryName.toUpperCase())!=null)
			return new HamtagResponse(Error.NAME_ALREADY_EXISTS).getResponse(Response.Status.BAD_GATEWAY);
		Category category=new Category();
		category.setName(categoryName);
		category.setTranslation(translation);
		CategoryMgr.add(category);
		return new HamtagResponse().getResponse(null);
	}
}
