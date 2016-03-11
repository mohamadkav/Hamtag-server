package net.hamtag.server.api.request.types.ads.panel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;
import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.AdDTO;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;
import net.hamtag.server.utils.CategoryToStringConverter;
import net.hamtag.server.utils.Config;

public class GetAdsRequest extends BaseWebPanelRequest{
	String maxResults,lastPublishTime;
	public GetAdsRequest(String maxResults,String lastPublishTime){
		this.maxResults=maxResults;
		this.lastPublishTime=lastPublishTime;
	}
	public Response handle(){
		Set<String>authorizedRoles=new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response=auth(authorizedRoles);
		if(response!=null)
			return response;
		User user=UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		Set<UserRole>roles=user.getUserRoles();
		boolean canGetAll=false;
		for(UserRole ur:roles){
			if(ur.getRole().equals("ROLE_ADMIN")){
				canGetAll=true;
				break;
			}
		}
		int max=0;
		if(maxResults==null||Integer.parseInt(maxResults)>Config.DEFAULT_MAX_RESULTS)
			max=Config.DEFAULT_MAX_RESULTS;
		else
			max=Integer.parseInt(maxResults);
		Date fromDate=new Date();
		if(lastPublishTime==null)
			fromDate=new Date(Long.parseLong(lastPublishTime));
		List<Ad>allAds;
		if(!canGetAll)
			allAds=AdMgr.getAdsByPublishTime(max, fromDate, user.getVisibleCorporations());
		else
			allAds=AdMgr.getAdsByPublishTime(max, fromDate, null);
		List<AdDTO>dtos=new ArrayList<>();
		for(Ad ad:allAds){
			AdDTO dto=new AdDTO();
			dto.setAddress(ad.getAddress());
			dto.setComments(ad.getComments());
			dto.setContentInfos(AdContentMgr.getContentInfoByAd(ad));
			dto.setCorporation(ad.getCorporation().getName());
			dto.setId(ad.getId());
			dto.setDescription(ad.getShortDescription());
			dto.setTitle(ad.getTitle());
			dto.setLink(ad.getLink());
			dto.setLocation(ad.getLocation());
			dto.setCategories(CategoryToStringConverter.getCategoryList(ad.getCategories()));
			dto.setPhone(ad.getPhone());
			dto.setPrice(ad.getPrice()+"");
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
