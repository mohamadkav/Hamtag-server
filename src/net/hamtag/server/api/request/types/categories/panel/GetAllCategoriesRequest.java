package net.hamtag.server.api.request.types.categories.panel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.category.CategoryDTO;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;

public class GetAllCategoriesRequest extends BaseWebPanelRequest{
	Set<String>neededRoles=new HashSet<>();
	@Override
	public Response handle(){
		neededRoles.add("ROLE_ADMIN");
		neededRoles.add("ROLE_WRITER");
		
		Response response=auth(neededRoles);
		if(response!=null)
			return response;
		List<CategoryDTO>dtos=new ArrayList<>();
		List<Category>allCategories=CategoryMgr.list();
		for(Category category:allCategories){
			CategoryDTO dto=new CategoryDTO();
			dto.setId(category.getId());
			dto.setName(category.getName());
			dto.setTranslation(category.getTranslation());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
