package net.hamtag.server.api.request.types.ads.panel;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.category.CategoryMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;

public class AddAdRequest extends BaseWebPanelRequest{
	String requestJson;
	AdInputRequestJson requestInputJson;
	public AddAdRequest(String requestJson){
		this.requestJson=requestJson;
	}
	private enum Error{
		CATEGORY_DOES_NOT_EXIST,
		CATEGORIES_MUST_BE_SELECTED,
		JSON_PARSE_EXCEPTION,
		CORPORATION_VISIBILITY_ERROR,
		CORPORATION_NOT_FOUND,
		AD_NOT_FOUND_FOR_UPDATE
	}
	public Response handle(){
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		try {
			this.requestInputJson=new ObjectMapper().readValue(requestJson, AdInputRequestJson.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new HamtagResponse(Error.JSON_PARSE_EXCEPTION).getResponse(Response.Status.BAD_REQUEST);
		}
		Set<Category>categories=new HashSet<>();
		if(requestInputJson.getCategories()==null||requestInputJson.getCategories().isEmpty())
			return new HamtagResponse(Error.CATEGORIES_MUST_BE_SELECTED).getResponse(Response.Status.BAD_REQUEST);
		for(String categoryName:requestInputJson.getCategories()){
			Category category=CategoryMgr.getCategoryByName(categoryName);
			if(category==null)
				return new HamtagResponse(Error.CATEGORY_DOES_NOT_EXIST).getResponse(Response.Status.BAD_REQUEST);
			categories.add(category);
		}
		Corporation corporation=CorporationMgr.getInstance().get(Corporation.class, requestInputJson.getCorporationId());
		if(corporation==null)
			return new HamtagResponse(Error.CORPORATION_NOT_FOUND).getResponse(Response.Status.BAD_REQUEST);
		User user=UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		boolean canSeeAll = false;
		for (UserRole roles : user.getUserRoles()) {
			if (roles.getRole().equals("ROLE_ADMIN")) {
				canSeeAll = true;
				break;
			}
		}
		if(!canSeeAll) {
			Set<Corporation> visibleCorporations= user.getVisibleCorporations();
			if (!visibleCorporations.contains(corporation))
				return new HamtagResponse(Error.CORPORATION_VISIBILITY_ERROR).getResponse(Response.Status.BAD_REQUEST);
		}
		
		Ad ad;
		if(requestInputJson.getId()!=null){
			ad=AdMgr.getInstance().get(Ad.class, requestInputJson.getId());
			if(ad==null)
				return new HamtagResponse(Error.AD_NOT_FOUND_FOR_UPDATE).getResponse(Response.Status.BAD_REQUEST);
		}
		else
			ad=new Ad();
		ad.setCategories(categories);
		ad.setAddress(requestInputJson.getAddress());
		ad.setComments(requestInputJson.getComments());
		ad.setTitle(requestInputJson.getTitle());
		ad.setCorporation(corporation);
		ad.setLink(requestInputJson.getLink());
		ad.setLocation(requestInputJson.getLocation());
		ad.setPhone(requestInputJson.getPhoneNumber());
		ad.setPrice(requestInputJson.getPrice());
		ad.setPublishTime(new Date());
		ad.setIsRelatedToNews(false);
		if(requestInputJson.getId()!=null){
			AdMgr.update(ad);
		}
		else{
			ad.setUser(user);
			AdMgr.add(ad);
		}
		return new HamtagResponse().getResponse(null);
	}

}
