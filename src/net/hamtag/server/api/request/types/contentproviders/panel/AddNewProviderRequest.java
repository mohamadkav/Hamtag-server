package net.hamtag.server.api.request.types.contentproviders.panel;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.contentprovider.ContentProvider;
import net.hamtag.server.datatypes.contentprovider.ContentProviderMgr;

public class AddNewProviderRequest extends BaseWebPanelRequest{
	String requestJson;
	AddNewProviderRequestInputJson requestInputJson;
	private enum Error{
		CATEGORY_DOES_NOT_EXIST,
		CATEGORIES_MUST_BE_SELECTED,
		JSON_PARSE_EXCEPTION
	}
	public AddNewProviderRequest(String requestJson){
		this.requestJson=requestJson;
	}
	public Response handle(){
		try {
			this.requestInputJson=new ObjectMapper().readValue(requestJson, AddNewProviderRequestInputJson.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new HamtagResponse(Error.JSON_PARSE_EXCEPTION).getResponse(Response.Status.BAD_REQUEST);
		}
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		Set<Category>categories=new HashSet<>();
		if(requestInputJson.getCategories()==null||requestInputJson.getCategories().isEmpty())
			return new HamtagResponse(Error.CATEGORIES_MUST_BE_SELECTED).getResponse(Response.Status.BAD_REQUEST);
		for(String categoryName:requestInputJson.getCategories()){
			Category category=CategoryMgr.getCategoryByName(categoryName);
			if(category==null)
				return new HamtagResponse(Error.CATEGORY_DOES_NOT_EXIST).getResponse(Response.Status.BAD_REQUEST);
			categories.add(category);
		}
		ContentProvider contentProvider=new ContentProvider();
		contentProvider.setCategories(categories);
		contentProvider.setName(requestInputJson.getProviderName());
		ContentProviderMgr.add(contentProvider);
		return new HamtagResponse().getResponse(null);
	}
}
