package net.hamtag.server.api.request.handler.ads;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.types.ads.GetAdsByCategoryForDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.AdDTO;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.datatypes.category.Category;
import net.hamtag.server.datatypes.device.Device;
import net.hamtag.server.datatypes.device.DeviceMgr;

public class GetAdsByCategoryForDeviceRequestHandler extends BaseRequestHandler{
	private GetAdsByCategoryForDeviceRequest request;
	public GetAdsByCategoryForDeviceRequestHandler(GetAdsByCategoryForDeviceRequest req){
		this.request=req;
	}
	@Override
	public Response handle() {
		List<AdDTO>dtos=new ArrayList<>();
		Response response=request.auth();
		if(response!=null)
			return response;
		Device device=DeviceMgr.getDeviceByPhoneNumber(request.getPhoneNumber());
		Set<Category>categories=device.getCategories();//Arrays.asList(request.getCategories().split("\\s*,\\s*"));
		List<Ad>allAds=AdMgr.getAdsByCategory(categories, request.getLastUpdateTime(), request.getNumber());
		for(Ad ad:allAds){
			AdDTO dto=new AdDTO();
			dto.setAddress(ad.getAddress());
			dto.setComments(ad.getComments());
			dto.setContentInfos(AdContentMgr.getContentInfoByAd(ad));
			dto.setCorporation(ad.getCorporation().getName());
			dto.setId(ad.getId());
			dto.setLink(ad.getLink());
			dto.setLocation(ad.getLocation());
			dto.setPhone(ad.getPhone());
			dto.setPrice(ad.getPrice()+"");
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
