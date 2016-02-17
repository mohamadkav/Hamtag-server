package net.hamtag.server.api.request.types.ads.android;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import net.hamtag.server.api.request.types.BaseDeviceRequest;
import net.hamtag.server.api.request.types.news.GetNewsByCategoryForDeviceRequest;
import net.hamtag.server.api.response.HamtagResponse;
import net.hamtag.server.api.response.types.ads.AdDTO;
import net.hamtag.server.datatypes.ad.Ad;
import net.hamtag.server.datatypes.ad.AdContentMgr;
import net.hamtag.server.datatypes.ad.AdMgr;
import net.hamtag.server.utils.CategoryToStringConverter;

public class GetAdsRequest extends BaseDeviceRequest{
	private String maxNumber;
	public GetAdsRequest(String maxNumber,String token,String phoneNumber){
		this.maxNumber=maxNumber;
		setToken(token);
		setPhoneNumber(phoneNumber);
	}
	@Override
	public Response handle() {
		List<AdDTO>dtos=new ArrayList<>();
		Response response=auth();
		if(response!=null)
			return response;
/*		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.DATE,(-1)*Config.FROM_WHEN_TO_ANALYSE_USER_DATA_BY_DAYS);
		List<NewsShown>shownNewsForDevice=NewsShownMgr.getNewsShownByDeviceAndDate(calendar.getTime(), DeviceMgr.getDeviceByPhoneNumber(request.getPhoneNumber()));
		HashMap<Category,Integer>categoriesAndCounts =new HashMap<>();
		HashMap<Category, Integer>catergoriesAndShowTimes=new HashMap<>();
		for(NewsShown newsShown:shownNewsForDevice){
			Set<Category>categories=newsShown.getNews().getCategories();
			for(Category category:categories){
				if(categoriesAndCounts.get(category)==null){
					categoriesAndCounts.put(category, 1);
					catergoriesAndShowTimes.put(category, newsShown.getShownTime());
				}
				else{
					categoriesAndCounts.replace(category, categoriesAndCounts.get(category)+1);
					catergoriesAndShowTimes.replace(category, catergoriesAndShowTimes.get(category)+newsShown.getShownTime());
				}
			}
		}
		//SORT ALL THE SHITS, then fucking map the fucking parameters to the maximum of the fucking shit. then we have a fucking shit.
		List<Ad>allAds=AdMgr.getAdsByDate(request.getLastUpdateTime(), request.getNumber());*/
		List<Ad>allAds=AdMgr.list();
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
