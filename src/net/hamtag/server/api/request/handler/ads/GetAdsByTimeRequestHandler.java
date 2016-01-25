package net.hamtag.server.api.request.handler.ads;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.handler.BaseRequestHandler;
import net.hamtag.server.api.request.handler.news.GetNewsByCategoryForDeviceRequestHandler;
import net.hamtag.server.api.request.types.ads.GetAdsByTimeRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.AdDTO;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;

public class GetAdsByTimeRequestHandler extends BaseRequestHandler{
	private GetAdsByTimeRequest request;
	public GetAdsByTimeRequestHandler(GetAdsByTimeRequest req){
		this.request=req;
	}
	@Override
	public Response handle() {
		List<AdDTO>dtos=new ArrayList<>();
		Response response=request.auth();
		if(response!=null)
			return response;
		List<Ad>allAds=AdMgr.getAdsByDate(request.getLastUpdateTime(), request.getNumber());
		for(Ad ad:allAds){
			AdDTO dto=new AdDTO();
			dto.setAddress(ad.getAddress());
			dto.setComments(ad.getComments());
			dto.setContentInfos(AdContentMgr.getContentInfoByAd(ad));
			dto.setCorporation(ad.getCorporation().getName());
			dto.setId(ad.getId());
			dto.setLink(ad.getLink());
			dto.setLocation(ad.getLocation());
			dto.setCategories(GetNewsByCategoryForDeviceRequestHandler.getCategoryList(ad.getCategories()));
			dto.setPhone(ad.getPhone());
			dto.setPrice(ad.getPrice()+"");
			dtos.add(dto);
		}
		return new HamtagResponse(dtos).getResponse(null);
	}
}
