package net.hamtag.server.api.request.types.ads.panel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.hamtag.server.api.request.types.BaseWebPanelRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.AdShownDTO;
import net.hamtag.server.datatypes.ad.AdShown;
import net.hamtag.server.datatypes.ad.AdShownMgr;
import net.hamtag.server.datatypes.corporation.Corporation;
import net.hamtag.server.datatypes.corporation.CorporationMgr;
import net.hamtag.server.datatypes.user.User;
import net.hamtag.server.datatypes.user.UserMgr;
import net.hamtag.server.datatypes.user.UserRole;
import net.hamtag.server.utils.Config;

public class AdShownReportRequest extends BaseWebPanelRequest{
	String requestJson;
	AdReportGetterInputRequest requestInputJson;
	public AdShownReportRequest(String requestJson){
		this.requestJson=requestJson;
	}
	private enum Error {
		CORPORATION_DOES_NOT_EXIST, JSON_PARSE_EXCEPTION,CORPORATIONS_MUST_BE_SELECTED,CORPORATION_VISIBILITY_ERROR
	}
	public Response handle(){
		Set<String> authorizedRoles = new HashSet<>();
		authorizedRoles.add("ROLE_ADMIN");
		Response response = auth(authorizedRoles);
		if (response != null)
			return response;
		try {
			this.requestInputJson = new ObjectMapper().readValue(requestJson,
					AdReportGetterInputRequest.class);
		} catch (IOException e) {
			e.printStackTrace();
			return new HamtagResponse(Error.JSON_PARSE_EXCEPTION)
					.getResponse(Response.Status.BAD_REQUEST);
		}
		Set<Corporation> corporations = new HashSet<>();
		if(requestInputJson.getCorporationIds()==null||requestInputJson.getCorporationIds().isEmpty())
			return new HamtagResponse(Error.CORPORATIONS_MUST_BE_SELECTED).getResponse(Response.Status.BAD_REQUEST);
		User user=UserMgr.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		boolean canSeeAll = false;
		for (UserRole roles : user.getUserRoles()) {
			if (roles.getRole().equals("ROLE_ADMIN")) {
				canSeeAll = true;
				break;
			}
		}
		Set<Corporation> visibleCorporations = null;
		if(!canSeeAll) {
			visibleCorporations= user.getVisibleCorporations();
		}
		for(Long corpId:requestInputJson.getCorporationIds()){
			Corporation corporation=CorporationMgr.getInstance().get(Corporation.class, corpId);
			if(corporation==null)
				return new HamtagResponse(Error.CORPORATION_DOES_NOT_EXIST).getResponse(Response.Status.BAD_REQUEST);
			if(!canSeeAll){
				if (!visibleCorporations.contains(corporation))
					return new HamtagResponse(Error.CORPORATION_VISIBILITY_ERROR).getResponse(Response.Status.BAD_REQUEST);
			}
			corporations.add(corporation);
		}
		Date fromDate = null,toDate = null,startFromShownDate = null;
		int maxResults;
		if(requestInputJson.getFromDate()!=null)
			fromDate=new Date(requestInputJson.getFromDate());
		if(requestInputJson.getToDate()!=null)
			toDate=new Date(requestInputJson.getToDate());
		if(requestInputJson.getStartFromShownDate()!=null)
			startFromShownDate=new Date(requestInputJson.getStartFromShownDate());
		if(requestInputJson.getMaxResults()!=null&&requestInputJson.getMaxResults()<Config.DEFAULT_MAX_RESULTS)
			maxResults=requestInputJson.getMaxResults();
		else
			maxResults=Config.DEFAULT_MAX_RESULTS;
		List<AdShown> allAdShowns=AdShownMgr.getAdsShownByFilter(fromDate, toDate, startFromShownDate, maxResults, corporations);
		List<AdShownDTO>dtos=new ArrayList<>();
		for(AdShown adShown:allAdShowns){
			AdShownDTO dto=new AdShownDTO();
			dto.setAdId(adShown.getAd().getId());
			dto.setDeviceUserName(adShown.getDevice().getPhoneNumber());
			dto.setId(adShown.getId());
			dto.setPercentage(adShown.getPercentage());
			dto.setShowDate(adShown.getShowDate());
			dto.setShownTime(adShown.getShownTime());
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
